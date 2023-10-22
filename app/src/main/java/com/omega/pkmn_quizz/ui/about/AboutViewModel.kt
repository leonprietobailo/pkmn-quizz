package com.omega.pkmn_quizz.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value =
            "Welcome to PKMN Quizz. This is a sample app developed for learning purposes. With this software, you will be able to train your Pokemon skills and learn the table of types.\n\nQuizz itself might be wrong due to possible mistakes importing data.\n\nFor any inquiries, email me at:\nleonprietobailo@gmail.com"
    }
    val text: LiveData<String> = _text
}