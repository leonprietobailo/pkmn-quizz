package com.omega.pkmn_quizz.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omega.pkmn_quizz.databinding.FragmentQuizzBinding

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizzBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val quizViewModel =
            ViewModelProvider(this).get(QuizViewModel::class.java)

        _binding = FragmentQuizzBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.answerText
        quizViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}