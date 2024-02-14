package io.taesu.urlshortner.shortenurl.infra.impl

import com.fasterxml.jackson.databind.ObjectMapper
import io.taesu.urlshortner.shortenurl.infra.ShortenUrlCacheEntity
import io.taesu.urlshortner.shortenurl.infra.ShortenUrlCacheRepository
import org.springframework.data.redis.connection.RedisStringCommands
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.types.Expiration
import org.springframework.stereotype.Component
import java.time.Duration

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@Component
class ShortenUrlRedisRepository(
    private val stringRedisTemplate: StringRedisTemplate,
    private val objectMapper: ObjectMapper,
): ShortenUrlCacheRepository {
    override fun find(hash: String): ShortenUrlCacheEntity? {
        return stringRedisTemplate.opsForValue().get(resolveShortenUrlKey(hash))?.let {
            objectMapper.readValue(it, ShortenUrlCacheEntity::class.java)
        }
    }

    override fun save(hash: String, originalUrl: String, ttl: Duration) {
        val key = stringRedisTemplate.stringSerializer.serialize(resolveShortenUrlKey(hash)) ?: return
        val value = stringRedisTemplate.stringSerializer.serialize(objectMapper.writeValueAsString(ShortenUrlCacheEntity(originalUrl))) ?: return

        stringRedisTemplate.executePipelined {
            it.stringCommands().set(key, value, Expiration.from(ttl), RedisStringCommands.SetOption.upsert())
        }
    }

    private fun resolveShortenUrlKey(hash: String) = "shorten_url:$hash"
}
