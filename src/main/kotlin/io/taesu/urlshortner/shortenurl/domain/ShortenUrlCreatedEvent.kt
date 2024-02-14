package io.taesu.urlshortner.shortenurl.domain

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
data class ShortenUrlCreatedEvent(val hash: String, val originalUrl: String)
