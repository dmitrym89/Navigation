package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import android.graphics.*
import android.util.Log

class Painter {

    private val spaceColor = Color.BLACK
    private val paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    fun draw(canvas: Canvas, stars: List<Star>, objs: List<Obj>) {
        val width = canvas.width
        val height = canvas.height

        canvas.drawColor(spaceColor)
        for (star in stars) {
            paint.color = star.actualColor
            canvas.drawOval(
                convertRectFToPixel(star.x2d, star.y2d, star.actualSize, canvas.width, canvas.height),
                paint
            )
        }
        for(obj in objs){
            //Log.e("AA", "obj = $obj")
            if(obj.drawable!=null){
                obj.drawable.setBounds(0,0,obj.actualSize.toInt(), obj.actualSize.toInt())
                canvas.save()
                canvas.translate(width/2 + width*obj.x2d/2, height/2 + height*obj.y2d/2)
                obj.drawable.draw(canvas)
                canvas.restore()
            }
        }
    }

    private fun convertRectFToPixel(
        x2d: Float, y2d: Float, size: Float, width: Int, height: Int
    ): RectF {
        val convertedX = width/2 + width*x2d/2
        val convertedY = height/2 + height*y2d/2
        val rect = RectF(
            convertedX - size,
            convertedY - size,
            convertedX + size,
            convertedY + size,
        )
        return rect
    }
}