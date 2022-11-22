package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.SurfaceHolder
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import dmitrij.mysenko.navigation.R

class WallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine {
        return WallpaperEngine()
    }

    inner class WallpaperEngine : WallpaperService.Engine() {

        private var holder: SurfaceHolder? = null
        private var handler: Handler? = null

        private val model = Model()
        private val painter = Painter()
        private var lastUpdate: Long = System.currentTimeMillis()

        init {
            model.drawables = listOf(
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj1, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj2, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj3, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj4, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj5, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj6, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj7, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj8, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj9, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj10, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj11, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj12, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj13, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj14, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj15, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj16, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj17, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj18, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj19, null),
                VectorDrawableCompat.create(this@WallpaperService.resources, R.drawable.obj20, null)
            )
            painter.bhBitmap = BitmapFactory.decodeResource(this@WallpaperService.resources, R.drawable.bh)
        }

        private val redrawRunnable = object : Runnable {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                model.update(currentTime - lastUpdate)
                lastUpdate = currentTime
                holder?.let { draw(it, model.forDraw) }
                handler?.postDelayed(this, 50)
            }
        }

        private fun draw(holder: SurfaceHolder, list: List<Space>) {
            try {
                val canvas = holder.lockCanvas()
                painter.draw(canvas, list)
                holder.unlockCanvasAndPost(canvas)
            } catch (e: Exception) {
                Log.e("WALLPAPER", e.message, e)
            }
        }

        override fun onCreate(surfaceHolder: SurfaceHolder) {
            super.onCreate(surfaceHolder)
            holder = surfaceHolder
            handler = Handler(Looper.getMainLooper())
        }

        override fun onDestroy() {
            super.onDestroy()
        }

        override fun onVisibilityChanged(visible: Boolean) {
            super.onVisibilityChanged(visible)
            handler?.removeCallbacks(redrawRunnable)
            if (visible) {
                handler?.post(redrawRunnable)
            }
        }

        override fun onSurfaceChanged(
            holder: SurfaceHolder,
            format: Int,
            width: Int,
            height: Int
        ) {
            super.onSurfaceChanged(holder, format, width, height)
            this.holder = holder
        }

        override fun onSurfaceRedrawNeeded(holder: SurfaceHolder) {
            super.onSurfaceRedrawNeeded(holder)
            this.holder = holder
            draw(holder, model.forDraw)
        }

        override fun onSurfaceCreated(holder: SurfaceHolder) {
            super.onSurfaceCreated(holder)
            this.holder = holder
            handler?.post(redrawRunnable)
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder) {
            super.onSurfaceDestroyed(holder)
            this.holder = holder
            handler?.removeCallbacks(redrawRunnable)
        }
    }
}

