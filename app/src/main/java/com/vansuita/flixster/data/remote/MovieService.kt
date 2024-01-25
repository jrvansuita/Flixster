package com.vansuita.flixster.data.remote

import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import com.google.gson.Gson
import com.vansuita.flixster.data.model.Movie
import com.vansuita.flixster.data.model.MoviePage
import okhttp3.Headers
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


private const val LIMIT = "limit"
private const val PAGE = "page"
private const val URL = "https://api.themoviedb.org/3/movie/now_playing"
private const val AUTHORIZATION = "Authorization"
private const val TOKEN =
	"Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5ZTNiOTNmYTM5MjNhNDEwYTY5NTUyMWVkM2Y4YTk0OCIsInN1YiI6IjY1YjI1ZmU5YjdiNjlkMDBjOWYzZmQzMSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ._e58Ne7WkjfFhNFr3_vPnfhZpTxdBgNDdUTjf9rI6QQ"

class MovieService {

	suspend fun getPage(page: Int = 0, limit: Int = 10)  = suspendCoroutine<List<Movie>> {
		val params = RequestParams().apply {
			put(PAGE, page)
			put(LIMIT, limit)
		}

		val requestHeaders = RequestHeaders().apply {
			put(AUTHORIZATION, TOKEN)
			put("accept", "application/json");

		}

		AsyncHttpClient().get(URL, requestHeaders, params, object : TextHttpResponseHandler() {
			override fun onFailure(
				statusCode: Int,
				headers: Headers?,
				errorResponse: String?,
				throwable: Throwable?
			) {
				try {
					if (throwable != null) it.resumeWithException(throwable)
					if (errorResponse.isNullOrBlank()) it.resume(emptyList())
				} catch (e: Exception) {
					it.resumeWithException(e)
				}
			}

			override fun onSuccess(statusCode: Int, headers: Headers?, response: String?) {
				try {
					val list = Gson().fromJson(response, MoviePage::class.java)
					it.resume(list.results)
				} catch (e: Exception) {
					it.resumeWithException(e)
				}
			}
		})
	}
}