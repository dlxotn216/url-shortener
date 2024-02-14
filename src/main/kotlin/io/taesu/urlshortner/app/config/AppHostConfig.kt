package io.taesu.urlshortner.app.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "config.app")
class AppHostConfig @ConstructorBinding constructor(
    val host: String,
)
