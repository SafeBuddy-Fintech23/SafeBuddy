package com.safebuddyfintech23.safebuddy.start

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.safebuddyfintech23.safebuddy.R

/**
 * This class handles the events in the onboarding screen.
 *
 * we must check if the user is signed up or not before displaying it
 */
class OnBoardingActivity : AppIntro2() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            // Make sure you don't call setContentView!

            /**
             * Shared pref: Bool to be used here to check if it is first time using the app
             */

            // Call addSlide passing your Fragments.
            // You can use AppIntroFragment to use a pre-built fragment
            addSlide(
                AppIntroFragment.createInstance(
                title = "Welcome to Safe Buddy",
                description = "Complete the signing up process.",
                imageDrawable = R.drawable.img_slide1_onboarding,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.black
            ))
            addSlide(
                AppIntroFragment.createInstance(
                title = "Set up your profile",
                description = "After signing in, please take time to setup your profile.",
                imageDrawable = R.drawable.img_slide2_onboarding,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.black
            ))
            addSlide(
                AppIntroFragment.createInstance(
                title = "Let's get started...",
                //description = "",
                imageDrawable = R.drawable.img_slide3_onboarding,
                titleColorRes = R.color.white,
                descriptionColorRes = R.color.white,
                backgroundColorRes = R.color.black
            ))

            setTransformer(AppIntroPageTransformerType.Fade)
            isColorTransitionsEnabled = true


            //switch from dot indicator to progress indicator
            setProgressIndicator()

            // Enable vibration and set duration in ms
            isVibrate = true
            vibrateDuration = 50L

            //set the skip button to back arrow button
            isWizardMode = true

            //hide the Status Bar
            setImmersiveMode()
        }

        override fun onSkipPressed(currentFragment: Fragment?) {
            super.onSkipPressed(currentFragment)
            // Decide what to do when the user clicks on "Skip"
            goToLoginActivity()
            finish()
        }

        override fun onDonePressed(currentFragment: Fragment?) {
            super.onDonePressed(currentFragment)
            // Decide what to do when the user clicks on "Done"
            goToLoginActivity()
            finish()
        }

    private fun goToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}