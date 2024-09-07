package com.example.newspaper

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.newspaper.data.remote.preferences.PreferencesConstants
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var currentDestinationId = 0
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val sharedPrefs by lazy {
        getSharedPreferences(PreferencesConstants.USER_PREFS, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentDestinationId = destination.id
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_home

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.favoritesFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                else -> {
                    bottomNavigationView.visibility = View.GONE
                }
            }
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                R.id.nav_favorites -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }
                R.id.nav_exit -> {
                    showExitConfirmationDialog()
                    true
                }
                else -> false
            }
        }
    }

    private fun showExitConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Çıkış")
            .setMessage("Uygulamadan çıkmak istediğinize emin misiniz?")
            .setPositiveButton("Evet") { _, _ ->
                signOut()
            }
            .setNegativeButton("Hayır", null)
            .show()
    }

    private fun signOut() {
        firebaseAuth.signOut()
        sharedPrefs.edit().putBoolean(PreferencesConstants.IS_LOGGED_IN, false).apply()
        findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_loginFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
