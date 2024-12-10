package com.example.medilink.controller

import com.example.medilink.model.authenticationManager
import com.google.firebase.auth.FirebaseUser

class authenticationController {

    private val authManager = authenticationManager()

    // Méthode pour gérer l'authentification
    fun signIn(email: String, password: String, callback: (FirebaseUser?) -> Unit) {
        authManager.signInWithEmailAndPassword(email, password, callback)
    }

    // Méthode pour obtenir l'utilisateur courant
    fun getCurrentUser(): FirebaseUser? {
        return authManager.getCurrentUser()
    }

    // Méthode pour déconnecter l'utilisateur
    fun signOut() {
        authManager.signOut()
    }
}
