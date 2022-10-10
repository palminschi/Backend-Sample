package dev.palminschi

import dev.palminschi.features.login.configureLoginRouting
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import dev.palminschi.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureLoginRouting()
        configureSerialization()
    }.start(wait = true)
}
