package com.example.starwars.presentation.ui.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwars.R
import com.example.starwars.databinding.FragmentStarshipsBinding
import com.example.starwars.presentation.base.BaseFragment
import com.example.starwars.presentation.ui.adapters.StarshipsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StarshipsFragment :
    BaseFragment<FragmentStarshipsBinding, StarshipsViewModel>(R.layout.fragment_starships) {

    override val binding by viewBinding(FragmentStarshipsBinding::bind)
    override val viewModel: StarshipsViewModel by viewModels()
    private val starshipsAdapter = StarshipsAdapter()

    override fun initialize() {
        binding.rvStarships.adapter = starshipsAdapter
        binding.rvStarships.layoutManager = LinearLayoutManager(context)

    }

    override fun setupListeners() {

    }

    override fun launchObservers() {
        viewModel.fetchPagedStarships().spectatePaging(success = {
            starshipsAdapter.submitData(it)
        })
    }
}