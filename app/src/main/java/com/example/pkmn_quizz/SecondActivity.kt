package com.example.pkmn_quizz

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class SecondActivity : AppCompatActivity() {

    lateinit var imgView1 : ImageView;
    lateinit var imgView2 : ImageView;
    lateinit var buttonX0 : ImageView;
    lateinit var buttonX12 : ImageView;
    lateinit var buttonX1 : ImageView;
    lateinit var buttonX2 : ImageView;
    lateinit var buttonSkip : ImageView;
    lateinit var answerText : TextView;

    lateinit var typeTop : PokemonTypes
    lateinit var typeBot : PokemonTypes


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.quizz_main)

            initComponents()
            initListeners()

            rerollTypes()
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    private fun initComponents() {
        imgView1 = findViewById<ImageView>(R.id.topType)
        imgView2 = findViewById<ImageView>(R.id.botType)
        buttonX0 = findViewById<ImageView>(R.id.buttonX0)
        buttonX12 = findViewById<ImageView>(R.id.buttonX12)
        buttonX1 = findViewById<ImageView>(R.id.buttonX1)
        buttonX2 = findViewById<ImageView>(R.id.buttonX2)
        buttonSkip = findViewById<ImageView>(R.id.buttonSkip)
        answerText = findViewById<TextView>(R.id.answerText)
    }

    private fun rerollTypes(){
        typeTop = PokemonTypes.entries.random();
        typeBot = PokemonTypes.entries.random();
        imgView1.setImageResource(typeTop.imageSrc)
        imgView2.setImageResource(typeBot.imageSrc)
    }

    private fun initListeners(){
        buttonX0.setOnClickListener {
            check(0.0)
        }
        buttonX12.setOnClickListener {
            check(0.5)
        }
        buttonX1.setOnClickListener {
            check(1.0)
        }
        buttonX2.setOnClickListener {
            check(2.0)
        }
        buttonSkip.setOnClickListener {
            rerollTypes()
        }
    }

    private fun check(value : Double){
        if(PokemonDataUtils.TYPE_TABLE[typeBot.ordinal][typeTop.ordinal] == value){
            answerText.text = "Correct!"
            answerText.setTextColor(Color.GREEN)
        } else {
            answerText.text = "Wrong, the correct answer is x" + PokemonDataUtils.TYPE_TABLE[typeBot.ordinal][typeTop.ordinal]
            answerText.setTextColor(Color.RED)
        }
        rerollTypes()
    }

}


