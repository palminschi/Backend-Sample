package dev.palminschi.features.registration

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRegistrationRouting() {
    routing {
        post("/registration") {
            val controller = RegistrationController(call)
            controller.registerNewUser()
        }
    }
}
