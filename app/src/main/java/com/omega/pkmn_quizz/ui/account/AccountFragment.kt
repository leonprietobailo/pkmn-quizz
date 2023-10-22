package com.omega.pkmn_quizz.ui.account

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.omega.pkmn_quizz.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null


    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient

    private lateinit var googleImage: ImageView
    private lateinit var personImage: ImageView
    private lateinit var name: TextView
    private lateinit var email: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider(this).get(AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /**
        val textView: TextView = binding.textNotifications
        accountViewModel.text.observe(viewLifecycleOwner) {
        textView.text = it
        }
         */


        // START //
        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()

        gsc = GoogleSignIn.getClient(requireActivity(), gso)
        gsc.signOut()

        googleImage = binding.gsign
        personImage = binding.gsign2
        name = binding.fullName
        email = binding.email

        name.visibility = View.INVISIBLE
        email.visibility = View.INVISIBLE
        personImage.visibility = View.INVISIBLE

        googleImage.setOnClickListener {
            signIn()
        }
        // END //
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

// Support methods

    fun signIn() {
        var signInIntent: Intent = gsc.signInIntent
        startActivityForResult(signInIntent, 1000)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)


            task.getResult()
            //finish()
            val acct = GoogleSignIn.getLastSignedInAccount(requireActivity())
            if (acct != null) {
                val nameText: String = "Name: " + acct.displayName
                val emailText: String = "Email: " + acct.email
                val nameTextSpannable = SpannableStringBuilder(nameText)
                val emailTextSpannable = SpannableStringBuilder(emailText)
                nameTextSpannable.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    5,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                emailTextSpannable.setSpan(
                    StyleSpan(Typeface.BOLD),
                    0,
                    6,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                name.text = nameTextSpannable
                email.text = emailTextSpannable

                name.visibility = View.VISIBLE
                email.visibility = View.VISIBLE
                personImage.visibility = View.VISIBLE
                googleImage.visibility = View.INVISIBLE
            }
        }
    }


}