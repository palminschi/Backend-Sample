package dev.palminschi.features.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationReceiveModel(
    val login: String,
    val email: String,
    val password: String
)

@Serializable
data class RegistrationResponseModel(
    val token: String
)