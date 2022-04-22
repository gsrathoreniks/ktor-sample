package com.example.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

/*
* Extension function for SERIALIZATION
* */
fun Application.configureSerialization() {

    /*
    *  Installing [ContentNegotiation] for Negotiating media-types between
    *  Client and Server, it uses the ACCEPT & CONTENT-TYPE headers for this
    * */
    install(ContentNegotiation) {
        /*Registers the application/json (or another specified contentType)
         content type to the ContentNegotiation plugin using kotlinx.serialization*/
        json()
    }
}