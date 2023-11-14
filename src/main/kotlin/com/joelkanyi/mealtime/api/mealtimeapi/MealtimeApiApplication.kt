package com.joelkanyi.mealtime.api.mealtimeapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [JdbcTemplateAutoConfiguration::class])
class MealtimeApiApplication

fun main(args: Array<String>) {
    runApplication<MealtimeApiApplication>(*args)
}
