package com.safebuddyfintech23.safebuddy

import android.os.Bundle
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
            R.id.myProfileFragment,
            R.id.moreFragment
        )
        val appBarConfiguration = AppBarConfiguration(topLevelDestinations)
        toolbar = binding.toolbar
        toolbar.setupWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

    }
}