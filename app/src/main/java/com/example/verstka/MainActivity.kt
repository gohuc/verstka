package com.example.verstka

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.example.verstka.ui.theme.VerstkaTheme

class MainActivity : ComponentActivity() {

    private lateinit var button1: LinearLayout
    private lateinit var button2: LinearLayout
    private lateinit var button3: LinearLayout
    private lateinit var button4: LinearLayout
    private lateinit var answer_button: Button
    private lateinit var correct: LinearLayout
    private lateinit var incorrect: LinearLayout
    private lateinit var text: TextView

    private var selectedAnswer: String = ""
    private var correctAnswer: String = "Дверь"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lern_word)

        button1 = findViewById<LinearLayout>(R.id.first_variant)
        button2 = findViewById<LinearLayout>(R.id.second_variant)
        button3 = findViewById<LinearLayout>(R.id.third_variant)
        button4 = findViewById<LinearLayout>(R.id.fourth_variant)
        correct = findViewById<LinearLayout>(R.id.correct)
        incorrect = findViewById<LinearLayout>(R.id.incorrect)
        answer_button = findViewById<Button>(R.id.skip)
        text = findViewById<TextView>(R.id.door)

        button1.setOnClickListener {
            button1.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            button2.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button3.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button4.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            answer_button.text = "Answer"
            answer_button.isEnabled = true
            selectedAnswer = "Март"

        }
        button2.setOnClickListener {
            button2.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            button1.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button3.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button4.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            answer_button.text = "Answer"
            answer_button.isEnabled = true
            selectedAnswer = "Память"
        }
        button3.setOnClickListener {
            button3.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            button1.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button2.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button4.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            answer_button.text = "Answer"
            answer_button.isEnabled = true
            selectedAnswer = "Дверь"
        }
        button4.setOnClickListener {
            button4.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            button1.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button3.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button2.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            answer_button.text = "Answer"
            answer_button.isEnabled = true
            selectedAnswer = "Клавиатура"
        }
        answer_button.setOnClickListener {
            checkAnswer()
        }
    }
    private fun MainActivity.checkAnswer() {
        if (selectedAnswer == correctAnswer){
            correct.visibility = View.VISIBLE
            button3.background = ContextCompat.getDrawable(this, R.drawable.rounded_containers_correct)
            text.setTextColor(ContextCompat.getColor(this,R.color.green))
            incorrect.visibility = View.GONE
            answer_button.visibility = View.GONE
        }
        else{
            incorrect.visibility = View.VISIBLE
            answer_button.visibility = View.GONE
            
        }
    }
}








