package com.safebuddyfintech23.safebuddy.stores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safebuddyfintech23.safebuddy.R

/**
 * Displays stores to the user, allowing the user to select the preferred store.
 */
class StoresFragment : Fragment() {

    private lateinit var storesRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stores, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        storesRecyclerView = view.findViewById(R.id.stores_recycler_view)
        storesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        //get data
        val data = StoreData().loadStores()
        val adapter = StoresAdapter(data)
        storesRecyclerView.adapter = adapter
        storesRecyclerView.setHasFixedSize(true)

        //get the urls
        val storesWebsites = StoreData().loadWebsites()
        val storeTitles = StoreData().loadTitles()

        //itemClick
        adapter.setOnItemClickListener(object : StoresAdapter.onItemClickListener {
            override fun onItemClick(position: Int) { //do actions here when the user clicks a store
                //get weblink position
                val myUrl = storesWebsites[position]
                val webTitle = storeTitles[position]

                //this was for testing the itemClick
//                Toast.makeText(requireContext(), myUrl, Toast.LENGTH_LONG).show()
//                val intent: Intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(myUrl)
//                startActivity(intent)

                val bundle = bundleOf(
                    "WEB URL" to myUrl,
                    "WEB TITLE" to webTitle
                )
                findNavController().navigate(R.id.action_storesFragment_to_webViewFragment, bundle)

            }
        })
    }
}