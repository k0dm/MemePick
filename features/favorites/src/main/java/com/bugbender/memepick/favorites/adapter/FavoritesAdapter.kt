package com.bugbender.memepick.favorites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FavoritesAdapter(
    private val viewModel: ItemActions,
    private val typeList: List<FavoritesType> = listOf(FavoritesType.Meme, FavoritesType.Empty)
) : RecyclerView.Adapter<FavoritesViewHolder>(), UpdateFavoritesAdapter {

    private val favoritesUiList = mutableListOf<FavoritesUi>()

    override fun update(list: List<FavoritesUi>) {
        val diffResult = DiffUtil.calculateDiff(
            FavoritesDiffUtilCallback(oldList = favoritesUiList, newList = list)
        )
        favoritesUiList.clear()
        favoritesUiList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) = typeList.indexOf(favoritesUiList[position].type())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        typeList[viewType].createViewHolder(parent = parent, viewModel = viewModel)

    override fun getItemCount() = favoritesUiList.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) =
        holder.bind(favoritesUiList[position])
}

