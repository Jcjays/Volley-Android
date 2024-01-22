package com.example.volleysample.repo

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.json.JSONObject

class Repository() : DataSource {
    private val volleyHttpClient: IVolley = VolleyHttpClient

    override suspend fun sampleGet(): Flow<ResponseHandler<Joke>> = callbackFlow {
        send(ResponseHandler.Loading())

        val stringRequest = StringRequest(Request.Method.GET,
            "https://v2.jokeapi.dev/joke/Any?type=single",
            { response ->
                val result = parseAndGetResponse<Joke>(response)

                if (result != null) {
                    trySend(ResponseHandler.Success(result))
                } else {
                    trySend(ResponseHandler.Failure(Exception(""), "No result"))
                }
            },
            { error ->
                trySend(ResponseHandler.Failure(error, "No result"))
            })

        volleyHttpClient.requestQueue().add(stringRequest)

        awaitClose {}
    }


    fun samplePost() {
        val jsonObject = JSONObject()

        jsonObject.put("params1", 0)

        val request = JsonObjectRequest(
            Request.Method.POST,
            "",
            jsonObject,
            { response ->

            },
            { error ->

            },

            )
    }

    private inline fun <reified T> parseAndGetResponse(response: String): T? {
        return try {
            if (response.isBlank())
                throw IllegalArgumentException()
            val typeToken = object : TypeToken<T>() {}.type
            Gson().fromJson(response, typeToken)
        } catch (e: Exception) {
            Log.e("Gson:", "Error parsing the response")
            null
        }
    }
}

