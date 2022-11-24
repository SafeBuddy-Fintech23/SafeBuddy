package com.safebuddyfintech23.safebuddy.home.stores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safebuddyfintech23.safebuddy.R


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
    }
}