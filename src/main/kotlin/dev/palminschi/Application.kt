package dev.palminschi

import dev.palminschi.cache.InMemoryCache
import dev.palminschi.features.login.configureLoginRouting
import dev.palminschi.features.registration.configureRegistrationRouting
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import dev.palminschi.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        InMemoryCache.userList
        configureRouting()
        configureLoginRouting()
        configureRegistrationRouting()
        configureSerialization()
    }.start(wait = true)
}
