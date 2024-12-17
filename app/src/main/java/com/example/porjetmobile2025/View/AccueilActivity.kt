package com.example.porjetmobile2025.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.R
import com.example.porjetmobile2025.View.SignInActivity  // Import de l'activité de connexion
import com.example.porjetmobile2025.View.SignUpActivity  // Import de l'activité d'inscription

class AccueilActivity : AppCompatActivity() {

    private lateinit var btnUser: Button
    private lateinit var btnServices: Button
    private lateinit var signUpLink: TextView  // Pour l'inscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pageacce)  // Charge le layout accueil_activity.xml





    }
}
