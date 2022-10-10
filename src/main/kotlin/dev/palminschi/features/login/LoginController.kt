package dev.palminschi.features.login

import dev.palminschi.database.tokens.TokenDTO
import dev.palminschi.database.tokens.TokensDB
import dev.palminschi.database.users.UsersDB
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin() {
        val receiveModel = call.receive<LoginReceiveModel>()
        val userDto = UsersDB.fetchUser(receiveModel.login)
        if (userDto == null) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = "User not found"
            )
        } else {
            if (userDto.password == receiveModel.password) {
                val newToken = UUID.randomUUID().toString()
                TokensDB.insert(
                    TokenDTO(
                        login = receiveModel.login,
                        token = newToken,
                        rowID = UUID.randomUUID().toString()
                    )
                )
                call.respond(
                    status = HttpStatusCode.OK,
                    message = LoginResponseModel(token = newToken)
                )
            } else {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Invalid password"
                )
            }
        }
    }
}