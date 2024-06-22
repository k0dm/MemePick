package com.bugbender.memepick.favorites.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bugbender.memepick.favorites.databinding.ViewholderEmptyBinding
import com.bugbender.memepick.favorites.databinding.ViewholderMemeBinding

abstract class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    open fun bind(item: FavoritesUi) = Unit

    class Meme(
        private val binding: ViewholderMemeBinding,
        private val viewModel: ItemActions
    ) : FavoritesViewHolder(binding.root) {

        override fun bind(item: FavoritesUi) {
            item.show(binding)
            binding.removeMemeImageButton.setOnClickListener {
                item.remove(viewModel)
            }
        }
    }

    class Empty(binding: ViewholderEmptyBinding) : FavoritesViewHolder(binding.root)
}