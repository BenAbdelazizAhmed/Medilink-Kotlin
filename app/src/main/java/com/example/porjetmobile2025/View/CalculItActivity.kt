package com.example.porjetmobile2025.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.porjetmobile2025.R
import kotlin.math.pow

class CalculActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.itemphar)

        // Configurez la Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Activez la flèche de retour
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed() // Retour à la page précédente
        }

        // Références aux éléments du layout
        val inputHeight: EditText = findViewById(R.id.input_height)
        val inputWeight: EditText = findViewById(R.id.input_weight)
        val calculateButton: Button = findViewById(R.id.calculate_button)
        val resultBmi: TextView = findViewById(R.id.result_bmi)
        val resultAdvice: TextView = findViewById(R.id.result_advice)

        // Listener pour le bouton "Calculer"
        calculateButton.setOnClickListener {
            val heightText = inputHeight.text.toString()
            val weightText = inputWeight.text.toString()

            if (heightText.isNotEmpty() && weightText.isNotEmpty()) {
                try {
                    val height = heightText.toDouble() / 100 // Convertir en mètres
                    val weight = weightText.toDouble()

                    if (height > 0 && weight > 0) {
                        // Calcul de l'IMC
                        val bmi = weight / height.pow(2)

                        // Affichage du résultat
                        resultBmi.text = "IMC : %.2f".format(bmi)

                        // Conseil basé sur l'IMC
                        val advice = when {
                            bmi < 18.5 -> "Vous êtes en insuffisance pondérale."
                            bmi in 18.5..24.9 -> "Vous avez un poids normal."
                            bmi in 25.0..29.9 -> "Vous êtes en surpoids."
                            else -> "Vous êtes en obésité."
                        }
                        resultAdvice.text = advice
                    } else {
                        Toast.makeText(this, "Veuillez entrer des valeurs valides.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Erreur de calcul. Veuillez vérifier les entrées.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}