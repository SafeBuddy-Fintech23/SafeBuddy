package com.safebuddyfintech23.safebuddy.installments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safebuddyfintech23.safebuddy.R

/**
 * This class shows the previous installments when the user clicks [view all] at the homeFragment
 */
class PreviousInstallmentsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_installments, container, false)
    }
}