package com.example.myapplication.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.utils.NUMERO

class FloatingNameGRVReport : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private val paint = Paint()
    private var heightPx : Float = 0F
    private var widthPx : Float = 0F
    private var floatX : Float = 50F
    private var floatY : Float = 50F
    private var scale : FloatingScaleNameGRV = FloatingScaleNameGRV.SCALE_FIVE

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

    fun getDrawing(canvas: Canvas, paint: Paint){
        if (heightPx == 0F) heightPx = height.toFloat()
        if (widthPx == 0F) widthPx = width.toFloat()

        val unitY = heightPx / 100
        val unitX = widthPx / 100

        definePaintStrokeFillRed(R.color.primary_color, unitY)
        canvas.drawRect(RectF(
            unitX * floatX,
            unitY * floatY - unitY,
            unitX * (6F + floatX),
            unitY * floatY + unitY / 2
        ), paint)

        definePaintStroke(R.color.black, unitY)

        Log.i("ADR_REPORT_FLOAT", "Floating : X : ${unitY*floatX} - Y : ${unitY*floatY}")
        canvas.drawText(NUMERO, unitX * floatX, unitY * floatY, paint)
    }

    fun moveFloatingName(direction: FloatingDirectionNameGRV) {
        when (direction) {
            FloatingDirectionNameGRV.LEFT -> floatX -= scale.scaleNumber
            FloatingDirectionNameGRV.RIGHT -> floatX += scale.scaleNumber
            FloatingDirectionNameGRV.UP -> floatY -= scale.scaleNumber
            FloatingDirectionNameGRV.DOWN -> floatY += scale.scaleNumber
        }
        invalidate()
    }

    fun setLocalisation(x: Float, y: Float) {
        floatX = x
        floatY = y
        invalidate()
    }

    fun changeScale(): Float {
        scale = scale.next()
        return scale.scaleNumber
    }

    fun getLocalisation(): Pair<Float, Float> = floatX to floatY
}

enum class FloatingDirectionNameGRV {
    LEFT,
    RIGHT,
    UP,
    DOWN
}

enum class FloatingScaleNameGRV(val scaleNumber: Float) {
    SCALE_FIVE(5F),
    SCALE_ONE(1F),
    SCALE_ZERO_FIVE(0.5F),
    SCALE_ZERO_ONE(0.1F);

    fun next(): FloatingScaleNameGRV = entries[(ordinal + 1) % entries.size]

}
