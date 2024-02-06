package com.vansuita.flixster.data.datasource.remote.api

import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler
import com.google.gson.Gson
import com.vansuita.flixster.BuildConfig
import okhttp3.Headers
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class Api {

	companion object {
		const val LIMIT = "limit"
		const val PAGE = "page"
		const val AUTHORIZATION = "Authorization"
		const val ACCEPT = "accept"
		const val APPLICATION_JSON = "application/json"
	}

	suspend inline fun <reified T> get(path: String) = suspendCoroutine<T> {

		val params = RequestParams().apply {
			put(PAGE, 1)
			put(LIMIT, 30)
		}

		val requestHeaders = RequestHeaders().apply {
			put(AUTHORIZATION, BuildConfig.API_TOKEN)
			put(ACCEPT, APPLICATION_JSON)
		}

		AsyncHttpClient().get(
			"${BuildConfig.BASE_URL}/$path",
			requestHeaders,
			params,
			object : TextHttpResponseHandler() {
				override fun onFailure(
					statusCode: Int,
					headers: Headers?,
					errorResponse: String?,
					throwable: Throwable?
				) {
					try {
						if (throwable != null)
							it.resumeWithException(throwable)

						it.resumeWithException(Exception(errorResponse))
					} catch (e: Exception) {
						it.resumeWithException(e)
					}
				}

				override fun onSuccess(statusCode: Int, headers: Headers?, response: String?) {
					try {
						it.resume(Gson().fromJson(response, T::class.java))
					} catch (e: Exception) {
						it.resumeWithException(e)
					}
				}
			})
	}
}