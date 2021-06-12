package id.adisuryantoro.footballclub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import id.adisuryantoro.footballclub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Disabled Dark Mode Android
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        // Finding the Navigation Controller
        val navController = findNavController(R.id.fragNavHost)

        // Setting Navigation Controller with the BottomNavigationView
        binding.bottomNavigationMenu.setupWithNavController(navController)
        binding.bottomNavigationMenu.background = null

        // Setting Up ActionBar with Navigation Controller
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
                R.id.searchFragment,
                R.id.favoritesFragment
            )
        )

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.homeFragment || nd.id == R.id.searchFragment || nd.id == R.id.favoritesFragment
            ) {
                binding.bottomAppBar.visibility = View.VISIBLE
                binding.fabSearch.visibility = View.VISIBLE
            } else {
                binding.bottomAppBar.visibility = View.GONE
                binding.fabSearch.visibility = View.GONE
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fabSearch.setOnClickListener {
            navController.navigate(R.id.searchFragment)
        }
    }
}