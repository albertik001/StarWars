package com.example.starwars.presentation.ui.fragments.people

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.starwars.R
import com.example.starwars.databinding.FragmentPeopleBinding
import com.example.starwars.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleFragment :
    BaseFragment<FragmentPeopleBinding, PeopleViewModel>(R.layout.fragment_people) {

    override val binding by viewBinding(FragmentPeopleBinding::bind)
    override val viewModel: PeopleViewModel by viewModels()
}