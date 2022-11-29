package com.safebuddyfintech23.safebuddy.stores

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.safebuddyfintech23.safebuddy.R
import com.safebuddyfintech23.safebuddy.databinding.FragmentStoreProductBinding

class StoreProductFragment : Fragment() {

    private val viewModel: StoreProductViewModel by activityViewModels()
    private var _binding: FragmentStoreProductBinding? = null
    private val binding get() = _binding!!
    private var storeProductsAdapter = StoreProductsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoreProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = storeProductsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModel.products.observe(viewLifecycleOwner) {
            storeProductsAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}