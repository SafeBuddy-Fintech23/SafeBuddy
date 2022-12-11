package com.safebuddyfintech23.safebuddy.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.safebuddyfintech23.safebuddy.R
import java.util.*

/**
 * Installments get to be handled here.
 */

class HomeFragment : Fragment() {
    companion object {
        private const val USERS = "USERS"
    }

    //late init my views
    private lateinit var userName: TextView
    private lateinit var dayORnightGreetings: TextView
    private lateinit var userProfileImage: ImageView
    private lateinit var duePayment: TextView
    private lateinit var amountPaid: TextView
    private lateinit var amountPaidProgressBar: ProgressBar
    private lateinit var viewAllUpcoming: TextView
    private lateinit var viewAllPrevious: TextView
    private lateinit var upcomingRecentTxt: TextView
    private lateinit var emptyActivityHolderImg: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init the views
        userName = view.findViewById(R.id.txt_username_home)
        dayORnightGreetings = view.findViewById(R.id.txt_daynight_greetings_home)
        userProfileImage = view.findViewById(R.id.img_userprofile_home)
        duePayment = view.findViewById(R.id.txt_due_amount_home)
        amountPaid = view.findViewById(R.id.txt_amount_paid_home)
        amountPaidProgressBar = view.findViewById(R.id.pgbr_amount_paid_home)
        viewAllUpcoming = view.findViewById(R.id.txt_viewall_upcoming_home)
        viewAllPrevious = view.findViewById(R.id.txt_viewall_previous_home)
        /*these last two view must be visible when the user has no upcoming or
        recent installments. Since these views take huge space in the screen, I have
        set their visibility to View.GONE in the layout file.
        */
        upcomingRecentTxt = view.findViewById(R.id.txt_upcoming_recent_home)
        emptyActivityHolderImg = view.findViewById(R.id.img_empty_holder_home)

        //Setting up the user name
        val defaultName = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        val sharedPreferences =
            requireActivity().applicationContext.getSharedPreferences("local", Context.MODE_PRIVATE)
        userName.text = sharedPreferences.getString("name1SavedInRealTime_DB", defaultName)

        //Setting greeting message
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        if (currentHour < 12) {
            dayORnightGreetings.text = getString(R.string.good_morning)
        } else if (currentHour in 12..15) {
            dayORnightGreetings.text = getString(R.string.good_afternoon)
        } else {
            dayORnightGreetings.text = getString(R.string.good_evening)
        }



//        if (isProfileCreated(requireActivity())) {
//            //do something if user created profile.
//        }
//
//        //get user data from db
//        val userNodeInDB = FirebaseAuth.getInstance().currentUser?.displayName.toString()
//        val database = FirebaseDatabase.getInstance().getReference(USERS)
//        database.child(userNodeInDB).get().addOnSuccessListener {
//            if (it.exists()) {
//                //get user data from firebase realtime database
//            }
//        }
    }
}