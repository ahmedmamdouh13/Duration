package com.ahmedmamdouh13.duration.custom
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat.getColor

class StraightLineView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

   private var color2: Int = getColor(context, android.R.color.white)
  private  var mScaleY = 1f
  private  var mScaleY2 = 0.5f
  private var paint = Paint()
  private var paint2 = Paint()
    init {
        paint.isAntiAlias = true
        paint.color = getColor(context, android.R.color.white)
        paint.strokeWidth = 15f
        paint2.isAntiAlias = true
        paint2.color = color2
        paint2.strokeWidth = 20f
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint2.color = color2
        canvas!!.translate((width / 2).toFloat(), 0f)

        canvas.drawLine(0f, 0f, 0f, mScaleY*height, paint)
//        drawDottedLine(canvas,mScaleY*height)
//        canvas.drawLine(0f, 0f, 0f, mScaleY2*height, paint2)

    }

    private fun drawDottedLine(canvas: Canvas, fl: Float) {
        var cnt = 0f
        var space = 0f
        while (cnt < fl){
            canvas.translate(0f, space)
//            canvas.drawLine(0f, 0f, 0f, 20f, paint2)
            canvas.drawCircle(0f,20f,5f,paint2)
            space+=5
            cnt+=5
        }
    }

    fun setScaleY2(float: Float){
        mScaleY2 = float
        invalidate()
    }
    fun setProgressColor(color: Int){
        color2 = color
        invalidate()
    }
}