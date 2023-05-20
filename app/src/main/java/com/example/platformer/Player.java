package com.example.platformer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {
    public int x = 0;
    public int y = 0;
    public int vx = 0; // Velocity in the x direction
    public int vy = 0; // Velocity in the y direction
    private int maxSpeed = 100; // The speed of the player
    private Canvas canvas;
    private final Bitmap image; // The image that represents the player

    public Player(int startingX, int startingY, Bitmap startingImage){
        x = startingX;
        y = startingY;
        image = startingImage;
    }

    public void move() {
        // Update the player's position based on the velocity.
        x += vx;
        y += vy;
    }

    public void draw(Canvas canvas) {
        if(this.canvas == null) {
            this.canvas = canvas;
        }
        // Draw the player's image on the canvas at the player's current position.
        canvas.drawBitmap(image, x, y, null);
    }

    //apply gravity to the player
    public void applyGravity() {
        vy += 1;
    }

    //collision detection
    public boolean isCollidingWith(int x, int y, int width, int height) {
        // Check if the player is colliding with the specified rectangle.
        if (this.x < x + width &&
                this.x + image.getWidth() > x &&
                this.y < y + height &&
                this.y + image.getHeight() > y) {
            return true;
        }
        return false;
    }

    public void update(){
        if (canvas == null) {
            return;
        }
        // Apply gravity
        //applyGravity();

        // Update the player's position
        move();

        // Collision detection and response
        // Add your collision detection code here
        //keep player on bottom of screen
        keepOnScreen();
        // Cap maximum velocity
        if(vy > maxSpeed) {
            vy = maxSpeed;
        }
    }

    private void keepOnScreen() {
        // Keep the player on the screen
        if(x < 0) {
            x = 0;
        }
        if(x > canvas.getWidth() - image.getWidth()) {
            x = canvas.getWidth() - image.getWidth();
        }
        if(y < 0) {
            y = 0;
        }
        if(y > canvas.getHeight() - image.getHeight()) {
            y = canvas.getHeight() - image.getHeight();
        }
    }



    // Getter and setter methods for velocity and image could be added as well, if necessary.
}

