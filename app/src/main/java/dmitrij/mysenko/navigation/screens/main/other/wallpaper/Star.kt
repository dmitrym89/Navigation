package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import android.graphics.Rect
import android.graphics.RectF

class Star(
    val x: Float,
    val y: Float,
    var z: Float,
    val size: Float,
    val r: Int,
    val g: Int,
    val b: Int,
    val speed: Float,

    var actualSize: Float = 0f,
    var actualColor: Int = 0,
    var x2d: Float = 0f,
    var y2d: Float = 0f,
){
    override fun toString(): String {
        return "Star(x=$x, y=$y, z=$z, size=$size, r=$r, g=$g, b=$b, speed=$speed, actualSize=$actualSize, actualColor=$actualColor, x2d=$x2d, y2d=$y2d)"
    }
}
