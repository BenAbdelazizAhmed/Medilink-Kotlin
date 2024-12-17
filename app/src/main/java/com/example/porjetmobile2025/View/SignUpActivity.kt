package com.example.porjetmobile2025.View

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.MainActivity
import com.example.porjetmobile2025.R
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var signInRedirectTextView: TextView
    private lateinit var patientRadioButton: RadioButton
    private lateinit var medecinRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inscription)

        // Initialiser FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialiser les vues
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        signUpButton = findViewById(R.id.signUpButton)
        signInRedirectTextView = findViewById(R.id.signInRedirectTextView)
        patientRadioButton = findViewById(R.id.radio_patient)
        medecinRadioButton = findViewById(R.id.radio_medecin)

        // Redirection vers la page de connexion
        signInRedirectTextView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Gestion du bouton d'inscription
        signUpButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()
        val role = if (patientRadioButton.isChecked) "Patient" else "Médecin"

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            return
        }

        // Validation de l'email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Adresse email invalide", Toast.LENGTH_SHORT).show()
            return
        }

        // Validation du mot de passe (6 caractères minimum)
        if (password.length < 6) {
            Toast.makeText(this, "Le mot de passe doit avoir au moins 6 caractères", Toast.LENGTH_SHORT).show()
            return
        }

        // Création de l'utilisateur avec Firebase Authentication
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inscription réussie
                    Toast.makeText(this, "Inscription réussie !", Toast.LENGTH_SHORT).show()

                    // Rediriger vers la page de connexion
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // En cas d'erreur
                    Toast.makeText(this, "Erreur : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
