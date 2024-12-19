package com.example.porjetmobile2025.View

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.porjetmobile2025.R

class CalculatortwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Récupérer la Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Ajouter un bouton de retour
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Récupérer les vues
        val heightInput = findViewById<EditText>(R.id.height_input)
        val weightInput = findViewById<EditText>(R.id.weight_input)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val bmiResult = findViewById<TextView>(R.id.bmi_result)
        val caloriesResult = findViewById<TextView>(R.id.calories_result)

        // Écouteur de clic pour le bouton "Calculer"
        calculateButton.setOnClickListener {
            // Récupérer les valeurs saisies
            val height = heightInput.text.toString().toFloatOrNull()
            val weight = weightInput.text.toString().toFloatOrNull()

            // Vérifier que les valeurs sont valides
            if (height != null && weight != null) {
                // Calcul du BMI
                val bmi = calculateBMI(height, weight)
                val calories = calculateCalories(height, weight)

                // Afficher les résultats
                bmiResult.text = "BMI: $bmi"
                caloriesResult.text = "Calories Nécessaires: $calories kcal/jour"
            } else {
                // Si les entrées sont invalides, afficher un message d'erreur
                bmiResult.text = "Veuillez entrer des valeurs valides"
                caloriesResult.text = ""
            }
        }
    }

    // Fonction pour calculer le BMI
    private fun calculateBMI(height: Float, weight: Float): Float {
        // Conversion de la taille en mètres
        val heightInMeters = height / 100
        return weight / (heightInMeters * heightInMeters)
    }

    // Fonction pour calculer les calories nécessaires (exemple simplifié)
    private fun calculateCalories(height: Float, weight: Float): Int {
        // Exemple de calcul simplifié basé sur la taille et le poids
        return (weight * 24).toInt()  // À ajuster selon les besoins
    }

    // Gérer le retour arrière dans la Toolbar
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}
