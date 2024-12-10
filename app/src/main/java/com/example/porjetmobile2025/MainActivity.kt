package com.example.porjetmobile2025.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medilink.view.LoginView
import com.example.porjetmobile2025.R
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    private lateinit var loginView: LoginView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.Connexion)

        // Initialiser Firebase
        FirebaseApp.initializeApp(this)

        // Initialisation de la vue de connexion
        loginView = LoginView(this)
    }
}
