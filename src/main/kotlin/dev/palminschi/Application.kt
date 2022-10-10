package dev.palminschi

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import dev.palminschi.plugins.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSerialization()
    }.start(wait = true)
}
