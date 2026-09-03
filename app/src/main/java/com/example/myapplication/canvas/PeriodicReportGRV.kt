package com.example.myapplication.canvas

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.model.StepControlGRV
import java.io.File
import java.io.FileOutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PeriodicReportGRV : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()
    private val path = Path()
    private var heightPx : Float = 0F
    private var widthPx : Float = 0F

    private var reportData : StepControlGRV.StepControlGRVAll?  = null

    private val margin: Float = 1.45F
    var section: Float = 0F

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        getDrawing(canvas = canvas, paint = paint)
    }

    fun getDrawing(canvas: Canvas, paint: Paint){
        if (heightPx == 0F) heightPx = height.toFloat()
        if (widthPx == 0F) widthPx = width.toFloat()

        val unitY: Float = if(heightPx != 0F) heightPx* 1/100 else height.toFloat() * 1/100
        val unitX: Float = if (widthPx != 0F) widthPx * 1/100 else width.toFloat() * 1/100

        createHeader(canvas = canvas, unitX = unitX, unitY = unitY)
        createFirstSection(canvas = canvas, unitX = unitX, unitY = unitY, startY = 12F)
        createSecondSection(canvas = canvas, unitX = unitX, unitY = unitY, startY = 16F)
        var nextSection = 16 + section + margin / 2
        createThirdSection(canvas = canvas, unitX = unitX, unitY = unitY, startY = nextSection)
        nextSection += section + margin / 2
        createFourthSection(canvas = canvas, unitX = unitX, unitY = unitY, startY = nextSection)
        nextSection += section + margin / 2
        createFiveSection(canvas = canvas, unitX = unitX, unitY = unitY, startY = nextSection)
    }

    // PAINTER

    private fun definePaintFill(mColor: Int, unitY: Float): Paint = paint.apply {
        textSize = Math.round(unitY * 1.5F).toFloat()
        isAntiAlias = true
        isLinearText = true
        shader = null
        color = ContextCompat.getColor(context, mColor)
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = unitY * 0.5F
    }

    private fun definePaintStroke(mColor: Int, unitY: Float): Paint = paint.apply {
        textSize = Math.round(unitY * 1.8F).toFloat()
        isAntiAlias = true
        isLinearText = true
        shader = null
        color = ContextCompat.getColor(context, mColor)
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 1F
    }

    private fun defineTextTitle(unitY: Float): Paint = paint.apply {
        textSize = Math.round(unitY * 1.5F).toFloat()
        typeface = Typeface.DEFAULT_BOLD
        isAntiAlias = true
        isLinearText = true
        shader = null
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 1F
    }

    private fun defineGraph(unitY: Float): Paint = paint.apply {
        color = Color.BLACK
        paint.strokeWidth = unitY * 0.1F
        style = Paint.Style.FILL
    }

    private fun defineTextDefault(unitY: Float): Paint = paint.apply {
        textSize = Math.round(unitY * 1.2F).toFloat()
        typeface = Typeface.DEFAULT
        isAntiAlias = true
        isLinearText = true
        shader = null
        style = Paint.Style.FILL
        strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 1F
    }


    /**
     *  GEOMETRY
     */

    private fun drawRect(canvas: Canvas, top: Float, left: Float, bottom: Float, right: Float) =
        canvas.drawRect(left, top, right, bottom, paint)

    private fun drawText(canvas: Canvas, text: String, data: String?, x: Float, y: Float) {
        canvas.drawText(text, x, y, paint)
        canvas.drawText(data ?: "", x * 10, y, paint)
        section += margin
    }

    private fun drawLineGraph(canvas: Canvas, unitX: Float, unitY: Float, floatY: Float) {
        path.moveTo(unitX * 85, floatY)
        path.lineTo(unitX * 86, floatY)
        path.lineTo(unitX * 86, floatY + unitY/2)
        path.lineTo(unitX * 87, floatY + unitY/2)
        path.lineTo(unitX * 85.5F, floatY + unitY)
        path.lineTo(unitX * 84, floatY + unitY/2)
        path.lineTo(unitX * 85, floatY + unitY/2)
        path.close()

        canvas.drawPath(path, paint)
    }

    /**
     *  ELEMENT
     */

    private fun createHeader(canvas: Canvas, unitX: Float, unitY: Float) {
        definePaintFill(R.color.white, unitY)
        drawRect( canvas = canvas, top = unitY * 2, left = unitX * 2, bottom = unitY * 10, right = unitX * 98)

        val logo = BitmapFactory.decodeResource(resources, R.drawable.full_logo_alorem)
        canvas.drawBitmap(logo, null, RectF(unitX * 5,unitY * 3,unitX * 36, unitY * 9), paint)

        definePaintStroke(R.color.black, unitY)

        drawRect( canvas = canvas, top = unitY * 2, left = unitX * 2, bottom = unitY * 10, right = unitX * 98)

        canvas.drawLine(unitX * 43, unitY * 2, unitX * 43, unitY * 10, paint)

        defineTextTitle(unitY)
        paint.textSize = Math.round(unitY * 1.8F).toFloat()
        canvas.drawText("RAPPORT DE CONTROLE PERIODIQUE", unitX * 50, unitY * 6, paint)

        defineTextDefault(unitY)
        paint.textSize = Math.round(unitY * 0.8F).toFloat()

        canvas.drawText("Ref : EN-GRV-02", unitX * 50, unitY * 8, paint)
        canvas.drawText("V.7", unitX * 68, unitY * 8, paint)
        canvas.drawText("24/07/2023", unitX * 82, unitY * 8, paint)

        defineTextDefault(unitY)
    }

    // -------------------------------------------------------------------------------------------------

    private fun createFirstSection(canvas: Canvas, unitX: Float, unitY: Float, startY: Float) {
        section = 0F
        drawText(canvas = canvas, text = "N° de rapport : CGRV-AL-", x = unitX * 5, data = "", y = unitY * (startY + section))
        drawText(canvas = canvas, text = "N° Commande ALOREM : ${reportData?.step0?.reportNumber ?:""}", x = unitX * 5, data = "", y = unitY * (startY + section))
        drawText(canvas = canvas, text = "Type de contrôle : 5 ans", x = unitX * 5, data = "", y = unitY * (startY + section))
    }

    // -------------------------------------------------------------------------------------------------

    private fun createSecondSection(canvas: Canvas, unitX: Float, unitY: Float, startY: Float) {
        section = 5F
        definePaintFill(R.color.light_grey, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+3), right = unitX * 98)
        definePaintStroke(R.color.black, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+3), right = unitX * 98)

        defineTextTitle(unitY)
        canvas.drawText(TITLE_IDENTIFICATION, unitX * 10, unitY * (startY+2F), paint)
        defineTextDefault(unitY)

        drawText(canvas = canvas, text = SITE_DE_CONTROLE, data = "",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = FABRICANT, x = unitX * 5, data = "", y = unitY * (startY + section))
        drawText(canvas = canvas, text = PROPRIETAIRE, data = "", x = unitX * 5, y = unitY * (startY + section))
        drawText(canvas = canvas, text = NUM_SERIE_ALO, data = reportData?.step0?.serialNumberAlorem.toString(), x = unitX * 5, y = unitY * (startY + section))
        drawText(canvas = canvas, text = NUM_SERIE_CLIENT, data = reportData?.step0?.customerSerialNumber.toString(), x = unitX * 5, y = unitY * (startY + section))
        drawText(canvas = canvas, text = MARQUE_PRINCIPALE, data =  reportData?.step2?.marquePrincipale, x = unitX * 5, y = unitY * (startY + section))
        drawText(canvas = canvas, text = REFERENCE, data = reportData?.step0?.type.toString(), x = unitX * 5, y = unitY * (startY + section))
        drawText(canvas = canvas, text = CAPACITE, data = reportData?.step2?.capacity20.toString(), x = unitX * 5, y = unitY * (startY + section))
        drawText(canvas = canvas, text = TARE, data = reportData?.step2?.tare.toString(), x = unitX * 5, y = unitY * (startY + section))

        drawText(canvas = canvas, text = GROSS_MASS, data = reportData?.step2?.grossMass.toString(), x = unitX * 5, y = unitY * (startY + section))
        canvas.drawText(MARQUES_ADDITION, unitX * 80,unitY * (startY + section), paint)

        drawText(canvas = canvas, text = TYPE_MATERIAU, data = reportData?.step2?.material, x = unitX * 5, y = unitY * (startY + section))
        canvas.drawText(reportData?.step2?.weightStacking.toString() + " " + KG, unitX * 80,unitY * (startY + section), paint)

        drawText(canvas = canvas, text = EPAISSEUR_PAROI, data = reportData?.step2?.shellThickness.toString(), x = unitX * 5, y = unitY * (startY + section))

        // GRAPH
        defineGraph(unitY)
        drawLineGraph(canvas = canvas, unitX = unitX, unitY = unitY, floatY = unitY * (startY + section))
        drawRect(canvas = canvas,
            top = unitY * (startY + section) + section/2,
            left = unitX * 83,
            bottom = unitY * (startY + section) + (section * 1.25F)/2,
            right = unitX * 88
        )
        drawRect(canvas = canvas,
            top = unitY * (startY + section) + (section * 1.5F)/2,
            left = unitX * 83,
            bottom = unitY * (startY + section) + (section * 3)/2,
            right = unitX * 88
        )

        defineTextDefault(unitY)
        drawText(canvas = canvas, text = DATE_FAB, data = reportData?.step2?.fabricationDate, x = unitX * 5, y = unitY * (startY + section))
        drawText(canvas = canvas, text = PICTO_GERBAGE, data =  affirmation(reportData?.step2?.pictogramStacking), x = unitX * 5, y = unitY * (startY + section))
        drawText(canvas = canvas, text = PRESSION_MAX, data = "", x = unitX * 5, y = unitY * (startY + section))

        definePaintStroke(R.color.black, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+ section), right = unitX * 98)
    }

    // -------------------------------------------------------------------------------------------------

    private fun createThirdSection(canvas: Canvas, unitX: Float, unitY: Float, startY: Float) {
        section = 5F
        definePaintFill(R.color.light_grey, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+3), right = unitX * 98)
        definePaintStroke(R.color.black, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+3), right = unitX * 98)

        defineTextTitle(unitY)
        canvas.drawText(TITLE_INSPECTION_REGLEMENTAIRE, unitX * 10, unitY * (startY+2F), paint)
        defineTextDefault(unitY)

        drawText(canvas = canvas, text = INSPECTEUR, data = "", unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = DATE_INSPECTION, data = reportData?.step6?.etancheiteDate1, unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = FONCTIONNEMENT_EQUIPEMENT, data = "", unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = ETAT_EXTERIEUR, data = getResultConformityOutside(
            listOf(
                reportData?.step3?.bottomRetentionFace ?: 0,
                reportData?.step3?.bottomRetentionRight ?: 0,
                reportData?.step3?.bottomRetentionLeft ?: 0,
                reportData?.step3?.bottomRetentionBehind ?: 0,
                reportData?.step3?.upperRetention ?: 0,
                reportData?.step3?.liftingRings ?: 0,
                reportData?.step3?.forkliftPass ?: 0,
                reportData?.step3?.dashboard ?: 0
            )
        ),
            unitX * 5,
            unitY * (startY + section)
        )
        drawText(canvas = canvas, text = ETAT_INTERIEUR, data = getResultConformityInside(reportData?.step4),
            unitX * 5,
            unitY * (startY + section)
        )
        drawText(canvas = canvas, text = EPAISSEUR_PAROIS, data = reportData?.step5?.let { getResultConformityThickNess(it) },
            unitX * 5,
            unitY * (startY + section)
        )
        drawText(canvas = canvas, text = MARQUES_REGLEMENTAIRE, data = "", unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = CONFORMITE_MODELE, data = "",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = INSPECTION_REG, data = "",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = OBSERVATION, data = "",unitX * 5, unitY * (startY + section))

        definePaintStroke(R.color.black, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+ section), right = unitX * 98)
    }

    // -------------------------------------------------------------------------------------------------

    private fun createFourthSection(canvas: Canvas, unitX: Float, unitY: Float, startY: Float) {
        section = 5F
        definePaintFill(R.color.light_grey, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+3), right = unitX * 98)
        definePaintStroke(R.color.black, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+3), right = unitX * 98)

        defineTextTitle(unitY)
        canvas.drawText(TITLE_EPREUVE_ETANCHEITE, unitX * 10, unitY * (startY+2F), paint)
        defineTextDefault(unitY)

        drawText(canvas = canvas, text = LIEU_EPREUVE, data = "",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = PERSONNE_EPREUVE, data = reportData?.name, unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = DATE_EPREUVE, data = reportData?.step6?.etancheiteDate1,unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = PRESSION_APPLIQUEE, data = reportData?.step6?.etancheiteBar1.toString() + " bar",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = DUREE_EPREUVE, data = "10 minutes",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = RESULTAT_EPREUVE, data = conformity(reportData?.step6?.etancheiteConforme1),unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = CONCLUSION_EPREUVE, data = conformity(reportData?.step6?.etancheiteConforme1),unitX * 5, unitY * (startY + section))

        definePaintStroke(R.color.black, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+ section), right = unitX * 98)
    }

    private fun createFiveSection(canvas: Canvas, unitX: Float, unitY: Float, startY: Float) {
        section = 5F
        definePaintFill(R.color.light_grey, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+3), right = unitX * 98)
        definePaintStroke(R.color.black, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * (startY+3), right = unitX * 98)

        defineTextTitle(unitY)
        canvas.drawText(TITLE_CONCLUSION, unitX * 10, unitY * (startY+2F), paint)
        defineTextDefault(unitY)

        drawText(canvas = canvas, text = RESULTAT, data = "",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = DATE_PROCHAIN_CONTROLE, data = getNextDateControl(reportData?.step6?.etancheiteDate1 ?: ""),unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = FAIT_A, data = "BEYNOST",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = DATE_RAPPORT, data = reportData?.step6?.etancheiteDate1,unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = SIGNATURE_RAPPORT, data = reportData?.name,unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = FONCTION, data = "Agent chargé des contrôles",unitX * 5, unitY * (startY + section))
        drawText(canvas = canvas, text = SIGNATURE, data = "",unitX * 5, unitY * (startY + section))

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.signature)
        val dst = RectF(unitX * 50, unitY * (startY + section - margin), unitX * 70, unitY * 99.2F)
        canvas.drawBitmap(bitmap, null, dst, paint)
        definePaintStroke(R.color.black, unitY)
        drawRect( canvas = canvas, top = unitY * startY, left = unitX * 2, bottom = unitY * 99.5F, right = unitX * 98)
    }

    // -----------------------------------------------------------------------------------------------

    fun generatePdf(file: File) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        widthPx = pageInfo.pageWidth.toFloat()
        heightPx = pageInfo.pageHeight.toFloat()

        getDrawing(page.canvas, paint)

        pdfDocument.finishPage(page)
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()
    }

    fun setDataIntoReportTemplate(reportData : StepControlGRV.StepControlGRVAll) {
        this.reportData = reportData
        invalidate()
    }

    fun conformity(result : Boolean?) : String = if (result == true) "CONFORME" else "NON CONFORME"

    fun affirmation(result : Boolean?) : String = if (result == true) "OUI" else "NON"

}