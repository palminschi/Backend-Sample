package dev.palminschi.cache

import dev.palminschi.features.registration.RegistrationReceiveRemote


data class TokenCache(
    val login: String,
    val token: String
)

object InMemoryCache {
    val userList: MutableList<RegistrationReceiveRemote> = mutableListOf()
    val tokens: MutableList<TokenCache> = mutableListOf()
}