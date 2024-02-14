package io.taesu.urlshortner.interfaces.dtos

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class ShortenUrlCreateRequest(
    @field:Size(max = 4096, message = "'originalUrl' must not be longer than 4096 characters.")
    @field:NotNull(message = "'originalUrl' must not be not null or blank.")
    @field:NotBlank(message = "'originalUrl' must not be null or blank.")
    val originalUrl: String,
)

class ShortenUrlCreateResponse private constructor(val shortenUrl: String) {
    companion object {
        fun of(host: String, hash: String) = ShortenUrlCreateResponse("$host/$hash")
    }
}
