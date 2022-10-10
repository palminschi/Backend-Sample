package dev.palminschi.features.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationReceiveRemote(
    val login: String,
    val email: String,
    val password: String
)

@Serializable
data class RegistrationResponseRemote(
    val token: String
)