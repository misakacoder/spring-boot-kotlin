package com.misaka.kotlin.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.ktorm.database.Database
import org.ktorm.jackson.KtormModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.sql.DataSource

@Configuration
class KtormConfiguration {

    @Bean
    fun database(datasource: DataSource): Database {
        return Database.connectWithSpringSupport(datasource)
    }

    @Bean
    fun ktormModule(): KtormModule {
        return KtormModule()
    }

    @Bean
    fun objectMapper(ktormModule: KtormModule): ObjectMapper {
        val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTimeSerializer = LocalDateTimeSerializer(dateTimeFormatter)
        val localDateTimeDeserializer = LocalDateTimeDeserializer(dateTimeFormatter)
        val javaTimeModule = JavaTimeModule()
        javaTimeModule.addSerializer(LocalDateTime::class.java, localDateTimeSerializer)
        javaTimeModule.addDeserializer(LocalDateTime::class.java, localDateTimeDeserializer)
        val objectMapper = Jackson2ObjectMapperBuilder()
                .modules(javaTimeModule, ktormModule)
                .build<ObjectMapper>()
        return objectMapper
    }
}