package com.safebuddyfintech23.safebuddy.stores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.safebuddyfintech23.safebuddy.R
import com.safebuddyfintech23.safebuddy.databinding.FragmentStoreProductBinding
import com.safebuddyfintech23.safebuddy.databinding.StoreProductDescriptionCardBinding
import com.safebuddyfintech23.safebuddy.stores.StoreProductsAdapter.ProductViewHolder

/**
 * This is the addapter for the recycler view in stores fragment
 */
class StoresAdapter(private val storesList: List<StoresModel>) : RecyclerView.Adapter<StoresAdapter.ViewHolder>() {

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var mListener: onItemClickListener

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_stores, parent, false)

        return ViewHolder(view, mListener)
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
    class ViewHolder(ItemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.store_logo)
        val textView: TextView = itemView.findViewById(R.id.store_name)

        //initialize the click on the itemView
        init {
            itemView.setOnClickListener { listener.onItemClick(adapterPosition) }
        }
    }
}

class StoreProductsAdapter(): ListAdapter<Product, ProductViewHolder>(Differ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = StoreProductDescriptionCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class ProductViewHolder(
        private val binding: StoreProductDescriptionCardBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            with(binding) {
                productName.text = product.name
                productPrice.text= product.price
                productImage.load(product.imageUrl)
            }
        }
    }

    companion object Differ: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

}