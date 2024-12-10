package com.example.medilink.view

import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import com.example.medilink.controller.authenticationController
import com.example.porjetmobile2025.R
import com.example.porjetmobile2025.MainActivity

class LoginView(private val activity: MainActivity) {

    private val emailEditText: EditText = activity.findViewById(R.id.emailEditText)
    private val passwordEditText: EditText = activity.findViewById(R.id.passwordEditText)
    private val signInButton: Button = activity.findViewById(R.id.signInButton)
    private val authController: authenticationController = authenticationController()

    init {
        // Initialisation du bouton de connexion
        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInWithEmailPassword(email, password)
            } else {
                showToast("Veuillez entrer un email et un mot de passe")
            }
        }
    }

    private fun signInWithEmailPassword(email: String, password: String) {
        authController.signIn(email, password) { user ->
            if (user != null) {
                // L'utilisateur est connecté
                showToast("Bienvenue ${user.email}")
            } else {
                // L'utilisateur n'a pas pu se connecter
                showToast("Échec de la connexion")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}
