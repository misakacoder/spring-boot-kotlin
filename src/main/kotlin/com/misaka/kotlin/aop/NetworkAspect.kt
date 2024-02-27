package com.misaka.kotlin.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class NetworkAspect {

    val logger = LoggerFactory.getLogger(NetworkAspect::class.java)!!

    @Pointcut("bean(*Controller)")
    fun controller() {

    }

    @Around("controller()")
    fun network(pjp: ProceedingJoinPoint): Any {
        val watch = StopWatch()
        watch.start()
        try {
            return pjp.proceed()
        } catch (e: Throwable) {
            throw e
        } finally {
            watch.stop()
            val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
            val request = attributes.request
            val remoteAddr = if (request.remoteAddr == "0:0:0:0:0:0:0:1") "localhost" else request.remoteAddr
            logger.info("{} {} {} {} {}ms", remoteAddr, request.method, request.requestURI, attributes.response?.status ?: 0, watch.totalTimeMillis)
        }
    }
}