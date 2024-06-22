package com.bugbender.memepick.memes.presentation

import android.view.View
import androidx.core.content.ContextCompat
import com.bugbender.memepick.memes.R
import com.bugbender.memepick.memes.databinding.FragmentMemesBinding
import com.bumptech.glide.Glide

interface MemesUiState {

    fun show(binding: FragmentMemesBinding) = Unit

    data class Base(
        private val postLink: String,
        private val subreddit: String,
        private val title: String,
        private val url: String,
        private val nsfw: Boolean,
        private val author: String,
    ) : MemesUiState {

        override fun show(binding: FragmentMemesBinding) = with(binding) {
            memeInfoLL.visibility = View.VISIBLE
            authorNameTextView.text =
                authorNameTextView.context.getString(R.string.meme_author, author)
            memeTitleTextView.text =
                memeTitleTextView.context.getString(R.string.meme_title, title)
            subredditTextView.text = subreddit

            Glide.with(memeImageView.context).load(url)
                .thumbnail(Glide.with(memeImageView.context).load(R.drawable.tenor).fitCenter())
                .fitCenter()
                .into(memeImageView);
            messageTextView.visibility = View.GONE

            shareViaTelegramButton.visibility = View.VISIBLE
            favoriteButton.visibility = View.VISIBLE
            memeButton.isEnabled = true
            memeButton.setBackgroundColor(
                ContextCompat.getColor(
                    memeButton.context,
                    com.bugbender.memepick.theme.R.color.black
                )
            )
        }
    }

    data class Error(private val message: String) : MemesUiState {

        override fun show(binding: FragmentMemesBinding) = with(binding) {
            memeInfoLL.visibility = View.GONE

            Glide.with(memeImageView.context).load(com.bugbender.memepick.theme.R.drawable.error)
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

            Glide.with(memeImageView.context).load(R.drawable.tenor).into(memeImageView)
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