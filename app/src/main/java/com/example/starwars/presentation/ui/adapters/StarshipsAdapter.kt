package com.example.starwars.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.ItemStarshipBinding
import com.example.starwars.presentation.base.BaseDiffUtil
import com.example.starwars.presentation.models.ResultUI

class StarshipsAdapter(private val onItemClick: ((id: Int) -> Unit)? = null) :
    PagingDataAdapter<ResultUI, StarshipsAdapter.StarshipsViewHolder>(BaseDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipsViewHolder {
        return StarshipsViewHolder(
            ItemStarshipBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StarshipsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class StarshipsViewHolder(private val binding: ItemStarshipBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultUI: ResultUI) {
            binding.starshipsName.text = resultUI.name
            binding.starshipsModel.text = resultUI.model
            binding.starshipsCapacity.text = resultUI.cargoCapacity
            binding.starshipsPrice.text = resultUI.costInCredits

            itemView.setOnClickListener {
                onItemClick?.invoke(resultUI.url.filter(Char::isDigit).toInt())
            }
        }
    }
}