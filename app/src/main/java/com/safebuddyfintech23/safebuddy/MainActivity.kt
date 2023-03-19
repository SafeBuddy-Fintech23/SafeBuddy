package com.safebuddyfintech23.safebuddy

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.safebuddyfintech23.safebuddy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: MaterialToolbar
    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = binding.bottomNavView
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController


        val topLevelDestinations = setOf(
            R.id.homeFragment,
            R.id.notificationsFragment,
            R.id.storesFragment,
            R.id.myProfileFragment,
            R.id.moreFragment
        )
        val appBarConfiguration = AppBarConfiguration(topLevelDestinations)
        toolbar = binding.toolbar
        toolbar.setupWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)


        // Only show the bottom navigation view when the current destination is
        // [fragments at the home screen]
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    bottomNavView.visibility = View.VISIBLE
                    //when at homeFragment, also hide the [toolbar]
                    toolbar.visibility = View.GONE
                }
                R.id.notificationsFragment -> {
                    bottomNavView.visibility = View.VISIBLE
                    //when at notificationsFragment, also show the [toolbar]
                    toolbar.visibility = View.VISIBLE
                }
                R.id.myProfileFragment -> {
                    bottomNavView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                }
                R.id.storesFragment -> {
                    bottomNavView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                }
                R.id.moreFragment -> {
                    bottomNavView.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                }
                //hide toolbar from the user
                R.id.webViewFragment -> {
                    toolbar.visibility = View.GONE
                    bottomNavView.visibility = View.GONE
                }
                else -> bottomNavView.visibility = View.GONE
            }
        }
    }
}