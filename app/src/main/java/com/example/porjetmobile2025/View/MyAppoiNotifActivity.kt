package com.example.porjetmobile2025.View

import com.example.porjetmobile2025.R
import com.google.firebase.database.DatabaseReference
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MyAppoiNotifActivity : AppCompatActivity() {

    private lateinit var bookAppointmentButton: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.myappoinments)

        // Initialisation de vos boutons et autres composants ici
    }
}
