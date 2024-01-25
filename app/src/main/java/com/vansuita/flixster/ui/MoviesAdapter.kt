package com.vansuita.flixster.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vansuita.flixster.R
import com.vansuita.flixster.data.model.Movie
import com.vansuita.flixster.databinding.MovieItemBinding
import java.lang.Integer.max


private const val COVER_BASE_URL = "https://image.tmdb.org/t/p/w500/"

class MoviesAdapter :
	RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

	private val data: MutableList<Movie> = mutableListOf()

	inner class ViewHolder(private val binding: MovieItemBinding) :
		RecyclerView.ViewHolder(binding.root) {

		fun bind(movie: Movie) {
			Glide
				.with(binding.root.context)
				.load(COVER_BASE_URL + movie.cover)
				.placeholder(R.drawable.cover_loading)
				.error(R.drawable.cover_error)
				.centerCrop()
				.into(binding.cover);

			binding.title.text = movie.title
			binding.overview.text = movie.overview
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			MovieItemBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun getItemCount() = data.size

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.bind(data[position])
	}

	fun addMore(movies: List<Movie>) {
		val lastPosition = max(0, itemCount - 1)
		data.addAll(movies)
		notifyItemRangeInserted(lastPosition, movies.size)
	}

}
