package com.example.porjetmobile2025.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.R
import com.example.porjetmobile2025.View.SignUpActivity  // Importez SignUpActivity

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion)  // Chargez la page de connexion


        // Gérer le clic sur le bouton "Se connecter"
        val signInButton: Button = findViewById(R.id.signeInButton)  // Utilisez l'ID du bouton "Se Connecter"
        signInButton.setOnClickListener {
            // Lancer la page d'inscription (SignUpActivity)
            val intent = Intent(this, AccueilActivity::class.java)
            startActivity(intent)
        }
    }
}
