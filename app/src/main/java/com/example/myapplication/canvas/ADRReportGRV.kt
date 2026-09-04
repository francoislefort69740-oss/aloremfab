package com.example.myapplication.canvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.pdf.PdfDocument
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.model.StepControlGRV
import java.io.File
import java.io.FileOutputStream

const val CODE_10TCG_970_551 = "10TCG_970_551"
const val CODE_10TCG_910_484 = "10TCG_910_484"
const val CODE_05AB_500_275 = "05AB_500_275"

class ADRReportGRV : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()
    private var heightPx : Float = 0F
    private var widthPx : Float = 0F

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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        getDrawing(canvas = canvas, paint = paint)
    }

    fun getDrawing(canvas: Canvas, paint: Paint){
        reportName?.let { report ->
            if (heightPx == 0F) heightPx = height.toFloat()
            if (widthPx == 0F) widthPx = width.toFloat()

            val unitY: Float = if(heightPx != 0F) heightPx* 1/100 else height.toFloat() * 1/100
            val unitX: Float = if (widthPx != 0F) widthPx * 1/100 else width.toFloat() * 1/100

            if (backgroundBitmap == null) {
                backgroundBitmap = getPdfPageAsBitmap(context = context, assetName = "${report}.pdf", pageIndex = 0)
            }

            backgroundBitmap?.let { backBitmap ->
                val bitmapPaint = Paint().apply {
                    isFilterBitmap = true
                    isAntiAlias = true
                }
                canvas.drawBitmap(backBitmap, null, RectF(0f, 0f, widthPx, heightPx), bitmapPaint)
            }

            definePaintStroke(R.color.black, unitY)

            canvas.drawText(numAlorem,unitX* 13.2F, unitY* getUnityNumberLoca(name = report), paint)

            //   canvas.drawLine(unitX*13, unitY*54, unitX*13, unitY*55, paint)
            // canvas.drawRect(unitX*13, unitY*53.5F, unitX*49, unitY*55, paint)
        }
    }

    fun generatePdf(file: File) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        widthPx = pageInfo.pageWidth.toFloat()
        heightPx = pageInfo.pageHeight.toFloat()

        getDrawing(page.canvas, paint)

        pdfDocument.finishPage(page)
        
        FileOutputStream(file).use { outputStream ->
            pdfDocument.writeTo(outputStream)
        }

        pdfDocument.close()
    }

    fun setNameReport(name: String, numero: String): String? {
        reportName = findReportByName(name = name)
        numAlorem = numero
        reportName?.let { invalidate() }
        return reportName
    }

    private fun getUnityNumberLoca(name: String?): Float = when (name) {
        CODE_10TCG_970_551 -> 8.25F
        CODE_10TCG_910_484 -> 7.65F
        CODE_05AB_500_275 -> 8.25F
        else -> 0F
    }

    private fun findReportByName(name: String): String? {
        return when (name) {
            CODE_10TCG_970_551 -> "10TCG_970_551"
            CODE_10TCG_910_484 -> "10TCG_910_484"
            CODE_05AB_500_275 -> "05AB_500_275"
            else -> null
        }
    }
}