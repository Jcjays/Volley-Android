package com.example.volleysample.repo

import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.example.volleysample.App
import java.util.concurrent.TimeUnit

object VolleyHttpClient : IVolley {

    private val cache = DiskBasedCache(App.getInstance().cacheDir, 1024 * 1024)

    private val network = BasicNetwork(HurlStack())

    private val globalRetryPolicy = DefaultRetryPolicy(
        5000,  // Timeout in milliseconds (e.g., 5 seconds)
        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
    )

    override fun requestQueue(): RequestQueue {
        return RequestQueue(cache, network).apply {
            start()
        }
    }

}