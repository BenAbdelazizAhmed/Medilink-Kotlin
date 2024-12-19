package com.example.porjetmobile2025;

import com.example.porjetmobile2025.R
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.View.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.porjetmobile2025.View.AccueilActivity
class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpTextView: TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion)

        // Initialiser Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialiser les vues
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signInButton = findViewById(R.id.signeInButton)
        signUpTextView = findViewById(R.id.signUpTextView)

        // Gestion de la connexion
        signInButton.setOnClickListener {
            validateInputs()
        }

        // Rediriger vers l'écran d'inscription
        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateInputs() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(this, "L'adresse e-mail est vide", Toast.LENGTH_SHORT).show()
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Adresse e-mail incorrecte", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Le mot de passe est vide", Toast.LENGTH_SHORT).show()
            return
        }

        signIn(email, password)
    }

    private fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
                    // Rediriger vers l'écran d'accueil
                    val intent = Intent(this, AccueilActivity::class.java)
                    startActivity(intent)
                    finish() // Fermer l'activité actuelle pour ne pas revenir en arrière
                } else {
                    Toast.makeText(this, "Échec de connexion: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
