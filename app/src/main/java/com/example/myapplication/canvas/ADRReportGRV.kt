package com.example.myapplication.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.pdf.PdfDocument
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.utils.NUMERO
import java.io.File
import java.io.FileOutputStream

class ADRReportGRV : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()
    private var heightPx : Float = 0F
    private var widthPx : Float = 0F
    private var floatX : Float = 50F
    private var floatY : Float = 50F

    private var reportName : String?  = null
    private var numAlorem: String = ""

    private var backgroundBitmap: Bitmap? = null

    private fun definePaintStroke(mColor: Int, unitY: Float): Paint = paint.apply {
        textSize = Math.round(unitY * 0.9F).toFloat()
        isAntiAlias = true
        isLinearText = true
        shader = null
        color = ContextCompat.getColor(context, mColor)
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 1F
    }

    private fun definePaintStrokeFillRed(mColor: Int, unitY: Float): Paint = paint.apply {
        textSize = Math.round(unitY * 0.9F).toFloat()
        isAntiAlias = true
        isLinearText = true
        shader = null
        color = ContextCompat.getColor(context, mColor)
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 1F
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        getDrawing(canvas = canvas, paint = paint)
    }

    fun getDrawing(canvas: Canvas, paint: Paint, pageIndex: Int = 0) {
        canvas.drawColor(android.graphics.Color.WHITE)

        reportName?.let { report ->
            if (heightPx == 0F) heightPx = height.toFloat()
            if (widthPx == 0F) widthPx = width.toFloat()

            val unitY: Float = if (heightPx != 0F) heightPx * 1 / 100 else height.toFloat() * 1 / 100
            val unitX: Float = if (widthPx != 0F) widthPx * 1 / 100 else width.toFloat() * 1 / 100

            val pdfFileName = if (report.lowercase().endsWith(".pdf")) report else "$report.pdf"

            val backgroundBitmap = try {
                getPdfPageAsBitmap(context, pdfFileName, pageIndex)
            } catch (e: Exception) {
                Log.e("ADRReportGRV", "CRITICAL: PDF page $pageIndex Load failed", e)
                null
            }

            backgroundBitmap?.let { bitmap ->
                canvas.drawBitmap(bitmap, null, RectF(0f, 0f, widthPx, heightPx), null)
            } ?: run {
                paint.color = android.graphics.Color.RED
                paint.style = Paint.Style.STROKE
                paint.strokeWidth = 5f
                canvas.drawRect(10f, 10f, widthPx - 10f, heightPx - 10f, paint)
            }

            if (pageIndex == 0) {
                definePaintStroke(R.color.black, unitY)

                Log.i("ADR_REPORT_FLOAT", "Report : X : ${unitY * floatX} - Y : ${unitY * floatY}")
                if (numAlorem != NUMERO) { canvas.drawText(numAlorem, unitX * floatX, unitY * floatY, paint)
                }
            }
        }
    }

    fun generatePdf(file: File) {
        val pdfDocument = PdfDocument()
        val pdfFileName = if (reportName?.lowercase()?.endsWith(".pdf") == true) reportName!! else "${reportName}.pdf"

        val pageCount = getPdfPageCount(context, pdfFileName)

        Log.d("ADRReportGRV", "Template : $pdfFileName - $pageCount page(s)")

        for (pageIndex in 0 until pageCount) {
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, pageIndex + 1).create()

            val page = pdfDocument.startPage(pageInfo)

            widthPx = pageInfo.pageWidth.toFloat()
            heightPx = pageInfo.pageHeight.toFloat()

            getDrawing(canvas = page.canvas, paint = paint, pageIndex = pageIndex)
            pdfDocument.finishPage(page)
        }

        try {
            FileOutputStream(file).use {
                outputStream -> pdfDocument.writeTo(outputStream)
            }
        } catch (e: Exception) {
            Log.e("ADRReportGRV", "Error writing PDF file", e)
        } finally {
            pdfDocument.close()
        }
    }

    fun setNameReport(name: String, numero: String, x: Float = 0F, y: Float = 0F): String? {
        reportName = findReportByName(name = name)
        numAlorem = numero
        floatX = x
        floatY = y
        reportName?.let {
            backgroundBitmap = null
            invalidate()
        }
        return reportName
    }

    private fun findReportByName(name: String): String? {
        if (name.isNotBlank()) {
            return name
        }
        return null
    }
}
