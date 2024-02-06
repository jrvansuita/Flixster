package com.vansuita.flixster.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.vansuita.flixster.BuildConfig
import com.vansuita.flixster.R
import com.vansuita.flixster.databinding.ActivityDetailBinding
import com.vansuita.flixster.domain.model.Cover


class DetailActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityDetailBinding.inflate(layoutInflater)
	}

	private val viewModel: DetailViewModel by viewModels()


	companion object {
		private const val COVER_TAG = "COVER_TAG"
		fun launch(activity: Activity, coverView: View, titleView: View, cover: Cover) {

			val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
				activity,
				androidx.core.util.Pair(coverView, "cover"),
				androidx.core.util.Pair(titleView, "title")
			)

			activity.startActivity(Intent(activity, DetailActivity::class.java).apply {
				putExtra(COVER_TAG, cover)
			}, options.toBundle())
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		setSupportActionBar(binding.toolbar)

		val cover = intent.extras?.getSerializable(COVER_TAG) as Cover

		Glide
			.with(binding.root.context)
			.load(BuildConfig.COVER_BASE_URL + cover.cover)
			.placeholder(R.drawable.cover_loading)
			.error(R.drawable.cover_error)
			.centerCrop()
			.transform(RoundedCorners(20))
			.into(binding.backdrop)

		binding.title.text = cover.title


		viewModel.detailState.observe(this) { (detail) ->
			binding.genres.text =
				getString(R.string.genres_label, detail.genres.joinToString("\n") { it }).takeIf {
					detail.genres.isNotEmpty()
				}
			binding.author.text =
				getString(R.string.authors_label, detail.authors.joinToString("\n") { it }).takeIf {
					detail.authors.isNotEmpty()
				}
			binding.type.text = getString(R.string.type_label, detail.type).takeIf {
				detail.type.isNotEmpty()
			}
			binding.overview.text = detail.overview
		}

		viewModel.load(cover)
	}
}