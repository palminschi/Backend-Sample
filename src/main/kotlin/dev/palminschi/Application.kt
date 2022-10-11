package dev.palminschi

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.palminschi.cache.InMemoryCache
import dev.palminschi.features.login.configureLoginRouting
import dev.palminschi.features.registration.configureRegistrationRouting
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import dev.palminschi.plugins.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {
    val config = HikariConfig("/hikari.properties")
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        configureRouting()
        configureLoginRouting()
        configureRegistrationRouting()
        configureSerialization()
    }.start(wait = true)
}
