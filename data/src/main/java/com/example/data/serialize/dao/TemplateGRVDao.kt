package com.example.data.serialize.dao

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import com.example.data.serialize.model.TemplateGRVLocal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.indexOfFirst

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
        val contentResolver = context.contentResolver
        val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Downloads._ID,
            MediaStore.Downloads.DISPLAY_NAME
        )
        val selection = "${MediaStore.Downloads.DISPLAY_NAME} = ? AND ${MediaStore.Downloads.RELATIVE_PATH} IN (?, ?)"
        val selectionArgs = arrayOf(JSON_FILE_NAME, "Download/$FOLDER_NAME/", "Download/$FOLDER_NAME")

        contentResolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
            if (!cursor.moveToFirst()) { return@withContext emptyList() }
            
            // ... (rest of the code)

                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Downloads._ID))

                val uri = android.content.ContentUris.withAppendedId(collection, id)

                contentResolver.openInputStream(uri)?.use { input ->
                    val json = input.bufferedReader().use { it.readText() }

                    if (json.isBlank()) { emptyList()
                    } else { val type = object : TypeToken<List<TemplateGRVLocal>>() {}.type
                        gson.fromJson(json, type)
                    }
                } ?: emptyList()
            } ?: emptyList()
        }


    // ---------------------------------------------------------
    // Sauvegarde du JSON
    // ---------------------------------------------------------

    private suspend fun saveTemplates(context: Context, templates: List<TemplateGRVLocal>) =
        withContext(Dispatchers.IO) {
            val contentResolver = context.contentResolver
            val collection = MediaStore.Downloads.EXTERNAL_CONTENT_URI
            val json = gson.toJson(templates)
            val projection = arrayOf(MediaStore.Downloads._ID)
            val selection = "${MediaStore.Downloads.DISPLAY_NAME} = ? AND ${MediaStore.Downloads.RELATIVE_PATH} IN (?, ?)"
            val selectionArgs = arrayOf(JSON_FILE_NAME, "Download/$FOLDER_NAME/", "Download/$FOLDER_NAME")

            var uri: android.net.Uri? = null
            contentResolver.query(collection, projection, selection, selectionArgs, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Downloads._ID))
                    uri = android.content.ContentUris.withAppendedId(collection, id)
                }
            }

            // Le fichier n'existe pas encore
            if (uri == null) {
                val values = ContentValues().apply {
                    put(MediaStore.Downloads.DISPLAY_NAME, JSON_FILE_NAME)
                    put(MediaStore.Downloads.MIME_TYPE, "text/plain")
                    put(MediaStore.Downloads.RELATIVE_PATH, "Download/$FOLDER_NAME/")
                }
                uri = contentResolver.insert(collection, values)
            }

            uri?.let {
                contentResolver.openOutputStream(it, "wt")?.use { output ->
                    output.write(json.toByteArray(Charsets.UTF_8))
                }
            }
        }


    // ---------------------------------------------------------
    // CREATE
    // ---------------------------------------------------------

    suspend fun createTemplate(templateGRVLocal: TemplateGRVLocal, context: Context) = withContext(Dispatchers.IO) {
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
        val templates = getTemplates(context).toMutableList()
        val index = templates.indexOfFirst { it.name == templateGRVLocal.name }
        if (index != -1) {
            templates[index] = templateGRVLocal
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
                val treeUri = android.net.Uri.parse(treeUriString)
                val documentFile = androidx.documentfile.provider.DocumentFile.fromTreeUri(context, treeUri)
                
                if (documentFile != null && documentFile.isDirectory) {
                    documentFile.listFiles().forEach { file ->
                        if (file.isFile && file.name?.lowercase()?.endsWith(".pdf") == true) {
                            result.add(TemplateGRVLocal(name = file.name!!.substringBeforeLast("."), x = 0f, y = 0f))
                        }
                    }
                    android.util.Log.d("TemplateGRVDao", "SAF Scan found: ${result.size} PDFs")
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("TemplateGRVDao", "SAF Scan Error", e)
        }

        // 2. SCAN DIRECT (Anciennes versions ou dossiers créés par l'app)
        if (result.isEmpty()) {
            try {
                val downloadDir = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_DOWNLOADS)
                val grvDir = java.io.File(downloadDir, FOLDER_NAME)
                if (grvDir.exists() && grvDir.isDirectory) {
                    grvDir.listFiles()?.forEach { file ->
                        if (file.isFile && file.name.lowercase().endsWith(".pdf")) {
                            result.add(TemplateGRVLocal(name = file.nameWithoutExtension, x = 0f, y = 0f))
                        }
                    }
                }
            } catch (e: Exception) {
                android.util.Log.e("TemplateGRVDao", "Direct Scan Error", e)
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
                android.util.Log.e("TemplateGRVDao", "MediaStore Error", e)
            }
        }

        result.distinctBy { it.name }.sortedBy { it.name }.also {
            android.util.Log.d("TemplateGRVDao", "Final templates found: ${it.size}")
        }
    }

}