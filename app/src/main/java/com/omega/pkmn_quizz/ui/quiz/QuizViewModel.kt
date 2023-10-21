package com.omega.pkmn_quizz.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Select the efectiveness of the bottom type against the top type."

    }
    val text: LiveData<String> = _text

}