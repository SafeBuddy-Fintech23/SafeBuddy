package com.safebuddyfintech23.safebuddy.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.safebuddyfintech23.safebuddy.R

/**
 * Shows user profile and any other information related to the user.
 */

class MyProfileFragment : Fragment() {

    private lateinit var authUserName: String
    private lateinit var userName: TextView
    private lateinit var btnCreateProfile: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userName = view.findViewById(R.id.auth_username)
        authUserName = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        userName.text = authUserName //change the name AFTER the user creates profile.

        //navigate to create profile fragment
        btnCreateProfile = view.findViewById(R.id.btn_create_profile)
        btnCreateProfile.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_createProfileFragment)
        }
    }
}