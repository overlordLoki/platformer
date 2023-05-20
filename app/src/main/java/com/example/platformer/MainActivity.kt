package com.example.platformer

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.platformer.R.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        val upButton = findViewById<Button>(R.id.buttonUp)
        val downButton = findViewById<Button>(R.id.buttonDown)
        val leftButton = findViewById<Button>(R.id.buttonLeft)
        val rightButton = findViewById<Button>(R.id.buttonRight)

        val funk = Funks();
        val gameView = findViewById<GameView>(id.gameView)
        // Set up the on click listeners for the buttons
        upButton.setOnClickListener {
            gameView.player.vy = -5 // Move up
        }

        downButton.setOnClickListener {
            gameView.player.vy = 5 // Move down
        }

        leftButton.setOnClickListener {
            gameView.player.vx = -5 // Move to the left
        }

        rightButton.setOnClickListener {
            gameView.player.vx = 5 // Move to the right
        }
    }
}

