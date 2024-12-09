package com.example.porjetmobile2025

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser Firebase services
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Tester Firebase Analytics
        logAnalyticsEvent()

        // Ajouter un utilisateur pour tester Firebase Auth
        testFirebaseAuth()

        // Ajouter un document dans Firestore
        addDataToFirestore()

        // Sample data


    private fun logAnalyticsEvent() {
        val bundle = Bundle().apply {
            putString("event_type", "app_started")
        }
        firebaseAnalytics.logEvent("main_activity_opened", bundle)
        Log.d("Firebase", "Analytics event logged.")
    }

    private fun testFirebaseAuth() {
        val email = "testuser@example.com"
        val password = "password123"

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseAuth", "User created: ${firebaseAuth.currentUser?.email}")
                } else {
                    Log.e("FirebaseAuth", "Error: ${task.exception?.message}")
                }
            }
    }

    private fun addDataToFirestore() {
        val doctor = hashMapOf(
            "name" to "Dr. Ahmed Abdelaziz",
            "specialty" to "Cardiologist",
            "location" to "Monastir"
        )

        firestore.collection("doctors")
            .add(doctor)
            .addOnSuccessListener {
                Log.d("Firestore", "Document added with ID: ${it.id}")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Error adding document", e)
            }
    }
}
