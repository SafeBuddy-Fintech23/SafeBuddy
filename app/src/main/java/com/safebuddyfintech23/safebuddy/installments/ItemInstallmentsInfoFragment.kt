package com.safebuddyfintech23.safebuddy.installments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safebuddyfintech23.safebuddy.R

/**
 * Shows the information installments made on a particular item purchased by the user.
 */
class ItemInstallmentsInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_installments_info, container, false)
    }
}