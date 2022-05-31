package com.example.favoritecats.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*

import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorInstance {

    private val client = HttpClient(CIO) {
        install(DefaultRequest) {
            header(API_KEY, TOKEN)
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private val postClient = HttpClient(CIO){
        install(DefaultRequest) {
            header(API_KEY, TOKEN)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    fun postClient(): HttpClient {
        return postClient
    }

    fun getClient(): HttpClient{
        return client
    }
    companion object {
        const val BASE_URL = "https://api.thecatapi.com/v1"
        const val API_KEY = "x-api-key"
        const val TOKEN = "834fffcf-e5fc-46fb-af03-6ebd407683b1"
    }

}