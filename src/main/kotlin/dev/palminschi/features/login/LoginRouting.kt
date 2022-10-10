package dev.palminschi.features.login

import dev.palminschi.cache.InMemoryCache
import dev.palminschi.cache.SessionCache
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val receive = call.receive<LoginReceiveRemote>()
            val user = InMemoryCache.userList.firstOrNull { it.login == receive.login }

            if (user == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "User doesn't exist"
                )
            } else {
                if (user.password == receive.password) {
                    val token = UUID.randomUUID().toString()
                    val userSession = SessionCache(
                        login = receive.login,
                        token = token
                    )
                    InMemoryCache.sessions.add(userSession)
                    call.respond(LoginResponseRemote(token = token))
                } else {
                    call.respond(
                        status = HttpStatusCode.BadRequest,
                        message = "Invalid password"
                    )
                }
            }
        }
    }
}