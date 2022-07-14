package com.example.starwars.presentation.ui.fragments.starships.detail

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwars.R
import com.example.starwars.databinding.FragmentStarshipBinding
import com.example.starwars.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StarshipFragment :
    BaseFragment<FragmentStarshipBinding, StarshipViewModel>(R.layout.fragment_starship) {

    override val binding by viewBinding(FragmentStarshipBinding::bind)
    override val viewModel by viewModels<StarshipViewModel>()
    private val args by navArgs<StarshipFragmentArgs>()

    override fun establishRequest() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchStarship(args.id)
            }
        }
    }

    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.starshipsState.spectateUiState(success = {
                    binding.manufacturer.text = it.manufacturer
                })
            }
        }
    }

}