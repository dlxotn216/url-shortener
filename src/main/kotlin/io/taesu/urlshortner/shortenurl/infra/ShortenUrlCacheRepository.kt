package io.taesu.urlshortner.shortenurl.infra

import java.time.Duration

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
interface ShortenUrlCacheRepository {
    fun find(hash: String): ShortenUrlCacheEntity?
    fun save(hash: String, originalUrl: String, ttl: Duration = Duration.ofDays(7L))
}

data class ShortenUrlCacheEntity(
    val originalUrl: String,
)
