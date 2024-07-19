package com.bugbender.memepick.memes.presentation

import android.view.View
import androidx.core.content.ContextCompat
import com.bugbender.memepick.data.favorites.api.FavoriteMeme
import com.bugbender.memepick.data.favorites.api.FavoritesRepository
import com.bugbender.memepick.memes.R
import com.bugbender.memepick.memes.databinding.FragmentMemesBinding
import com.bumptech.glide.Glide

interface MemesUiState {

    fun show(binding: FragmentMemesBinding) = Unit

    suspend fun changeFavorite(favoritesRepository: FavoritesRepository.AddAndRemove): MemesUiState =
        this

    abstract class Base(
        private val postLink: String,
        private val subreddit: String,
        private val title: String,
        private val url: String,
        private val nsfw: Boolean,
        private val author: String,
        private val imageData: ByteArray,
        private val isFavorite: Boolean
    ) : MemesUiState {

        override fun show(binding: FragmentMemesBinding) = with(binding) {
            memeInfoLL.visibility = View.VISIBLE
            authorNameTextView.text =
                authorNameTextView.context.getString(R.string.meme_author, author)
            memeTitleTextView.text =
                memeTitleTextView.context.getString(R.string.meme_title, title)
            subredditTextView.text = subreddit

            val context = memeImageView.context
            Glide.with(context)
                .load(url)
                .thumbnail(Glide.with(context).load(R.drawable.still_waiting))
                .fitCenter()
                .into(memeImageView)

            messageTextView.visibility = View.GONE

            shareViaTelegramButton.visibility = View.VISIBLE
            favoriteButton.visibility = View.VISIBLE
            favoriteButton.setImageResource(if (isFavorite) R.drawable.favorite_filled_48 else R.drawable.favorite_outline_48)
            memeButton.isEnabled = true
            memeButton.setBackgroundColor(
                ContextCompat.getColor(
                    memeButton.context,
                    com.bugbender.memepick.theme.R.color.black
                )
            )
        }

        override suspend fun changeFavorite(favoritesRepository: FavoritesRepository.AddAndRemove): MemesUiState {
            return if (isFavorite) {
                favoritesRepository.removeMeme(postLink = postLink)
                NotFavorite(postLink, subreddit, title, url, nsfw, author, imageData)
            } else {
                favoritesRepository.addMeme(
                    userId = "", //todo change
                    FavoriteMeme(
                        postLink,
                        subreddit,
                        title,
                        url,
                        nsfw,
                        author,
                        imageData
                    )
                )
                Favorite(postLink, subreddit, title, url, nsfw, author, imageData)
            }
        }
    }

    data class NotFavorite(
        private val postLink: String,
        private val subreddit: String,
        private val title: String,
        private val url: String,
        private val nsfw: Boolean,
        private val author: String,
        private val imageData: ByteArray,
    ) : Base(postLink, subreddit, title, url, nsfw, author, imageData, isFavorite = false)

    data class Favorite(
        private val postLink: String,
        private val subreddit: String,
        private val title: String,
        private val url: String,
        private val nsfw: Boolean,
        private val author: String,
        private val imageData: ByteArray,
    ) : Base(postLink, subreddit, title, url, nsfw, author, imageData, isFavorite = true)

    data class Error(private val message: String) : MemesUiState {

        override fun show(binding: FragmentMemesBinding) = with(binding) {
            memeInfoLL.visibility = View.GONE

            Glide.with(memeImageView.context)
                .load(com.bugbender.memepick.theme.R.drawable.error)
                .fitCenter()
                .into(memeImageView)

            messageTextView.setTextColor(
                ContextCompat.getColor(
                    messageTextView.context,
                    com.bugbender.memepick.theme.R.color.red
                )
            )
            messageTextView.text = message
            messageTextView.visibility = View.VISIBLE

            shareViaTelegramButton.visibility = View.GONE
            favoriteButton.visibility = View.GONE
            memeButton.isEnabled = true
        }
    }

    object Progress : MemesUiState {

        override fun show(binding: FragmentMemesBinding) = with(binding) {
            memeInfoLL.visibility = View.GONE

            val context = memeImageView.context

            Glide.with(context)
                .load(R.drawable.tenor)
                .fitCenter()
                .into(memeImageView)

            messageTextView.setTextColor(
                ContextCompat.getColor(
                    messageTextView.context,
                    com.bugbender.memepick.theme.R.color.black
                )
            )
            messageTextView.setText(R.string.i_said_wait)
            messageTextView.visibility = View.VISIBLE

            shareViaTelegramButton.visibility = View.GONE
            favoriteButton.visibility = View.GONE
            memeButton.isEnabled = false
            memeButton.setBackgroundColor(
                ContextCompat.getColor(
                    memeButton.context,
                    com.bugbender.memepick.theme.R.color.gray
                )
            )
        }
    }
}