package com.example.bali.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.bali.MainActivity
import com.example.bali.R
import com.example.bali.profile.ProfileFragment
import com.example.bali.signup.SignUpActivity
import com.example.bali.signup.UserProperties



class LoginFragment : Fragment() {
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var emailInput : EditText
    private lateinit var passwordInput : EditText
    private lateinit var loginButton: Button
    private lateinit var signupLink:TextView
//    private lateinit var messageBox : TextView
    private lateinit var progressBarLogin: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val view: View = inflater.inflate(
            R.layout.fragment_login, container, false
        )
        emailInput = view.findViewById(R.id.emailInput)
        passwordInput = view.findViewById(R.id.passwordInput)
        loginButton = view.findViewById(R.id.login_button)
        signupLink = view.findViewById<TextView>(R.id.signup_link)
//        messageBox = view.findViewById(R.id.message_box)
        progressBarLogin = view.findViewById(R.id.progress_bar)

        handleLoginClick(loginButton)
        handleSignUpClick(signupLink)
        observeLoginResult()

        return view
    }

    private fun handleSignUpClick(signupLink: TextView) {
        signupLink.setOnClickListener {
            val intent = Intent(requireContext(), SignUpActivity::class.java)
            startActivity(intent)
        }
    }



    override fun onResume() {
        resetParameters()
        super.onResume()
    }

    private fun observeLoginResult() {
        loginViewModel.loginResult.observe(viewLifecycleOwner) { result: Pair<HashMap<String,Any>, String> ->
            if (result.first.isNotEmpty()) {
//                closeKeyboard(requireContext(), requireView())
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                // Navigate to the ProfileFragment using an explicit intent
                val transaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, ProfileFragment())
                transaction.addToBackStack(null) // Optional: Allows the user to navigate back to the previous fragment
                transaction.commit()
                Toast.makeText(requireContext(), "login success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "login faild", Toast.LENGTH_SHORT).show()
            }
            progressBarLogin.visibility = View.GONE
        }
    }

    private fun handleLoginClick(loginButton:Button) {
        loginButton.setOnClickListener {
            val credentials = UserCredentials(emailInput.text.toString(), passwordInput.text.toString())
            if (checkCredentials(credentials, emailInput, passwordInput)) {
//                manageViews(emailInput, passwordInput, loginButton, signupButton, messageBox, mode="GONE")
                progressBarLogin.visibility = View.VISIBLE
                loginViewModel.loginUser(credentials)
            }
        }
    }


    private fun resetParameters(){
        emailInput.text.clear()
        passwordInput.text.clear()
    }

    //can be in utils - validationFunction
    private fun checkCredentials(
        credentials: UserCredentials, emailInput: EditText,
        passwordInput:EditText): Boolean{
        if (credentials.email.isEmpty()){
            emailInput.error = "Enter a email"
        }
        else if (credentials.password.isEmpty()){
            passwordInput.error = "Enter a password"
        }
        else if (credentials.password.length <=6) {
            passwordInput.error = "Password need to more than 6 characters long"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(credentials.email).matches()){
            emailInput.error = "Enter valid email format"
        }
        else{ return true }
        return false
    }
}
