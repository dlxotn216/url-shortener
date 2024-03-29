package io.taesu.urlshortner.shortenurl.interfaces

import io.taesu.urlshortner.app.dtos.SuccessResponse
import io.taesu.urlshortner.shortenurl.application.ShortenUrlRetrieveService
import io.taesu.urlshortner.shortenurl.interfaces.dtos.ShortenUrlRetrieveResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@RestController
class ShortenUrlRetrieveController(
    private val shortenUrlRetrieveService: ShortenUrlRetrieveService,
) {
    @GetMapping("/api/v1/shorten-urls/{hash}")
    fun retrieve(@PathVariable hash: String): SuccessResponse<ShortenUrlRetrieveResponse> {
        return SuccessResponse(ShortenUrlRetrieveResponse(shortenUrlRetrieveService.retrieveOriginalUrl(hash)))
    }
}

@Controller
class ShortenUrlRedirectController(
    private val shortenUrlRetrieveService: ShortenUrlRetrieveService,
) {
    @GetMapping("{hash}")
    fun redirect(@PathVariable hash: String): RedirectView {
        val originalUrl = shortenUrlRetrieveService.retrieveOriginalUrl(hash)
        return RedirectView(originalUrl)
    }
}
