package com.example.volleysample.repo

import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun sampleGet(): Flow<ResponseHandler<Joke>>
}