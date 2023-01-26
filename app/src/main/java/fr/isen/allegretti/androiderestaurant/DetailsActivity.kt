package fr.isen.allegretti.androiderestaurant


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import fr.isen.allegretti.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.allegretti.androiderestaurant.model.Items


@Suppress("DEPRECATION")
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var item: Items
    private lateinit var name: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        item = intent.getSerializableExtra("detail") as Items
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        name = item.nameFr.toString()


        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setIcon(R.drawable.panier)
        actionBar?.title = name


        val spannableTitle = SpannableString(actionBar?.title)
        spannableTitle.setSpan(RelativeSizeSpan(0.80f), 0, spannableTitle.length, 0)
        actionBar?.title = spannableTitle


        val textView = findViewById<TextView>(R.id.DetailsNamePlat)
        val text = name
        textView.text = text


        for (ima in item.images) {
            if (ima.isNotEmpty()) {
                Picasso.get().load(item.images[0]).into(binding.detailsImagePlat);
            }
        }

        //Ajouter et supprimer un article
        val test = findViewById<TextView>(R.id.textView3)
        val Price = findViewById<TextView>(R.id.prixtot)
        val buttonIncrement = findViewById<Button>(R.id.buttonplus)
        buttonIncrement.setOnClickListener {
            val currentValue = test.text.toString().toInt()
            val currentValue2 = Price.text.toString().toInt()
            test.text = (currentValue + 1).toString()
            Price.text = (currentValue2 + 10).toString()
        }

        val buttonDecrement = findViewById<Button>(R.id.buttonmoins)
        buttonDecrement.setOnClickListener {
            val currentValue = test.text.toString().toInt()
            val currentValue2 = Price.text.toString().toInt()
            test.text = (currentValue - 1).toString()
            Price.text = (currentValue2 - 10).toString()
        }


        //verification des bouton
        /*binding.buttonplus.setOnClickListener {
        val text = "Vous avez ajouté un article"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
        }

        binding.buttonmoins.setOnClickListener {
            val text = "Vous avez enlevé un article"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }*/


        val ingredients= item.ingredients
        val ingredientsString = java.lang.StringBuilder()
        ingredients.forEach{ ingredients -> ingredientsString.append(ingredients.nameFr)
        ingredientsString.append("\n")
        }
    binding.detailsPricePlat.text = ingredientsString
    }


}

