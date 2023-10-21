package com.omega.pkmn_quizz.ui.quiz

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omega.pkmn_quizz.PokemonDataUtils
import com.omega.pkmn_quizz.PokemonTypes
import com.omega.pkmn_quizz.databinding.FragmentQuizzBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizzBinding? = null
    private lateinit var typeTop: PokemonTypes
    private lateinit var typeBot: PokemonTypes
    private lateinit var textView: TextView
    private lateinit var topTypeImg: ImageView
    private lateinit var botTypeImg: ImageView
    private lateinit var buttonX0: ImageView
    private lateinit var buttonX05: ImageView
    private lateinit var buttonX1: ImageView
    private lateinit var buttonX2: ImageView


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)

        _binding = FragmentQuizzBinding.inflate(inflater, container, false)
        val root: View = binding.root

        textView = binding.answerText
        topTypeImg = binding.topType
        botTypeImg = binding.botType
        buttonX0 = binding.buttonX0
        buttonX05 = binding.buttonX12
        buttonX1 = binding.buttonX1
        buttonX2 = binding.buttonX2

        quizViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        buttonX0.setOnClickListener {
            val result: Boolean = check(0.0)
            modifyText(result)
        }
        buttonX05.setOnClickListener {
            val result: Boolean = check(0.5)
            modifyText(result)
        }
        buttonX1.setOnClickListener {
            val result: Boolean = check(1.0)
            modifyText(result)
        }
        buttonX2.setOnClickListener {
            val result: Boolean = check(2.0)
            modifyText(result)
        }

        rerollTypes()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun check(value: Double): Boolean {
        return PokemonDataUtils.TYPE_TABLE[typeBot.ordinal][typeTop.ordinal] == value
    }

    private fun modifyText(result: Boolean) {
        if (result) {
            textView.text = "Correct!"
            textView.setTextColor(Color.GREEN)
        } else {
            textView.text =
                "Wrong, the correct answer is x" + PokemonDataUtils.TYPE_TABLE[typeBot.ordinal][typeTop.ordinal]
            textView.setTextColor(Color.RED)
        }
        rerollTypes()
    }

    private fun rerollTypes() {
        typeTop = PokemonTypes.entries.random();
        typeBot = PokemonTypes.entries.random();
        topTypeImg.setImageResource(typeTop.imageSrc)
        botTypeImg.setImageResource(typeBot.imageSrc)
    }

}