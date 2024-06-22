package com.bugbender.memepick.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bugbender.memepick.favorites.databinding.ViewholderEmptyBinding
import com.bugbender.memepick.favorites.databinding.ViewholderMemeBinding

interface FavoritesType {

    fun createViewHolder(parent: ViewGroup, viewModel: ItemActions): FavoritesViewHolder

    object Meme : FavoritesType {

        override fun createViewHolder(parent: ViewGroup, viewModel: ItemActions) =
            FavoritesViewHolder.Meme(
                binding = ViewholderMemeBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                viewModel = viewModel
            )
    }

    object Empty : FavoritesType {

        override fun createViewHolder(parent: ViewGroup, viewModel: ItemActions) =
            FavoritesViewHolder.Empty(
                binding = ViewholderEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}