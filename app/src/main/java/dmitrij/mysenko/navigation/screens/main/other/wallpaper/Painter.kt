package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import android.graphics.*

class Painter {

    private val spaceColor = Color.BLACK
    private val paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    fun draw(canvas: Canvas, stars: List<Star>) {
        canvas.drawColor(spaceColor)
        for (star in stars) {
            paint.color = star.actualColor
            canvas.drawOval(
                convertRectFToPixel(star.x2d, star.y2d, star.size, canvas.width, canvas.height),
                paint
            )
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