package fr.isen.allegretti.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.allegretti.androiderestaurant.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val actionBar = supportActionBar
        actionBar!!.title = "DroitRestaurant"


        /*
        val action = findViewById<Button>(R.id.buttonEntrée_affichage)
        action.setOnClickListener() {
            Toast.makeText(applicationContext, "Coucou", Toast.LENGTH_SHORT).show();
        }
        */

        //val action1= findViewById<Button>(R.id.buttonEntrée_affichage)
        binding.buttonentree.setOnClickListener {

            Log.d("TAG", "Home est delete")

            val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("samplename", "Entrées")
            startActivity(intent)
        }


        binding.buttonplat.setOnClickListener {
            val intent= Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("samplename", "Plats")
            startActivity(intent)
        }


        binding.buttondessert.setOnClickListener {
            val intent= Intent(this@HomeActivity, CategoryActivity::class.java)
            intent.putExtra("samplename", "Desserts")
            startActivity(intent)
        }



        /*val action3= findViewById<Button>(R.id.buttonPlat_affichage)
        action3.setOnClickListener() {
            Snackbar.make(it, "Creeper, OH MAN!!!", Snackbar.LENGTH_SHORT).show()
        }
            */

        /* val action4 = findViewById<Button>(R.id.buttonDessert_affichage)
        action4.setOnClickListener() {
            Snackbar.make(it, "Creeper, OH MAN!!!", Snackbar.LENGTH_SHORT).show()
        }

         */


    }


}


