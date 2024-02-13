package io.taesu.urlshortner

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class UrlShortnerApplication

fun main(args: Array<String>) {
    runApplication<UrlShortnerApplication>(*args)
}
