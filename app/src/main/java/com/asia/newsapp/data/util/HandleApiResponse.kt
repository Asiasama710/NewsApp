package com.asia.newsapp.data.util

import android.content.res.Resources
import com.asia.newsapp.domain.util.BadRequestException
import com.asia.newsapp.domain.util.InternalServerException
import com.asia.newsapp.domain.util.NetworkException
import com.asia.newsapp.domain.util.TooManyRequestsException
import com.asia.newsapp.domain.util.UnAuthorizedException
import retrofit2.Response

fun <T> handleApiResponse(response: Response<T>): T {
    if (response.isSuccessful) {
        return response.body() ?: throw Resources.NotFoundException()
    } else {
        when (response.code()) {
            400 -> throw BadRequestException()
            401 -> throw UnAuthorizedException()
            429 -> throw TooManyRequestsException()
            500 -> throw InternalServerException()
            else -> throw NetworkException()
        }
    }
}