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
    private lateinit var tryagain: Button
    private lateinit var one: TextView
    private lateinit var two: TextView
    private lateinit var three: TextView
    private lateinit var four: TextView

    private var selectedNumber: TextView? = null
    private var selectedLayout: LinearLayout? = null
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
        tryagain = findViewById<Button>(R.id.try_again)
        text = findViewById<TextView>(R.id.door)
        one = findViewById<TextView>(R.id.one)
        two = findViewById<TextView>(R.id.two)
        three = findViewById<TextView>(R.id.three)
        four = findViewById<TextView>(R.id.four)
        setClickableParent()

        button1.setOnClickListener {
            button1.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            button2.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button3.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button4.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            answer_button.text = getString(R.string.answer)
            answer_button.isEnabled = true
            selectedAnswer("Март",button1, one)

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
            answer_button.text = getString(R.string.answer)
            answer_button.isEnabled = true
            selectedAnswer("Память", button2, two )
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
            answer_button.text = getString(R.string.answer)
            answer_button.isEnabled = true
            selectedAnswer("Дверь",button3,three)
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
            answer_button.text = getString(R.string.answer)
            answer_button.isEnabled = true
            selectedAnswer("Клавиатура", button4,four)
        }
        answer_button.setOnClickListener {
            checkAnswer()
        }
        tryagain.setOnClickListener {
            button4.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button1.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button3.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            button2.background =
                ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
            answer_button.visibility = View.VISIBLE
            answer_button.text = getString(R.string.skip_button)
            incorrect.visibility = View.GONE

        }
    }

    private fun setClickableParent() {
        val clickableParent = findViewById<ConstraintLayout>(R.id.parentLayout)
        clickableParent.setOnClickListener {
            answer_button.text = getString(R.string.skip_button)
            selectedLayout?.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
        }
    }

    private fun MainActivity.checkAnswer() {
        if (selectedAnswer == correctAnswer){
            correct.visibility = View.VISIBLE
            selectedLayout?.background = ContextCompat.getDrawable(this, R.drawable.rounded_containers_correct)
            text.setTextColor(ContextCompat.getColor(this,R.color.green))
            selectedNumber?.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_variants_correct)
            selectedNumber?.setTextColor(ContextCompat.getColor(this,R.color.white))
            incorrect.visibility = View.GONE
            answer_button.visibility = View.GONE
        }
        else{
            incorrect.visibility = View.VISIBLE
            answer_button.visibility = View.GONE
            selectedLayout?.background = ContextCompat.getDrawable(this, R.drawable.rounded_containers_wrong)
            selectedNumber?.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_variants_wrong)
            selectedNumber?.setTextColor(ContextCompat.getColor(this,R.color.white))
        }
    }
    private fun MainActivity.selectedAnswer(answer: String, layout: LinearLayout, number: TextView) {
        selectedAnswer = answer
        selectedLayout = layout
        selectedNumber = number
        answer_button.isEnabled = true
    }
}











