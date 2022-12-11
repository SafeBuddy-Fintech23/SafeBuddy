package com.safebuddyfintech23.safebuddy.userprofile

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.safebuddyfintech23.safebuddy.R
import com.safebuddyfintech23.safebuddy.userprofile.model.User

class CreateProfileFragment : Fragment() {
    companion object {
        private const val USERS = "USERS"
        private val CurrentUserName = FirebaseAuth.getInstance().currentUser?.displayName.toString()
        private var PROFILE_PICS = "USER PROFILE PICTURES/$CurrentUserName"
    }

    //late init views
    private lateinit var profileImgView: ImageView
    private lateinit var setProfileTxt: TextView
    private lateinit var firstNameEdtTxt: EditText
    private lateinit var lastNameEdtTxt: EditText
    private lateinit var bankIdEdtTxt: EditText
    private lateinit var locationEdtTxt: EditText
    private lateinit var btnSave: Button
    private lateinit var dataSavedTxt: TextView
    private lateinit var customerSupportTxt: TextView
    private lateinit var progressBar: ProgressBar

    private var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init views
        profileImgView = view.findViewById(R.id.img_user_profile)
        setProfileTxt = view.findViewById(R.id.txt_set_profile)
        firstNameEdtTxt = view.findViewById(R.id.edit_txt_first_name)
        lastNameEdtTxt = view.findViewById(R.id.edit_txt_last_name)
        bankIdEdtTxt = view.findViewById(R.id.edit_txt_bank_id)
        locationEdtTxt = view.findViewById(R.id.edit_txt_location)
        btnSave = view.findViewById(R.id.btn_continue_create)
        dataSavedTxt = view.findViewById(R.id.txt_notify_data_saved)
        customerSupportTxt = view.findViewById(R.id.txt_customer_support)
        progressBar = view.findViewById(R.id.progress_bar_create_profile)

        progressBar.isEnabled = false
        progressBar.visibility = View.GONE

        dataSavedTxt.visibility = View.GONE
        //Getting Profile Pic
        // Click listeners on [profileImgView] and [setProfileTxt] to get user profile image.
        //If one of them is clicked, [getProfileImg()]
        profileImgView.setOnClickListener { getProfileImg() }
        setProfileTxt.setOnClickListener { getProfileImg() }

        // var for Getting text data
        var inputFirstName: String?
        var inputLastName: String?
        var inputBankID: String?
        var inputUserLocation: String?

        //Saving User Data
        btnSave.setOnClickListener {
            //Request the user to wait while saving data
            Toast.makeText(requireContext(), getString(R.string.plz_wait), Toast.LENGTH_LONG).show()

            //Check if User Data is valid
            if (imageUri == null) {
                Toast.makeText(requireContext(), getString(R.string.err_no_image), Toast.LENGTH_LONG).show()
            }
            //Raise errors where necessary if any field is empty
            else if (firstNameEdtTxt.text.isEmpty()) {
                firstNameEdtTxt.error = getString(R.string.err_firstname)
            } else if (lastNameEdtTxt.text.isEmpty()) {
                lastNameEdtTxt.error = getString(R.string.err_lastname)
            } else if (bankIdEdtTxt.text.isEmpty()) {
                bankIdEdtTxt.error = getString(R.string.err_bank_id)
            } else if (locationEdtTxt.text.isEmpty()) {
                locationEdtTxt.error = getString(R.string.err_user_location)
            } else {
                //input data
                inputFirstName = firstNameEdtTxt.text.toString()
                inputLastName = lastNameEdtTxt.text.toString()
                inputBankID = bankIdEdtTxt.text.toString()
                inputUserLocation = locationEdtTxt.text.toString()


                progressBar.isEnabled = true
                progressBar.visibility = View.VISIBLE

                //then upload it
                uploadUserData(
                    requireContext(),
                    imageUri,
                    inputFirstName!!,
                    inputLastName!!,
                    inputBankID!!,
                    inputUserLocation!!
                )

            }
        }

        customerSupportTxt.setOnClickListener {
            //Create an intent to open web or send email

            //this toast should be removed later
            Toast.makeText(requireContext(), "Customer Support Unavailable", Toast.LENGTH_SHORT)
                .show()
        }
    }

    //ToDo: Fix Deprecated
    //Function to select image from device storage
    private fun getProfileImg() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 24)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 24 && data != null && data.data != null) {
            imageUri = data.data!!
            profileImgView.setImageURI(imageUri)
        }
    }

    //Uploads user data to Firebase Database
    private fun uploadUserData(
        context: Context,
        uri: Uri?,
        firstName: String,
        lastName: String,
        bankID: String,
        location: String,
    ) { //if [CurrentUserName] is "null", set it to [firstName]+[lastName]
        //this is because when user signs in using phone, [CurrentUserName] will be "null"
        if (CurrentUserName == "null") PROFILE_PICS = firstName + lastName

        // First, upload the image to Firebase Storage
        val storageReference = FirebaseStorage.getInstance().getReference(PROFILE_PICS)
        //store the image then get its downloadURL, using [.addOnSuccessListener]
        if (uri != null) {
            storageReference.putFile(uri).addOnSuccessListener(requireActivity()) {

                //get downloadURL from metadata,  then addOnSuccessListener to save the downloadURL
                //to the database
                    taskSnapshot ->
                taskSnapshot.metadata!!.reference!!.downloadUrl.addOnSuccessListener { downloadURL ->

                    //save input to the database
                    val user = User(
                        downloadURL.toString(), firstName, lastName, bankID, location
                    )

                    //ref for the database
                    val database = FirebaseDatabase.getInstance().getReference(USERS)

                    //Create a node to the database for the user
                    val userNodeInDB = firstName + lastName

                    //now we input to the database
                    database.child(userNodeInDB).setValue(user).addOnSuccessListener {
                        // After the data has been uploaded; additional activities to be done here

                        // Notify the user that the profile has been created
                        profileImgView.isClickable = false
                        setProfileTxt.isClickable = false
                        firstNameEdtTxt.isEnabled = false
                        lastNameEdtTxt.isEnabled = false
                        bankIdEdtTxt.isEnabled = false
                        locationEdtTxt.isEnabled = false

                        progressBar.isEnabled = false
                        progressBar.visibility = View.GONE

                        btnSave.setOnClickListener {
                            Toast.makeText(
                                context, getString(R.string.profile_saved), Toast.LENGTH_SHORT
                            ).show()
                        }
                        dataSavedTxt.visibility = View.VISIBLE


                        // ToDo: Change the views at the profile fragment, with reference to a shared pref*
                        val sharedPref =
                            requireActivity().applicationContext.getSharedPreferences("local", Context.MODE_PRIVATE)
                        val editor = sharedPref.edit()
                        editor?.apply{
                            putBoolean("isProfileCreated", true)
                            putString("name1SavedInRealTime_DB", firstName)
                            apply()
                        }
                    }
                }
            }.addOnFailureListener {
                //if the WHOLE process of uploading data fails, notify the user
                Toast.makeText(
                    context, getString(R.string.profile_not_saved), Toast.LENGTH_SHORT
                ).show()
                dataSavedTxt.text = getString(R.string.profile_not_created)
                dataSavedTxt.visibility = View.VISIBLE

                progressBar.isEnabled = false
                progressBar.visibility = View.GONE

            }
        }
    }
}


fun isProfileCreated(reqActivity: FragmentActivity): Boolean {
    val sharedPreferences =
        reqActivity.applicationContext.getSharedPreferences("local", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isProfileCreated", false)
}