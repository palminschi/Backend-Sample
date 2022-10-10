package dev.palminschi.features.registration

import dev.palminschi.cache.InMemoryCache
import dev.palminschi.cache.SessionCache
import dev.palminschi.utils.isEmailValid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRegistrationRouting() {
    routing {
        post("/registration") {
            val receive = call.receive(RegistrationReceiveRemote::class)
            if (!receive.email.isEmailValid()) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Email is not valid"
                )
                return@post
            } else if (InMemoryCache.userList.map { it.login }.contains(receive.login)) {
                call.respond(
                    status = HttpStatusCode.Conflict,
                    message = "User already exists"
                )
                return@post
            } else if (InMemoryCache.userList.map { it.email }.contains(receive.email)) {
                call.respond(
                    status = HttpStatusCode.Conflict,
                    message = "Email already registered"
                )
                return@post
            } else {
                val token = UUID.randomUUID().toString()
                InMemoryCache.userList.add(receive)
                call.respond(
                    status = HttpStatusCode.OK,
                    message = RegistrationResponseRemote(
                        token = token
                    )
                )
            }

        }
    }
}
