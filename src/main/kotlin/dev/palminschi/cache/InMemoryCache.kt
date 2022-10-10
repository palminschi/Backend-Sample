package dev.palminschi.cache

import dev.palminschi.features.registration.RegistrationReceiveRemote


data class SessionCache(
    val login: String,
    val token: String
)

object InMemoryCache {
    val userList: MutableList<RegistrationReceiveRemote> = mutableListOf()
    val sessions: MutableList<SessionCache> = mutableListOf()
}