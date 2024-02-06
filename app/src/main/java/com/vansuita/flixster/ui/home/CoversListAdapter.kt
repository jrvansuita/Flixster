package com.vansuita.flixster.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vansuita.flixster.BuildConfig
import com.vansuita.flixster.R
import com.vansuita.flixster.databinding.CoverItemBinding
import com.vansuita.flixster.domain.model.Cover


class CoversListAdapter(private val onClick: (CoverItemBinding, Cover) -> Unit) :
	RecyclerView.Adapter<CoversListAdapter.ViewHolder>() {

	private val data: MutableList<Cover> = mutableListOf()

	inner class ViewHolder(private val binding: CoverItemBinding) :
		RecyclerView.ViewHolder(binding.root) {

		fun bind(cover: Cover) {
			Glide
				.with(binding.root.context)
				.load(BuildConfig.COVER_BASE_URL + cover.cover)
				.placeholder(R.drawable.cover_loading)
				.error(R.drawable.cover_error)
				.centerCrop()
				.transform(RoundedCorners(20))
				.into(binding.cover)

			binding.title.text = cover.title
			binding.root.setOnClickListener {
				onClick(binding, cover)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			CoverItemBinding.inflate(
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

	fun addMore(coverList: List<Cover>) {
		val lastPosition = Integer.max(0, itemCount - 1)
		data.addAll(coverList)
		notifyItemRangeInserted(lastPosition, coverList.size)
	}

}