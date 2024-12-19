package com.example.porjetmobile2025.View

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.Model.Article
import com.example.porjetmobile2025.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class AddPostActivity : AppCompatActivity() {

    private lateinit var commentEditText: EditText
    private lateinit var addPostButton: Button
    private lateinit var selectImageButton: Button
    private lateinit var backButtonImageView: ImageView // Nouvelle variable pour le bouton retour

    private var imageUri: Uri? = null

    // Firebase instances
    private lateinit var database: DatabaseReference
    private val storageReference = FirebaseStorage.getInstance().reference
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_article) // Votre fichier XML

        // Initialiser les vues
        commentEditText = findViewById(R.id.commentEditText)
        addPostButton = findViewById(R.id.addPostButton)
        selectImageButton = findViewById(R.id.selectImageButton)
        backButtonImageView = findViewById(R.id.backButtonImageView) // Initialiser l'ImageView

        // Initialiser Firebase Database
        database = FirebaseDatabase.getInstance().reference.child("articles")

        // Gérer le clic sur l'image pour revenir à la page précédente
        backButtonImageView.setOnClickListener {
            finish() // Terminer l'activité actuelle
        }

        // Sélectionner une image
        selectImageButton.setOnClickListener {
            openImageChooser()
        }

        // Ajouter un article
        addPostButton.setOnClickListener {
            uploadPost()
        }
    }

    private fun openImageChooser() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            Toast.makeText(this, "Image sélectionnée", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadPost() {
        val comment = commentEditText.text.toString().trim()
        if (comment.isEmpty()) {
            Toast.makeText(this, "Ajoutez un commentaire", Toast.LENGTH_SHORT).show()
            return
        }

        val uniqueId = UUID.randomUUID().toString()
        val imageRef = if (imageUri != null) storageReference.child("articles/$uniqueId.jpg") else null

        // Si une image est choisie, uploader l'image dans Firebase Storage
        imageRef?.putFile(imageUri!!)?.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                savePostToDatabase(comment, uri.toString())
            }
        }?.addOnFailureListener {
            Toast.makeText(this, "Erreur d'upload de l'image", Toast.LENGTH_SHORT).show()
        } ?: run {
            // Si aucune image n'est sélectionnée, simplement ajouter le post sans image
            savePostToDatabase(comment, "")
        }
    }

    private fun savePostToDatabase(comment: String, imageUrl: String) {
        val userId = firebaseAuth.currentUser?.uid ?: "anonyme"
        val timestamp = System.currentTimeMillis()

        val article = Article(
            userId = userId,
            comment = comment,
            imageUrl = imageUrl,
            timestamp = timestamp
        )

        // Ajouter le post à Firebase Realtime Database sous la collection "articles"
        database.push().setValue(article).addOnSuccessListener {
            Toast.makeText(this, "Post ajouté avec succès", Toast.LENGTH_SHORT).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Erreur lors de l'ajout du post", Toast.LENGTH_SHORT).show()
        }
    }
}
