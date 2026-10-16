package com.example.data.serialize.dao

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import com.example.data.serialize.model.TemplateGRVLocal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class TemplateGRVDao {

    companion object {
        private const val FOLDER_NAME = "GRVTemplate"
        private const val JSON_FILE_NAME = "template_GRV.txt"
    }

    private val gson = Gson()

    // ---------------------------------------------------------
    // Récupération du JSON
    // ---------------------------------------------------------

    private suspend fun getTemplates(context: Context): List<TemplateGRVLocal> = withContext(Dispatchers.IO) {
        val results = mutableListOf<TemplateGRVLocal>()

        fun parseJson(json: String): List<TemplateGRVLocal> {
            if (json.isBlank()) return emptyList()
            return try {
                val type = object : TypeToken<List<TemplateGRVLocal>>() {}.type
                gson.fromJson<List<TemplateGRVLocal>>(json, type) ?: emptyList()
            } catch (e: Exception) {
                Log.e("TemplateGRVDao", "JSON parse error", e)
                emptyList()
            }
        }

        // 1. SCAN VIA DOCUMENT TREE (SAF)
        try {
            val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val treeUriString = sharedPrefs.getString("template_folder_uri", null)

            if (treeUriString != null) {
                val treeUri = Uri.parse(treeUriString)
                val documentFile = DocumentFile.fromTreeUri(context, treeUri)

                if (documentFile != null && documentFile.isDirectory) {
                    documentFile.listFiles().forEach { file ->
                        val name = file.name
                        if (file.isFile && name != null && name.startsWith("template_GRV") && name.endsWith(".txt")) {
                            context.contentResolver.openInputStream(file.uri)?.use { input ->
                                val json = input.bufferedReader().use { it.readText() }
                                results.addAll(parseJson(json))
                            }
                        }
                    }
                    if (results.isNotEmpty()) {
                        Log.d("TemplateGRVDao", "SAF getTemplates found ${results.size} templates")
                        return@withContext results.distinctBy { it.name }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("TemplateGRVDao", "SAF getTemplates Error", e)
        }

        // 2. SCAN DIRECT (Fichier physique)
        try {
            val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val grvDir = File(downloadDir, FOLDER_NAME)
            if (grvDir.exists() && grvDir.isDirectory) {
                grvDir.listFiles()?.forEach { file ->
                    if (file.isFile && file.name.startsWith("template_GRV") && file.name.endsWith(".txt")) {
                        val json = file.readText(Charsets.UTF_8)
                        results.addAll(parseJson(json))
                    }
                }
                if (results.isNotEmpty()) {
                    Log.d("TemplateGRVDao", "Direct File getTemplates found ${results.size} templates")
                    return@withContext results.distinctBy { it.name }
                }
            }
        } catch (e: Exception) {
            Log.e("TemplateGRVDao", "Direct File getTemplates Error", e)
        }

        // 3. MEDIASTORE (Fallback)
        try {
            val contentResolver = context.contentResolver
            val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI
            val projection = arrayOf(
                MediaStore.Downloads._ID,
                MediaStore.Downloads.DISPLAY_NAME
            )
            val selection = "${MediaStore.Downloads.DISPLAY_NAME} LIKE ? AND ${MediaStore.Downloads.RELATIVE_PATH} LIKE ?"
            val selectionArgs = arrayOf("template_GRV%.txt", "%$FOLDER_NAME%")

            contentResolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
                val idIndex = cursor.getColumnIndexOrThrow(MediaStore.Downloads._ID)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idIndex)
                    val uri = android.content.ContentUris.withAppendedId(collection, id)
                    contentResolver.openInputStream(uri)?.use { input ->
                        val json = input.bufferedReader().use { it.readText() }
                        results.addAll(parseJson(json))
                    }
                }
            }
            if (results.isNotEmpty()) {
                Log.d("TemplateGRVDao", "MediaStore getTemplates found ${results.size} templates")
                return@withContext results.distinctBy { it.name }
            }
        } catch (e: Exception) {
            Log.e("TemplateGRVDao", "MediaStore getTemplates Error", e)
        }

        emptyList()
    }


    // ---------------------------------------------------------
    // Sauvegarde du JSON
    // ---------------------------------------------------------

    private suspend fun saveTemplates(context: Context, templates: List<TemplateGRVLocal>) =
        withContext(Dispatchers.IO) {
            val json = gson.toJson(templates)

            // 1. SAUVEGARDE VIA SAF (Si configuré)
            try {
                val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                val treeUriString = sharedPrefs.getString("template_folder_uri", null)

                if (treeUriString != null) {
                    val treeUri = Uri.parse(treeUriString)
                    val documentFile = DocumentFile.fromTreeUri(context, treeUri)

                    if (documentFile != null && documentFile.isDirectory) {
                        var jsonFile = documentFile.findFile(JSON_FILE_NAME)
                        if (jsonFile == null) {
                            jsonFile = documentFile.createFile("text/plain", JSON_FILE_NAME)
                        }
                        if (jsonFile != null) {
                            context.contentResolver.openOutputStream(jsonFile.uri, "wt")?.use { output ->
                                val bytes = json.toByteArray(Charsets.UTF_8)
                                output.write(bytes)
                                output.flush()
                                Log.i("TemplateGRVDao", "Successfully wrote ${bytes.size} bytes via SAF to ${jsonFile.uri}")
                            }

                            // Suppression des fichiers doublons générés précédemment (ex: template_GRV (1).txt)
                            documentFile.listFiles().forEach { file ->
                                val name = file.name
                                if (name != null && name.startsWith("template_GRV (") && name.endsWith(").txt")) {
                                    file.delete()
                                    Log.i("TemplateGRVDao", "Cleaned up duplicate file via SAF: $name")
                                }
                            }
                            return@withContext
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("TemplateGRVDao", "SAF Save Error", e)
            }

            // 2. SAUVEGARDE VIA FICHIER DIRECT
            try {
                val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val grvDir = File(downloadDir, FOLDER_NAME)
                if (!grvDir.exists()) {
                    grvDir.mkdirs()
                }
                val jsonFile = File(grvDir, JSON_FILE_NAME)
                jsonFile.writeText(json, Charsets.UTF_8)
                Log.i("TemplateGRVDao", "Successfully wrote ${json.length} chars via Direct File to ${jsonFile.absolutePath}")

                // Suppression des fichiers doublons générés précédemment
                grvDir.listFiles()?.forEach { file ->
                    if (file.name.startsWith("template_GRV (") && file.name.endsWith(").txt")) {
                        file.delete()
                        Log.i("TemplateGRVDao", "Cleaned up duplicate direct file: ${file.name}")
                    }
                }
                return@withContext
            } catch (e: Exception) {
                Log.e("TemplateGRVDao", "Direct File Save Error", e)
            }

            // 3. SAUVEGARDE VIA MEDIASTORE (Fallback)
            try {
                val contentResolver = context.contentResolver
                val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI
                val querySelection = "${MediaStore.Downloads.DISPLAY_NAME} = ? AND ${MediaStore.Downloads.RELATIVE_PATH} LIKE ?"
                val queryArgs = arrayOf(JSON_FILE_NAME, "%$FOLDER_NAME%")

                var uri: Uri? = null
                contentResolver.query(collection, arrayOf(MediaStore.Downloads._ID), querySelection, queryArgs, null)?.use { cursor ->
                    if (cursor.moveToFirst()) {
                        val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Downloads._ID))
                        uri = android.content.ContentUris.withAppendedId(collection, id)
                    }
                }

                if (uri == null) {
                    val values = ContentValues().apply {
                        put(MediaStore.Downloads.DISPLAY_NAME, JSON_FILE_NAME)
                        put(MediaStore.Downloads.MIME_TYPE, "text/plain")
                        put(MediaStore.Downloads.RELATIVE_PATH, "Download/$FOLDER_NAME/")
                    }
                    uri = contentResolver.insert(collection, values)
                }

                uri?.let { targetUri ->
                    contentResolver.openOutputStream(targetUri, "wt")?.use { output ->
                        val bytes = json.toByteArray(Charsets.UTF_8)
                        output.write(bytes)
                        output.flush()
                        Log.i("TemplateGRVDao", "Successfully wrote ${bytes.size} bytes via MediaStore to $targetUri")
                    }
                }
            } catch (e: Exception) {
                Log.e("TemplateGRVDao", "MediaStore Save Error", e)
            }
        }


    // ---------------------------------------------------------
    // CREATE
    // ---------------------------------------------------------

    suspend fun createTemplate(templateGRVLocal: TemplateGRVLocal, context: Context) = withContext(Dispatchers.IO) {
        Log.i("TemplateGRVDao", "createTemplate called for ${templateGRVLocal.name}")
        val templates = getTemplates(context).toMutableList()
        // Évite les doublons
        if (templates.none { it.name == templateGRVLocal.name }) {
            templates.add(templateGRVLocal)
            saveTemplates(context, templates)
        }
    }


    // ---------------------------------------------------------
    // GET
    // ---------------------------------------------------------

    suspend fun getTemplate(name: String, context: Context): TemplateGRVLocal =
        withContext(Dispatchers.IO) {
            val templates = getTemplates(context)
            templates.firstOrNull { it.name == name } ?: TemplateGRVLocal(name = name, x = 0f, y = 0f)
        }


    // ---------------------------------------------------------
    // EXISTS
    // ---------------------------------------------------------

    suspend fun templateExists(name: String, context: Context): Boolean =
        withContext(Dispatchers.IO) {
            getTemplates(context).any { it.name == name }
        }


    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------

    suspend fun updateTemplate(templateGRVLocal: TemplateGRVLocal, context: Context) = withContext(Dispatchers.IO) {
        Log.i("TemplateGRVDao", "updateTemplate called for ${templateGRVLocal.name}")
        val templates = getTemplates(context).toMutableList()
        val index = templates.indexOfFirst { it.name == templateGRVLocal.name }
        if (index != -1) {
            templates[index] = templateGRVLocal
            saveTemplates(context, templates)
        } else {
            Log.w("TemplateGRVDao", "updateTemplate - template NOT FOUND in list, creating instead")
            templates.add(templateGRVLocal)
            saveTemplates(context, templates)
        }
    }


    // ---------------------------------------------------------
    // GET ALL PDF
    // ---------------------------------------------------------

    suspend fun getAllTemplates(context: Context): List<TemplateGRVLocal> = withContext(Dispatchers.IO) {
        val result = mutableListOf<TemplateGRVLocal>()

        // 1. SCAN VIA DOCUMENT TREE (SAF) - La méthode la plus fiable sur Android 11+
        try {
            val sharedPrefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val treeUriString = sharedPrefs.getString("template_folder_uri", null)

            if (treeUriString != null) {
                val treeUri = Uri.parse(treeUriString)
                val documentFile = DocumentFile.fromTreeUri(context, treeUri)

                if (documentFile != null && documentFile.isDirectory) {
                    documentFile.listFiles().forEach { file ->
                        if (file.isFile && file.name?.lowercase()?.endsWith(".pdf") == true) {
                            result.add(TemplateGRVLocal(name = file.name!!.substringBeforeLast("."), x = 0f, y = 0f))
                        }
                    }
                    Log.d("TemplateGRVDao", "SAF Scan found: ${result.size} PDFs")
                }
            }
        } catch (e: Exception) {
            Log.e("TemplateGRVDao", "SAF Scan Error", e)
        }

        // 2. SCAN DIRECT (Anciennes versions ou dossiers créés par l'app)
        if (result.isEmpty()) {
            try {
                val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val grvDir = File(downloadDir, FOLDER_NAME)
                if (grvDir.exists() && grvDir.isDirectory) {
                    grvDir.listFiles()?.forEach { file ->
                        if (file.isFile && file.name.lowercase().endsWith(".pdf")) {
                            result.add(TemplateGRVLocal(name = file.nameWithoutExtension, x = 0f, y = 0f))
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("TemplateGRVDao", "Direct Scan Error", e)
            }
        }

        // 3. MEDIASTORE (Scan global)
        if (result.isEmpty()) {
            try {
                val collection = MediaStore.Files.getContentUri("external")
                val projection = arrayOf(MediaStore.Files.FileColumns.DISPLAY_NAME)
                val selection = "${MediaStore.Files.FileColumns.RELATIVE_PATH} LIKE ?"
                val selectionArgs = arrayOf("%$FOLDER_NAME%")

                context.contentResolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
                    val nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)
                    while (cursor.moveToNext()) {
                        val fileName = cursor.getString(nameIndex)
                        if (fileName.lowercase().endsWith(".pdf")) {
                            val name = fileName.substringBeforeLast(".")
                            if (result.none { it.name == name }) {
                                result.add(TemplateGRVLocal(name = name, x = 0f, y = 0f))
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("TemplateGRVDao", "MediaStore Error", e)
            }
        }

        result.distinctBy { it.name }.sortedBy { it.name }.also {
            Log.d("TemplateGRVDao", "Final templates found: ${it.size}")
        }
    }

}
