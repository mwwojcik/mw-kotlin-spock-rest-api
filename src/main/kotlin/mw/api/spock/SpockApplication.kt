package mw.api.spock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpockApplication

fun main(args: Array<String>) {
	runApplication<SpockApplication>(*args)
}
