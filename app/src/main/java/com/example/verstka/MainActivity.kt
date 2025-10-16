package com.example.verstka

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

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
    private lateinit var themeSwitch: SwitchMaterial
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var parentLayout: androidx.constraintlayout.widget.ConstraintLayout

    private var selectedNumber: TextView? = null
    private var selectedLayout: LinearLayout? = null
    private var selectedAnswer: String = ""
    private var correctAnswer: String = "Дверь"

    override fun onCreate(savedInstanceState: Bundle?) {
        // Устанавливаем тему ПЕРЕД созданием активности
        sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE)
        applyTheme()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lern_word)

        initializeViews()
        setupThemeSwitch()
        setClickableParent()
        setupButtonListeners()
    }

    private fun applyTheme() {
        val isDarkTheme = sharedPreferences.getBoolean("dark_theme", false)
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun initializeViews() {
        button1 = findViewById(R.id.first_variant)
        button2 = findViewById(R.id.second_variant)
        button3 = findViewById(R.id.third_variant)
        button4 = findViewById(R.id.fourth_variant)
        correct = findViewById(R.id.correct)
        incorrect = findViewById(R.id.incorrect)
        answer_button = findViewById(R.id.skip)
        tryagain = findViewById(R.id.try_again)
        text = findViewById(R.id.door)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        themeSwitch = findViewById(R.id.themeSwitch)
        parentLayout = findViewById(R.id.parentLayout)
    }

    private fun setupThemeSwitch() {
        val isDarkTheme = sharedPreferences.getBoolean("dark_theme", false)
        themeSwitch.isChecked = isDarkTheme
        updateSwitchText(isDarkTheme)

        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Сохраняем настройку
            sharedPreferences.edit().putBoolean("dark_theme", isChecked).apply()
            updateSwitchText(isChecked)

            // Применяем тему без перезагрузки активности
            applyTheme()

            // Обновляем цвета элементов вручную
            updateColorsForTheme(isChecked)
        }
    }

    private fun updateColorsForTheme(isDarkTheme: Boolean) {
        if (isDarkTheme) {
            // Цвета для тёмной темы
            parentLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.dark_background))
            updateTextColors(ContextCompat.getColor(this, R.color.white))
        } else {
            // Цвета для светлой темы
            parentLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            updateTextColors(ContextCompat.getColor(this, R.color.black))
        }
    }

    private fun updateTextColors(color: Int) {
        // Обновляем цвет основного текста
        text.setTextColor(color)

        // Можно добавить обновление других текстовых элементов
        val textView = findViewById<TextView>(R.id.textView)
        textView.setTextColor(color)
    }

    private fun updateSwitchText(isDarkTheme: Boolean) {
        themeSwitch.text = if (isDarkTheme) "Тёмная тема" else "Светлая тема"
    }

    private fun setupButtonListeners() {
        button1.setOnClickListener {
            resetAllBackgrounds()
            button1.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            answer_button.text = getString(R.string.answer)
            answer_button.isEnabled = true
            selectedAnswer("Март", button1, one)
        }

        button2.setOnClickListener {
            resetAllBackgrounds()
            button2.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            answer_button.text = getString(R.string.answer)
            answer_button.isEnabled = true
            selectedAnswer("Память", button2, two)
        }

        button3.setOnClickListener {
            resetAllBackgrounds()
            button3.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            answer_button.text = getString(R.string.answer)
            answer_button.isEnabled = true
            selectedAnswer("Дверь", button3, three)
        }

        button4.setOnClickListener {
            resetAllBackgrounds()
            button4.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers_active)
            answer_button.text = getString(R.string.answer)
            answer_button.isEnabled = true
            selectedAnswer("Клавиатура", button4, four)
        }

        answer_button.setOnClickListener {
            checkAnswer()
        }

        tryagain.setOnClickListener {
            resetAllBackgrounds()
            answer_button.visibility = View.VISIBLE
            answer_button.text = getString(R.string.skip_button)
            incorrect.visibility = View.GONE
        }
    }

    private fun resetAllBackgrounds() {
        val defaultBackground = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
        button1.background = defaultBackground
        button2.background = defaultBackground
        button3.background = defaultBackground
        button4.background = defaultBackground
    }

    private fun setClickableParent() {
        parentLayout.setOnClickListener {
            answer_button.text = getString(R.string.skip_button)
            selectedLayout?.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_containers)
        }
    }

    private fun checkAnswer() {
        if (selectedAnswer == correctAnswer) {
            correct.visibility = View.VISIBLE
            selectedLayout?.background = ContextCompat.getDrawable(this, R.drawable.rounded_containers_correct)
            text.setTextColor(ContextCompat.getColor(this, R.color.green))
            selectedNumber?.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_variants_correct)
            selectedNumber?.setTextColor(ContextCompat.getColor(this, R.color.white))
            incorrect.visibility = View.GONE
            answer_button.visibility = View.GONE
        } else {
            incorrect.visibility = View.VISIBLE
            answer_button.visibility = View.GONE
            selectedLayout?.background = ContextCompat.getDrawable(this, R.drawable.rounded_containers_wrong)
            selectedNumber?.background = ContextCompat.getDrawable(this, R.drawable.shape_rounded_variants_wrong)
            selectedNumber?.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }

    private fun selectedAnswer(answer: String, layout: LinearLayout, number: TextView) {
        selectedAnswer = answer
        selectedLayout = layout
        selectedNumber = number
        answer_button.isEnabled = true
    }
}