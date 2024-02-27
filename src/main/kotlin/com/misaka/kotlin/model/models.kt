package com.misaka.kotlin.model

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.datetime
import org.ktorm.schema.long
import org.ktorm.schema.varchar
import java.time.LocalDateTime

interface Alcohol : Entity<Alcohol> {
    companion object : Entity.Factory<Alcohol>()
    val id: Long
    val codeName: String
    val personName: String
    val alcoholName: String
    val createAt: LocalDateTime
}

object Alcohols : Table<Alcohol>("alcohol") {
    val id = long("id").primaryKey().bindTo { it.id }
    val codeName = varchar("code_name").bindTo { it.codeName }
    val personName = varchar("person_name").bindTo { it.personName }
    val alcoholName = varchar("alcohol_name").bindTo { it.alcoholName }
    val createAt = datetime("create_at").bindTo { it.createAt }
}

val Database.alcohols get() = this.sequenceOf(Alcohols)