package com.safebuddyfintech23.safebuddy.home

import android.content.Context
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
import java.util.*

/**
 * Shows user profile and any other information related to the user.
 */

class MyProfileFragment : Fragment() {

    private lateinit var authUserName: String
    private lateinit var userName: TextView
    private lateinit var greetUser: TextView
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
        greetUser = view.findViewById(R.id.txt_greeting_user)
        authUserName = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        userName.text = authUserName //change the name AFTER the user creates profile.

        //navigate to create profile fragment
        btnCreateProfile = view.findViewById(R.id.btn_create_profile)
        btnCreateProfile.setOnClickListener {
            findNavController().navigate(R.id.action_myProfileFragment_to_createProfileFragment)
        }

        /*
        * The following blocks should be removed after the profile has been created.
        * */
        //Setting up the user name
        val sharedPreferences =
            requireActivity().applicationContext.getSharedPreferences("local", Context.MODE_PRIVATE)
        userName.text = sharedPreferences.getString("name1SavedInRealTime_DB", authUserName)

        //Setting greeting message
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        if (currentHour < 12) {
            greetUser.text = getString(R.string.good_morning)
        } else if (currentHour in 12..15){
            greetUser.text = getString(R.string.good_afternoon)
        } else {
            greetUser.text = getString(R.string.good_evening)
        }
    }
}