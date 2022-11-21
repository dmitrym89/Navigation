package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.VectorDrawable
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.toColor
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import dmitrij.mysenko.navigation.R
import kotlin.math.abs
import kotlin.random.Random

class Model {

    var ctx: Context? = null

    private val initZ = -3f
    private val initCoef = 0f
    private val chanceStar = 0.5f
    private val chanceObj = 0.1f

    val stars = mutableListOf<Star>()
    val objs = mutableListOf<Obj>()

    fun update(deltaTime: Long) {
        if (random(0f, 1f) < chanceStar) {
            stars.add(generateStar())
        }
        for (star in stars) {
            star.z += deltaTime * star.speed
            val coef = calculateCoef(star.z)
            star.actualSize = calculateActualSize(star.size, coef)
            star.actualColor = calculateActualColor(star, coef)
            star.x2d = star.x / star.z
            star.y2d = star.y / star.z
        }
        stars.removeIf { star -> star.z >= 0 }

        if (random(0f, 1f) < chanceObj) {
            objs.add(generateObj())
        }
        for (obj in objs) {
            obj.z += deltaTime * obj.speed
            val coef = calculateCoef(obj.z)
            obj.actualSize = calculateActualSize(obj.size, coef)
            obj.x2d = obj.x / obj.z
            obj.y2d = obj.y / obj.z
        }
        objs.removeIf { obj -> obj.z >= 0 }
        Log.e("AA", "count = ${stars.size}")
        Log.e("AA", "objs = ${objs.size}")
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
        star.actualSize = calculateActualSize(star.size, initCoef)
        star.actualColor = calculateActualColor(star, initCoef)
        star.x2d = star.x / star.z
        star.y2d = star.y / star.z
        return star
    }

    private fun generateObj(): Obj {
        val obj = Obj(
            x = random(-1f, 1f),
            y = random(-1f, 1f),
            z = initZ,
            size = random(50f, 100f),
            speed = random(0.0001f, 0.0003f),
            drawable = generateDrawable()
        )
        obj.actualSize = calculateActualSize(obj.size, initCoef)
        obj.x2d = obj.x / obj.z
        obj.y2d = obj.y / obj.z
        return obj
    }

    private fun generateDrawable(): VectorDrawableCompat? {
        val id = when (Random.nextInt(1, 21)) {
            1 -> R.drawable.obj1
            2 -> R.drawable.obj2
            3 -> R.drawable.obj3
            4 -> R.drawable.obj4
            5 -> R.drawable.obj5
            6 -> R.drawable.obj6
            7 -> R.drawable.obj7
            8 -> R.drawable.obj8
            9 -> R.drawable.obj9
            10 -> R.drawable.obj10
            11 -> R.drawable.obj11
            12 -> R.drawable.obj12
            13 -> R.drawable.obj13
            14 -> R.drawable.obj14
            15 -> R.drawable.obj15
            16 -> R.drawable.obj16
            17 -> R.drawable.obj17
            18 -> R.drawable.obj18
            19 -> R.drawable.obj19
            else -> R.drawable.obj20
        }
        return if (ctx == null) null else VectorDrawableCompat.create(ctx!!.resources, id, null)
    }


    private fun calculateActualSize(size: Float, coef: Float): Float {
        return size * coef
    }

    private fun calculateActualColor(star: Star, coef: Float): Int {
        return (255 shl 24) or (((star.r * coef).toInt()) shl 16) or (((star.g * coef).toInt()) shl 8) or ((star.b * coef).toInt())
    }

    private fun calculateCoef(z: Float): Float {
        return (initZ - z) / (initZ)
    }

    private fun random(from: Float, to: Float): Float {
        return (from + (to - from) * Math.random()).toFloat()
    }
}