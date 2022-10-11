package dev.palminschi.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object TokensDB: Table("tokens") {
    private val login = TokensDB.varchar(name = "login", length = 25)
    private val id = TokensDB.varchar(name = "id", length = 50)
    private val token = TokensDB.varchar(name = "token", length = 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            TokensDB.insert {
                it[id] = tokenDTO.rowID
                it[token] = tokenDTO.token
                it[login] = tokenDTO.login
            }
        }
    }
}