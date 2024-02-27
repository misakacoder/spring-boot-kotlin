package com.misaka.kotlin

import com.misaka.kotlin.config.KtormConfiguration
import com.misaka.kotlin.model.alcohols
import org.ktorm.database.Database
import org.ktorm.entity.toList
import org.ktorm.jackson.KtormModule
import org.slf4j.LoggerFactory
import java.sql.Connection
import java.sql.DriverManager
import kotlin.concurrent.thread

class Ktorm
val logger = LoggerFactory.getLogger(Ktorm::class.java)!!

fun main() {
    val connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/haibara", "root", "root")
    Runtime.getRuntime().addShutdownHook(thread(start = false) {
        connection.close()
    })
    val database = Database.connect {
        object : Connection by connection {
            override fun close() {

            }
        }
    }
    val objectMapper = KtormConfiguration().objectMapper(KtormModule())
    database.alcohols.toList().run {
        logger.info("{}", objectMapper.writeValueAsString(this))
    }
}