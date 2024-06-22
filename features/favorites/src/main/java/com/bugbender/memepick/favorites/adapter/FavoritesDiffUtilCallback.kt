package com.bugbender.memepick.favorites.adapter

import androidx.recyclerview.widget.DiffUtil

class FavoritesDiffUtilCallback(
    private val oldList: List<FavoritesUi>,
    private val newList: List<FavoritesUi>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = areItemsTheSame(
        oldItemPosition = oldItemPosition, newItemPosition = newItemPosition
    )
}