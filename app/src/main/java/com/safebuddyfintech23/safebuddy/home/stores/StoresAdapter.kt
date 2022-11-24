package com.safebuddyfintech23.safebuddy.home.stores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.safebuddyfintech23.safebuddy.R

class StoresAdapter(private val storesList: List<StoresModel>) : RecyclerView.Adapter<StoresAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_stores, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val storesViewModel = storesList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(storesViewModel.storeLogoImage)

        // sets the text to the textview from our itemHolder class
        holder.textView.setText(storesViewModel.storeName)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return storesList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.store_logo)
        val textView: TextView = itemView.findViewById(R.id.store_name)
    }
}