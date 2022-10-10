package dev.palminschi.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

object UsersDB: Table("users") {
    private val login = UsersDB.varchar(name = "login", length = 25)
    private val password = UsersDB.varchar(name = "password", length = 25)
    private val username = UsersDB.varchar(name = "username", length = 30)
    private val email = UsersDB.varchar(name = "email", length = 30)

    fun insert(userDTO: UserDTO) {
        transaction {
            UsersDB.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[username] = userDTO.username
                it[email] = userDTO.email ?: ""
            }
        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            transaction {
                val userModel = UsersDB.select { UsersDB.login.eq(login) }.single()
                UserDTO(
                    login = userModel[UsersDB.login],
                    password = userModel[password],
                    username = userModel[username],
                    email = userModel[email]
                )
            }
        } catch (e: Exception) {
            null
        }

    }
}