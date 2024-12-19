package com.example.porjetmobile2025.View

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.porjetmobile2025.R

class MedicamentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medicamenet) // Assure-toi que le fichier XML correspondant existe

        // Configuration de la Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Activer le bouton retour
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Gestion du clic sur le bouton retour dans la Toolbar
        toolbar.setNavigationOnClickListener {
            finish() // Termine l'activité pour revenir à l'écran précédent
        }

        // Gestion du bouton "Retour" via OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish() // Termine l'activité et retourne à l'écran précédent
            }
        })
    }
}
