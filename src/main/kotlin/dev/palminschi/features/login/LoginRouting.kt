package dev.palminschi.features.login

import dev.palminschi.cache.InMemoryCache
import dev.palminschi.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        get("/login") {
            val receive = call.receive(LoginReceiveRemote::class)
            if (InMemoryCache.userList.map { it.login }.contains(receive.login)) {
                val token = UUID.randomUUID().toString()
                val userSession = TokenCache(
                    login = receive.login,
                    token = token
                )
                InMemoryCache.tokens.add(userSession)
                call.respond(LoginResponseRemote(token = token))
                return@get
            }

            call.respond(HttpStatusCode.BadRequest)
        }
    }
}