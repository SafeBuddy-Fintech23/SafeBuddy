package com.safebuddyfintech23.safebuddy.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.safebuddyfintech23.safebuddy.R

/**
 * Installments get to be handled here.
 */

class HomeFragment : Fragment() {

    //lateinit my views
    private lateinit var userName: TextView
    private lateinit var dayORnightGreetings: TextView
    private lateinit var userProfileImage: ImageView
    private lateinit var duePayment: TextView
    private lateinit var amountPaid: TextView
    private lateinit var amountPaidProgressBar: ProgressBar
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
        /*these last two view must be visible when the user has no upcoming or
        recent installments. Since these views take huge space in the screen, I have
        set their visibility to View.GONE in the layout file.
        */
        upcomingRecentTxt = view.findViewById(R.id.txt_upcoming_recent_home)
        emptyActivityHolderImg = view.findViewById(R.id.img_empty_holder_home)
    }
}