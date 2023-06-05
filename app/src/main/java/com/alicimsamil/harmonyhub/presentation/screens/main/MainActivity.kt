package com.alicimsamil.harmonyhub.presentation.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.alicimsamil.harmonyhub.R
import com.alicimsamil.harmonyhub.databinding.ActivityMainBinding
import com.alicimsamil.harmonyhub.presentation.screens.firstscreen.FirstFragment
import com.alicimsamil.harmonyhub.presentation.screens.fourthscreen.FourthFragment
import com.alicimsamil.harmonyhub.presentation.screens.secondscreen.SecondFragment
import com.alicimsamil.harmonyhub.presentation.screens.thirdscreen.ThirdFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

    private var state: MainUiState = MainUiState()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            state.splashAnimationState
        }
        splashScreenAnimationExitListener()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeUiState()
        initBottomNavigation()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collectLatest {
                    state = it
                }
            }
        }
    }

    private fun initBottomNavigation() {
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()
        val fourthFragment = FourthFragment()

        setCurrentFragment(firstFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> setCurrentFragment(firstFragment)
                R.id.page_2 -> setCurrentFragment(secondFragment)
                R.id.page_3 -> setCurrentFragment(thirdFragment)
                R.id.page_4 -> setCurrentFragment(fourthFragment)
            }
            true
        }
    }


    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            addToBackStack(null)
            replace(R.id.flFragment, fragment)
            commit()
        }
}