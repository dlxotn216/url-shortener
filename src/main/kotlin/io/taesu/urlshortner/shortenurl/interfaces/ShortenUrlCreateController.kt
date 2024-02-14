package io.taesu.urlshortner.shortenurl.interfaces

import io.taesu.urlshortner.app.dtos.SuccessResponse
import io.taesu.urlshortner.shortenurl.application.AppHostConfig
import io.taesu.urlshortner.shortenurl.application.ShortenUrlCreateService
import io.taesu.urlshortner.shortenurl.interfaces.dtos.ShortenUrlCreateRequest
import io.taesu.urlshortner.shortenurl.interfaces.dtos.ShortenUrlCreateResponse
import jakarta.validation.Valid
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
class ShortenUrlCreateController(
    private val shortenUrlCreateService: ShortenUrlCreateService,
    private val appHostConfig: AppHostConfig,
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/shorten-urls")
    fun create(@Valid @RequestBody request: ShortenUrlCreateRequest): SuccessResponse<ShortenUrlCreateResponse> {
        val hash = shortenUrlCreateService.create(request)
        return SuccessResponse(ShortenUrlCreateResponse.of(appHostConfig.host, hash))
    }
}

