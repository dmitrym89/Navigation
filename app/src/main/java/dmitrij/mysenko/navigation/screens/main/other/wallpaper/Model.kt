package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import android.graphics.Rect
import android.graphics.RectF
import android.util.Log
import androidx.core.graphics.toColor
import kotlin.math.abs

class Model {

    private val initZ = -3f
    private val initCoef = 0f
    private val chance = 0.5f

    val stars = mutableListOf<Star>()

    fun update(deltaTime: Long) {
        if (random(0f, 1f) < chance) {
            stars.add(generateStar())
        }
//        if (stars.isEmpty()) {
//            stars.add(generateStar())
//        }

        for (star in stars) {
            star.z += deltaTime * star.speed
            val coef = calculateCoef(star)
            star.actualSize = calculateActualSize(star, coef)
            star.actualColor = calculateActualColor(star, coef)
            star.x2d = star.x / star.z
            star.y2d = star.y / star.z
        }

        stars.removeIf { star -> star.z >= 0 }
        Log.e("AA", "count = ${stars.size}")
    }

    private fun generateStar(): Star {
        val star = Star(
            x = random(-1f, 1f),
            y = random(-1f, 1f),
            z = initZ,
            size = random(1f, 10f),
            r = (255 * random(0.8f, 1f)).toInt(),
            g = (255 * random(0.8f, 1f)).toInt(),
            b = (255 * random(0.8f, 1f)).toInt(),
            speed = random(0.0001f, 0.0003f)
        )
        star.actualSize = calculateActualSize(star, initCoef)
        star.actualColor = calculateActualColor(star, initCoef)
        star.x2d = star.x / star.z
        star.y2d = star.y / star.z
        return star
    }

    private fun calculateActualSize(star: Star, coef: Float): Float {
        return star.size * coef
    }

    private fun calculateActualColor(star: Star, coef: Float): Int {
        return (255 shl 24) or (((star.r * coef).toInt()) shl 16) or (((star.g * coef).toInt()) shl 8) or ((star.b * coef).toInt())
    }

    private fun calculateCoef(star: Star): Float {
        return (initZ - star.z) / (initZ)
    }

    private fun random(from: Float, to: Float): Float {
        return (from + (to - from) * Math.random()).toFloat()
    }
}