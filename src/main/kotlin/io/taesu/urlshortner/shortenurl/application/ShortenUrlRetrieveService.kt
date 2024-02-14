package io.taesu.urlshortner.shortenurl.application

import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntityRepository

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
class ShortenUrlRetrieveService(private val shortenUrlEntityRepository: ShortenUrlEntityRepository) {
    fun retrieve(hash: String) {

    }
}
