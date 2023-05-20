package com.example.platformer

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View

class GameView1 : View {
    // Instantiate the player
    private val originalPlayerImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bird1)
    private val scaledPlayerImage: Bitmap = Bitmap.createScaledBitmap(originalPlayerImage, 100, 100, false)
    private val player: Player = Player(100, 100, scaledPlayerImage)

    // Background image
    private val originalBackground: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg)
    private var scaledBackground = originalBackground

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // When the size of the view is determined, create a scaled bitmap for the background
        scaledBackground = Bitmap.createScaledBitmap(originalBackground, w, h, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Draw the background
        canvas.drawBitmap(scaledBackground, 0f, 0f, null)
        player.draw(canvas) // Draw the player
    }
}

class GameView(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs), SurfaceHolder.Callback {
    private val thread: GameThread
    val player: Player
    private val background: Bitmap

    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)

        // Instantiate the player
        val originalPlayerImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bird1)
        val scaledPlayerImage: Bitmap = Bitmap.createScaledBitmap(originalPlayerImage, 100, 100, false)
        player = Player(100, 100, scaledPlayerImage)

        // Load the background image
        background = BitmapFactory.decodeResource(resources, R.drawable.bg)
    }

    fun update() {
        // Update game state
        player.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        // Draw the background image
        val scaledBackground = Bitmap.createScaledBitmap(background, canvas.width, canvas.height, true)
        canvas.drawBitmap(scaledBackground, 0f, 0f, null)
        // Draw game state to the screen
        player.draw(canvas)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread.running = true
        thread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        while (retry) {
            try {
                thread.running = false
                thread.join()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            retry = false
        }
    }
}


