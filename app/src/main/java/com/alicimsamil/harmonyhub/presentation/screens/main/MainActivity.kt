package com.alicimsamil.harmonyhub.presentation.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.alicimsamil.harmonyhub.R
import com.alicimsamil.harmonyhub.core.common.extensions.gone
import com.alicimsamil.harmonyhub.core.common.extensions.visible
import com.alicimsamil.harmonyhub.databinding.ActivityMainBinding
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
        val navController = this.findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    fun hideToolbar(){
        binding.clToolbar.root.gone()
    }

    fun showToolbar(){
        binding.clToolbar.root.visible()
    }

}