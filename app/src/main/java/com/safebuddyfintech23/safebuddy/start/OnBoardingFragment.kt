package com.safebuddyfintech23.safebuddy.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.safebuddyfintech23.safebuddy.R

/**
 * In this fragment; we introduce the user to the app.
 * The dependency to be used here is AppIntro : https://github.com/AppIntro/AppIntro
 */
class OnBoardingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }
}