package com.example.porjetmobile2025.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.R
import com.example.porjetmobile2025.Model.Appointment
import com.google.firebase.database.DatabaseReference
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase
import android.app.DatePickerDialog
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

class AppointmentActivity : AppCompatActivity() {


    private lateinit var bookAppointmentButton: Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchlabo)
    bookAppointmentButton = findViewById(R.id.book_appointment_button)
    database = FirebaseDatabase.getInstance().reference
   bookAppointmentButton.setOnClickListener {
       val appointment = Appointment("doctorName", "ahmed ben abdelaziz", "12/02/2025", "complaint")

                // Ajouter les données de rendez-vous dans Firebase
                val appointmentId = database.child("appointments").push().key
                if (appointmentId != null) {
                    database.child("appointments").child(appointmentId).setValue(appointment)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Rendez-vous réservé avec succès!", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Échec de la réservation, veuillez réessayer.", Toast.LENGTH_SHORT).show()
                        }
                }

        }
    }
}
