package dev.palminschi.cache

import dev.palminschi.features.registration.RegistrationReceiveModel

/**
 * Only for testing server without database.
 * **/
data class SessionCache(
    val login: String,
    val token: String
)

object InMemoryCache {
    val USER_LIST: MutableList<RegistrationReceiveModel> = mutableListOf()
    val sessions: MutableList<SessionCache> = mutableListOf()
}