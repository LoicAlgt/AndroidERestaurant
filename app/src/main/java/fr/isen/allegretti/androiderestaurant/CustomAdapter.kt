package fr.isen.allegretti.androiderestaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.allegretti.androiderestaurant.model.Items

internal class CustomAdapter(
    private var itemsList: ArrayList<Items>,
    val onItemClickListener: (Items) -> Unit
):
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemtext)
        var itemPriceView: TextView = view.findViewById(R.id.prix)
        var itemImageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.nameFr
        holder.itemTextView.setOnClickListener {
            onItemClickListener(item)
        }


        val price = item.prices[0]
        holder.itemPriceView.text = price.price
        holder.itemPriceView.setOnClickListener {
            onItemClickListener(item)
        }
        if (item.images[0].isNotEmpty()) {
            Picasso.get().load(item.images[0]).into(holder.itemImageView);}

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun refreshList(mealFromAPI: ArrayList<Items>) {
        itemsList = mealFromAPI

        notifyDataSetChanged()
    }
}




