package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat

class Obj(
    val x: Float,
    val y: Float,
    var z: Float,
    val size: Float,
    val speed: Float,
    val drawable: VectorDrawableCompat?,

    var actualSize: Float = 0f,
    var x2d: Float = 0f,
    var y2d: Float = 0f,
) {
    override fun toString(): String {
        return "Obj(x=$x, y=$y, z=$z, size=$size, speed=$speed, actualSize=$actualSize, x2d=$x2d, y2d=$y2d)"
    }
}