package com.example.retrofitsample

import retrofit2.http.GET

interface SampleApiService {
    @GET("/enotest")
    suspend fun get(): String
}