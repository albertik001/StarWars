package com.example.starwars.presentation.ui.fragments.starships

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwars.R
import com.example.starwars.databinding.FragmentStarshipsBinding
import com.example.starwars.presentation.base.BaseFragment
import com.example.starwars.presentation.extensions.directionsSafeNavigation
import com.example.starwars.presentation.ui.adapters.StarshipsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StarshipsFragment :
    BaseFragment<FragmentStarshipsBinding, StarshipsViewModel>(R.layout.fragment_starships) {

    override val binding by viewBinding(FragmentStarshipsBinding::bind)
    override val viewModel: StarshipsViewModel by viewModels()
    private val starshipsAdapter = StarshipsAdapter(this::onItemClick)


    override fun initialize() {
        binding.rvStarships.adapter = starshipsAdapter
        binding.rvStarships.layoutManager = LinearLayoutManager(context)
    }


    override fun launchObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.fetchPagedStarships().spectatePaging(success = {
                    starshipsAdapter.submitData(it)
                })
            }
        }
    }

    private fun onItemClick(id: Int) {
        findNavController().directionsSafeNavigation(
            StarshipsFragmentDirections.actionStarshipsFragmentToStarshipsDetailFragment(
                id
            )
        )
    }

}