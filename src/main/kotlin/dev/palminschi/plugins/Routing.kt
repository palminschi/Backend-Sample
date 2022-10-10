package dev.palminschi.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable

@Serializable
data class Model(val title: String)

fun Application.configureRouting() {
    routing {
        get("/") {
            val list = listOf(
                Model(title = "Hi"),
                Model(title = "Hello")
            )
            call.respond(
                message = list
            )
        }
    }
}
