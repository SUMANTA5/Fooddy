package com.sumanta.fooddy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.sumanta.fooddy.R
import com.sumanta.fooddy.model.Restaurant
import kotlinx.android.synthetic.main.recycler_home_single_row.view.*

class HomeRecyclerAdapter(val context: Context,val itemList : ArrayList<Restaurant>)
    :RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_home_single_row, parent, false)

        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
       val restaurant = itemList[position]
        holder.textRestaurantName.text = restaurant.name
        holder.textRestaurantRating.text = restaurant.rating
        holder.textRestaurantCost.text = restaurant.costForOne
        Picasso.get().load(restaurant.imageUrl).error(R.drawable.kfc).into(holder.imgRestaurant)


        holder.clickRestaurant.setOnClickListener {
            Toast.makeText(context,"click on ${holder.textRestaurantName.text}"
                ,Toast.LENGTH_LONG).show()
        }

    }

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textRestaurantName: TextView = view.findViewById(R.id.txtRestaurantName)
        val textRestaurantRating: TextView = view.findViewById(R.id.txtRestaurantRating)
        val textRestaurantCost: TextView = view.findViewById(R.id.txtCase)
       val imgRestaurant: ImageView = view.findViewById(R.id.imgRestaurant)
       val clickRestaurant : LinearLayout = view.findViewById(R.id.clickRestaurant)
    }
}