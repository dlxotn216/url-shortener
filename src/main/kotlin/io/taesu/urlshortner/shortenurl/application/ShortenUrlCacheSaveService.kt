package io.taesu.urlshortner.shortenurl.application

import io.taesu.urlshortner.shortenurl.infra.ShortenUrlCacheRepository
import org.springframework.stereotype.Service

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@Service
class ShortenUrlCacheSaveService(private val shortenUrlCacheRepository: ShortenUrlCacheRepository) {
    fun save(hash: String, originalUrl: String) {
        // 응답이 느린경우, 실패하는 경우 커넥션이 물릴 것을 대비하여 Circuit breaker 도입 고려
        shortenUrlCacheRepository.save(hash, originalUrl)
    }
}
