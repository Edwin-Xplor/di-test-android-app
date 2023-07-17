package com.example.koinandroidapp.data.network

import org.json.JSONObject
import retrofit2.Response
import com.example.koinandroidapp.util.ApiException

abstract class SafeApiRequestWrapper {

    suspend fun <T : Any> makeSafeRequest(call: suspend () -> Response<T>): T? {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()
        } else {
            val jsonObject = response.errorBody()?.string()?.let { JSONObject(it) }
            val error = jsonObject?.getString("error")

            val code = response.code()
            throw ApiException(message = "$error with code $code")
        }
    }
}