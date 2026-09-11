package com.example.myapplication.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.util.Log
import com.example.myapplication.model.StepControlGRV
import androidx.documentfile.provider.DocumentFile
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.core.graphics.createBitmap
import java.io.FileNotFoundException

fun getResultConformityOutside(exterieur: List<Int>): String {
    var result = "CONFORME"
    for (i in exterieur) {
        if (i != 1) { result = "NON CONFORME" }
    }
    return result
}

fun getResultConformityInside(stepInside: StepControlGRV.Step4ControlGRV?): String {
    var resultat = "CONFORME"
    if (stepInside?.internalNA ?: false) {
        resultat = "N/A"
    }
    if (stepInside?.internalOK == false || stepInside?.internalObjectInside ?: false || stepInside?.internalPollution ?: false ) {
        resultat = "NON CONFORME"
    }
    if (stepInside == null) resultat = "NON CONFORME"
    return resultat
}

fun getResultConformityThickNess(step: StepControlGRV.Step5ControlGRV): String {

    val minimums = listOf(step.epaisseurMinSideFront, step.epaisseurMinSideBack, step.epaisseurMinSideRight, step.epaisseurMinSideLeft)
    val controls = listOf(step.epaisseurMinSideBack to
            listOf(step.epaisseurSideBackResult1, step.epaisseurSideBackResult2, step.epaisseurSideBackResult3, step.epaisseurSideBackResult4, step.epaisseurSideBackResult5),

        step.epaisseurMinSideFront to
                listOf(step.epaisseurSideFrontResult1, step.epaisseurSideFrontResult2, step.epaisseurSideFrontResult3, step.epaisseurSideFrontResult4, step.epaisseurSideFrontResult5),

        step.epaisseurMinSideLeft to
                listOf(step.epaisseurSideLeftResult1, step.epaisseurSideLeftResult2, step.epaisseurSideLeftResult3, step.epaisseurSideLeftResult4, step.epaisseurSideLeftResult5),

        step.epaisseurMinSideRight to
                listOf(step.epaisseurSideRightResult1, step.epaisseurSideRightResult2, step.epaisseurSideRightResult3, step.epaisseurSideRightResult4, step.epaisseurSideRightResult5)
    )

    return when {
        step.epaisseurNA -> "N/A"
        minimums.any { it == 0 } -> "NON CONFORME"
        controls.any { (minimum, results) -> minimum != null && results.filterNotNull().any { it < minimum } } -> "NON CONFORME"
        else -> "CONFORME"
    }
}

fun getNextDateControl(d: String): String {
    return try {
        val date = LocalDate.parse(
            d,
            DateTimeFormatter.ofPattern("dd/MM/yyyy")
        )

        val result = date.plusMonths(30).minusDays(1)
        return result.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

    } catch (e: Exception) {
        "A DEFINIR"
    }
}

fun getPdfPageAsBitmap(context: Context, fileName: String, pageIndex: Int = 0): Bitmap {
    val cleanFileName = fileName.trim()
    val appContext = context.applicationContext
    val sharedPrefs = appContext.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val treeUriString = sharedPrefs.getString("template_folder_uri", null)

    val fileDescriptor: ParcelFileDescriptor? = try {
        if (treeUriString != null) {
            val treeUri = android.net.Uri.parse(treeUriString)

            // On vérifie les permissions de manière plus souple (comparaison de chaines)
            val hasPermission = appContext.contentResolver.persistedUriPermissions.any {
                it.uri.toString() == treeUri.toString() && it.isReadPermission
            }
            android.util.Log.d("canvasUtils", "Has persisted permission: $hasPermission")

            val pickedDir = DocumentFile.fromTreeUri(appContext, treeUri)

            // On cherche le fichier (tentative insensible à la casse si échec)
            var file = pickedDir?.findFile(cleanFileName)
            if (file == null) {
                android.util.Log.w("canvasUtils", "File '$cleanFileName' not found directly, scanning folder...")
                file = pickedDir?.listFiles()?.find { it.name?.equals(cleanFileName, ignoreCase = true) == true }
            }

            if (file != null) {
                android.util.Log.d("canvasUtils", "File found via SAF: ${file.uri}")
                appContext.contentResolver.openFileDescriptor(file.uri, "r")
            } else {
                android.util.Log.e("canvasUtils", "File '$cleanFileName' NOT FOUND in selected SAF directory")
                null
            }
        } else {
            android.util.Log.e("canvasUtils", "No SAF directory selected! Background cannot be loaded.")
            null
        }
    } catch (e: Exception) {
        android.util.Log.e("canvasUtils", "CRITICAL Error opening via SAF: $cleanFileName", e)
        null
    }

    if (fileDescriptor == null) {
        return try {
            val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val externalFile = File(downloadDir, "GRVTemplate/$cleanFileName")
            if (externalFile.exists()) {
                val fd = ParcelFileDescriptor.open(externalFile, ParcelFileDescriptor.MODE_READ_ONLY)
                renderPdfToBitmap(fd, pageIndex)
            } else {
                throw FileNotFoundException("Fichier non trouvé sur le disque : ${externalFile.absolutePath}")
            }
        } catch (e: Exception) {
            throw FileNotFoundException("Impossible de lire le PDF: $cleanFileName")
        }
    }

    return renderPdfToBitmap(fileDescriptor, pageIndex)
}

fun getPdfPageCount(context: Context, fileName: String): Int {

    val cleanFileName = fileName.trim()
    val appContext = context.applicationContext

    val sharedPrefs = appContext.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val treeUriString = sharedPrefs.getString("template_folder_uri", null)

    val fileDescriptor: ParcelFileDescriptor? = try {

        if (treeUriString != null) {
            val treeUri = Uri.parse(treeUriString)
            val pickedDir = DocumentFile.fromTreeUri(appContext, treeUri)
            var file = pickedDir?.findFile(cleanFileName)

            if (file == null) {
                file = pickedDir?.listFiles()?.find { it.name?.equals(cleanFileName, ignoreCase = true) == true }
            }

            file?.let { appContext.contentResolver.openFileDescriptor(it.uri, "r") }

        } else {
            null
        }

    } catch (e: Exception) {
        Log.e("ADRReportGRV", "Erreur ouverture PDF", e)
        null
    }

    if (fileDescriptor != null) {

        val renderer = PdfRenderer(fileDescriptor)
        val pageCount = renderer.pageCount

        renderer.close()
        fileDescriptor.close()

        return pageCount
    }

    val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

    val externalFile = File(downloadDir, "GRVTemplate/$cleanFileName")

    if (!externalFile.exists()) {
        throw FileNotFoundException("PDF introuvable : $cleanFileName")
    }

    val fd = ParcelFileDescriptor.open(externalFile, ParcelFileDescriptor.MODE_READ_ONLY)

    val renderer = PdfRenderer(fd)
    val pageCount = renderer.pageCount

    renderer.close()
    fd.close()

    return pageCount
}

private fun renderPdfToBitmap(fileDescriptor: ParcelFileDescriptor, pageIndex: Int): Bitmap {
    val pdfRenderer = PdfRenderer(fileDescriptor)
    val page = pdfRenderer.openPage(pageIndex)
    val scale = 4
    val bitmap = createBitmap(page.width * scale, page.height * scale)
    bitmap.eraseColor(android.graphics.Color.WHITE)
    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_PRINT)
    page.close()
    pdfRenderer.close()
    fileDescriptor.close()
    return bitmap
}



