package de.gitzoz.homefeed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HomefeedApplication

fun main(args: Array<String>) {
    runApplication<HomefeedApplication>(*args)
}
