package fr.isen.allegretti.androiderestaurant


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
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


        val textView = findViewById<TextView>(R.id.nomplat)
        val text = name
        textView.text = text


        for (ima in item.images) {
            if (ima.isNotEmpty()) {
                Picasso.get().load(item.images[0]).into(binding.detailsImagePlat);
            }
        }




        //Ajouter un bouton
        val prixproduit = item.prices
        val priceString = java.lang.StringBuilder()
        val priceunique = item.prices[0].price?.toDouble()
        var somme = 0
        binding.buttonplus.setOnClickListener {
            somme++
            binding.textView3.text =
                Editable.Factory.getInstance().newEditable(somme.toString())

            if (item.prices.isNotEmpty()) {
                prixproduit.forEach { prix ->
                    priceString.append(prix.price)
                }
                val number = somme * priceunique!!
                binding.prixtot.text = number.toString()
            }
        }

        //ajouter dans le panier
        binding.ajouterpanier.setOnClickListener {
            binding.textView3.text =
                Editable.Factory.getInstance().newEditable(somme.toString())

            if (item.prices.isNotEmpty()) {
                prixproduit.forEach { prix ->
                    priceString.append(prix.price)
                }
                val number = somme * priceunique!!
                binding.prixtot.text = number.toString()
            }
            val number = somme * priceunique!!
            val basket = mapOf("Total du panier : " to number.toString() + "€")
            val basketJson = Gson().toJson(basket)
            val fileOutputStream = openFileOutput("basket.json", Context.MODE_PRIVATE)
            fileOutputStream.write(basketJson.toByteArray())
            fileOutputStream.close()
            val view = findViewById<View>(android.R.id.content)
            Snackbar.make(view, "Vous avez pris " + number.toString() + "€", Snackbar.LENGTH_SHORT)
                .show()
        }

        //Bouton moins
        val myTextView = findViewById<TextView>(R.id.prixtot)
        val text2 = myTextView.text.toString()
        binding.buttonmoins.setOnClickListener {
            if (somme <= 0) {
                Toast.makeText(applicationContext, "Vous avez déjà 0 article", Toast.LENGTH_SHORT).show();
            }
            else {
                somme--
                binding.textView3.setText(
                    Editable.Factory.getInstance().newEditable(somme.toString())
                )
                val number = somme * priceunique!!
                binding.prixtot.text = number.toString()
            }
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
        ingredientsString.append(",")
        }
    binding.ingredient.text = ingredientsString
    }


}

