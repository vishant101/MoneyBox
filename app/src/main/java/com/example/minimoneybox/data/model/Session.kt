package com.example.minimoneybox.data.model

data class Session (
    val BearerToken: String,
    val ExternalSessionId: String,
    val SessionExternalId: String,
    val ExpiryInSeconds: String
)