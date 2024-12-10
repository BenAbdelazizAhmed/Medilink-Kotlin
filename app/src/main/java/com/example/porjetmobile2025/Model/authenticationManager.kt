package com.example.medilink.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class authenticationManager {

    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    // Fonction pour se connecter avec email et mot de passe
    fun signInWithEmailAndPassword(email: String, password: String, callback: (FirebaseUser?) -> Unit) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Connexion réussie
                    val user: FirebaseUser? = mAuth.currentUser
                    callback(user)
                } else {
                    // Échec de la connexion
                    callback(null)
                }
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return mAuth.currentUser
    }

    fun signOut() {
        mAuth.signOut()
    }
}
