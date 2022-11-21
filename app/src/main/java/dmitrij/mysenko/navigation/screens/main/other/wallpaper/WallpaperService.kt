package dmitrij.mysenko.navigation.screens.main.other.wallpaper

import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.util.Log
import android.view.SurfaceHolder
import kotlin.random.Random

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

        private val redrawRunnable = object : Runnable {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                model.update(currentTime - lastUpdate)
                lastUpdate = currentTime
                holder?.let { draw(it, model.stars) }
                handler?.postDelayed(this, 50)
            }
        }

        private fun draw(holder: SurfaceHolder, stars: List<Star>) {
            try {
                val canvas = holder.lockCanvas()
                painter.draw(canvas, stars)
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
            draw(holder, model.stars)
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

