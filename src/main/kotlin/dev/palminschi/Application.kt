package dev.palminschi

import dev.palminschi.cache.InMemoryCache
import dev.palminschi.features.login.configureLoginRouting
import dev.palminschi.features.registration.configureRegistrationRouting
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import dev.palminschi.plugins.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect(
        url = "jdbc:postgresql://localhost:8080/backend-sample",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "m0236125"
    )
    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        InMemoryCache.USER_LIST
        configureRouting()
        configureLoginRouting()
        configureRegistrationRouting()
        configureSerialization()
    }.start(wait = true)
}
