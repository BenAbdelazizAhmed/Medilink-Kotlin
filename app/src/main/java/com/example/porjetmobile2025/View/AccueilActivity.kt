import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.porjetmobile2025.R
import com.google.firebase.database.*

class AccueilActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var articlesContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pageacce)

        // Initialisation de Firebase Database
        database = FirebaseDatabase.getInstance().reference.child("articles")
        articlesContainer = findViewById(R.id.articlesContainer)

        // Charger les articles recommandés
        loadRecommendedArticles()
    }

    private fun loadRecommendedArticles() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                articlesContainer.removeAllViews() // Vider les articles existants
                for (articleSnapshot in snapshot.children) {
                    val article = articleSnapshot.getValue(Article::class.java)
                    if (article != null) {
                        addArticleToView(article)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Gérez les erreurs ici
            }
        })
    }

    private fun addArticleToView(article: Article) {
        val articleView = layoutInflater.inflate(R.layout.article_item, articlesContainer, false)

        val imageView = articleView.findViewById<ImageView>(R.id.articleImageView)
        val titleView = articleView.findViewById<TextView>(R.id.articleTitleTextView)

        // Charger l'image avec Glide
        Glide.with(this).load(article.imageUrl).into(imageView)

        // Définir le titre
        titleView.text = article.title

        articlesContainer.addView(articleView)
    }
}

data class Article(
    val imageUrl: String = "",
    val title: String = ""
)
