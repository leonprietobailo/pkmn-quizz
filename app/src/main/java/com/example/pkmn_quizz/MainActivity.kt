package com.example.pkmn_quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myButton = findViewById<Button>(R.id.button2)

        myButton.setOnClickListener {
           // val intent = Intent(this, SecondActivity::class.java)
            try {
                startActivity(Intent(this, SecondActivity::class.java))
            } catch (e : Exception){
                e.printStackTrace();
            }
        }

    }


}