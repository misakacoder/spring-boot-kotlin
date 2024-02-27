package com.misaka.kotlin.exception

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionsHandler {

    val logger = LoggerFactory.getLogger(ExceptionsHandler::class.java)!!

    @ExceptionHandler(Throwable::class)
    fun exceptionHandler(request : HttpServletRequest, response: HttpServletResponse, throwable : Throwable) {
        logger.error("", throwable)
        response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        val writer = response.writer
        writer.use {
            throwable.printStackTrace(response.writer)
        }
    }
}