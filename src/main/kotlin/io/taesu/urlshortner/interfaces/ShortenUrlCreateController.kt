package io.taesu.urlshortner.interfaces

import io.taesu.urlshortner.app.dtos.SuccessResponse
import io.taesu.urlshortner.application.ShortenUrlCreateService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * Created by itaesu on 2024/02/13.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@RestController
class ShortenUrlCreateController(private val shortenUrlCreateService: ShortenUrlCreateService) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/shorten-urls")
    fun create(@Valid @RequestBody request: ShortenUrlCreateRequest): SuccessResponse<String> {
        return SuccessResponse(shortenUrlCreateService.create(request))
    }
}

data class ShortenUrlCreateRequest(
    @field:Size(max = 4096, message = "'originalUrl' must not be longer than 4096 characters.")
    @field:NotNull(message = "'originalUrl' must not be not null or blank.")
    @field:NotBlank(message = "'originalUrl' must not be null or blank.")
    val originalUrl: String,
)
