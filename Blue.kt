package com.example.simonsaysapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class Blue : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blue)

        // Fetch buttons and text areas
        val title = findViewById<TextView>(R.id.titleText)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val green = findViewById<Button>(R.id.greenBtn)
        val yellow = findViewById<Button>(R.id.yellowBtn)
        val blue = findViewById<Button>(R.id.blueBtn)
        val red = findViewById<Button>(R.id.redBtn)
        val restart = findViewById<Button>(R.id.restartBtn)
        val activitiesArray = arrayOf(Green::class.java, Yellow::class.java, Blue::class.java, Red::class.java)

        // Get count, index and color from intent
        var score = intent.getIntExtra("score", -2)
        var count = intent.getIntExtra("count", -3)
        val colors = intent.getStringArrayListExtra("colors")

        // Update displayed score
        scoreText.text = score.toString()

        // Update title text
        if(score != count){
            val temp = "Color: " + (count + 1)
            title.text = temp
        } else {
            val temp = "Simon says " + colors[count]
            title.text = temp
        }

        // Play lost or won the game
        fun gameOver(newTitle: String){
            colors[count] = newTitle
            title.text = newTitle
            restart.visibility = View.VISIBLE
            red.text = newTitle
            yellow.text = newTitle
            green.text = newTitle
        }

        // Update game based on user's choice
        fun onCorrect(answer: String, classNum: Int){
            if (colors[count] == answer){
                val intent = Intent(this@Blue, activitiesArray[classNum])
                if((count+1) == colors.size){
                    gameOver("YOU WIN!")
                } else {
                    if (count == score) {
                        count = -1
                        score++
                    }
                    count++
                    intent.putStringArrayListExtra("colors", colors)
                    intent.putExtra("count", count)
                    intent.putExtra("score", score)
                    startActivity(intent)
                }
            }
            else if(restart.visibility != 0){
                gameOver("gameOver")
            }
        }

        // On click listeners for each button
        green.setOnClickListener {
            onCorrect("Green", 0)
        }
        yellow.setOnClickListener {
            onCorrect("Yellow", 1)
        }
        blue.setOnClickListener {
            onCorrect("Blue", 2)
        }
        red.setOnClickListener {
            onCorrect("Red", 3)
        }
        restart.setOnClickListener {
            val intent = Intent(this@Blue, MainActivity::class.java)
            startActivity(intent)
        }
    }
}















