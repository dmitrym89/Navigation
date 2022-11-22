package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import android.graphics.*
import android.util.Log

class Painter {

    var bhBitmap: Bitmap? = null
    private val spaceColor = Color.BLACK
    private val paint = Paint()

    init {
        paint.isAntiAlias = true
    }

    fun draw(canvas: Canvas, list: List<Space>) {
        val timeStart = System.currentTimeMillis()
        val width = canvas.width
        val height = canvas.height

        canvas.drawColor(spaceColor)

        list.forEach { item ->
            when(item){
                is Star -> {
                    paint.color = item.actualColor
                    canvas.drawOval(
                        convertRectFToPixel(
                            item.x2d,
                            item.y2d,
                            item.actualSize,
                            canvas.width,
                            canvas.height
                        ),
                        paint
                    )
                }
                is Obj -> {
                    if (item.drawable != null) {
                        item.drawable.setBounds(0, 0, item.actualSize.toInt(), item.actualSize.toInt())
                        canvas.save()
                        canvas.translate(
                            width / 2 + width * item.x2d / 2,
                            height / 2 + height * item.y2d / 2
                        )
                        item.drawable.draw(canvas)
                        canvas.restore()
                    }
                }
                is SuperMassiveBlackHole -> {
//                    if (bhBitmap != null) {
//                        try {
//                            val normalizedWidthScale = bhBitmap!!.width * item.scale / width
//                            val offsetX = if (normalizedWidthScale <= 1) {
//                                0
//                            } else {
//                                ((bhBitmap!!.width * item.scale - width) / item.scale / 2).toInt()
//                            }
//
//                            val normalizedHeightScale = bhBitmap!!.height * item.scale / height
//                            val offsetY = if (normalizedHeightScale <= 1) {
//                                0
//                            } else {
//                                ((bhBitmap!!.height * item.scale - height) / item.scale / 2).toInt()
//                            }
//
//                            val matrix = Matrix()
//                            matrix.postScale(item.scale, item.scale)
//
//                            val bitmap = Bitmap.createBitmap(
//                                bhBitmap!!,
//                                offsetX,
//                                offsetY,
//                                bhBitmap!!.width - offsetX * 2,
//                                bhBitmap!!.height - offsetY * 2,
//                                matrix,
//                                true
//                            )
//                            canvas.drawBitmap(
//                                bitmap,
//                                ((width - bitmap.width) / 2).toFloat(),
//                                ((height - bitmap.height) / 2).toFloat(),
//                                paint
//                            )
//                            bitmap.recycle()
//                        } catch (e: Exception) {
//                            Log.e("WALLPAPER", e.message, e)
//                        }
//                    }
                }
            }
        }
        Log.e("AA", "draw time = ${System.currentTimeMillis() - timeStart}")
    }

    private fun convertRectFToPixel(
        x2d: Float, y2d: Float, size: Float, width: Int, height: Int
    ): RectF {
        val convertedX = width / 2 + width * x2d / 2
        val convertedY = height / 2 + height * y2d / 2
        val rect = RectF(
            convertedX - size,
            convertedY - size,
            convertedX + size,
            convertedY + size,
        )
        return rect
    }
}