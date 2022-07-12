package com.example.starwars.presentation.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwars.R
import com.example.starwars.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setupNavigation()
    }

    private fun setupNavigation() {
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.people_fragment,
            R.id.starships_fragment
        ).build()
        val navController = findNavController(this, R.id.nav_host_fragment)
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        setupWithNavController(binding.bottomNavigation, navController)
    }

}