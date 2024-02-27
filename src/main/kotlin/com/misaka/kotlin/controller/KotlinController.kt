package com.misaka.kotlin.controller

import com.misaka.kotlin.model.alcohols
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.find
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class KotlinController {

    @GetMapping("")
    fun index(): String {
        return "hello spring boot kotlin"
    }
}