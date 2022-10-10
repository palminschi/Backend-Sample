package dev.palminschi.features.registration

import dev.palminschi.database.tokens.TokenDTO
import dev.palminschi.database.tokens.TokensDB
import dev.palminschi.database.users.UserDTO
import dev.palminschi.database.users.UsersDB
import dev.palminschi.utils.isEmailValid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class RegistrationController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val registrationReceiveModel = call.receive(RegistrationReceiveModel::class)
        if (!registrationReceiveModel.email.isEmailValid()) {
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = "Email is not valid"
            )
        }
        val userDto = UsersDB.fetchUser(registrationReceiveModel.login)
        if (userDto != null) {
            call.respond(
                status = HttpStatusCode.Conflict,
                message = "User already exists"
            )
        } else {
            val newToken = UUID.randomUUID().toString()
            UsersDB.insert(
                UserDTO(
                    login = registrationReceiveModel.login,
                    password = registrationReceiveModel.password,
                    username = registrationReceiveModel.login,
                    email = registrationReceiveModel.email
                )
            )
            TokensDB.insert(
                TokenDTO(
                    login = registrationReceiveModel.login,
                    token = newToken,
                    rowID = UUID.randomUUID().toString()
                )
            )
            call.respond(
                status = HttpStatusCode.OK,
                message = RegistrationResponseModel(token = newToken)
            )
        }
    }
}