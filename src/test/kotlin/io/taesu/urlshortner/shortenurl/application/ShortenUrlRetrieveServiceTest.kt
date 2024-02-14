package io.taesu.urlshortner.shortenurl.application

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntity
import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntityRepository
import io.taesu.urlshortner.shortenurl.domain.findOrThrow
import io.taesu.urlshortner.shortenurl.infra.ShortenUrlCacheEntity
import io.taesu.urlshortner.shortenurl.infra.ShortenUrlCacheRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@ExtendWith(MockKExtension::class)
class ShortenUrlRetrieveServiceTest {
    @MockK
    private lateinit var shortenUrlCacheRepository: ShortenUrlCacheRepository

    @MockK
    private lateinit var shortenUrlEntityRepository: ShortenUrlEntityRepository

    @SpyK
    @InjectMockKs
    private lateinit var service: ShortenUrlRetrieveService

    @Test
    fun `Cache 된 데이터가 있다면 반환한다`() {
        // given
        val hash = "hash-valie"
        every { shortenUrlCacheRepository.find(hash) } returns ShortenUrlCacheEntity("https://test.com")

        // when
        val originalUrl = service.retrieveOriginalUrl(hash)

        // then
        assertEquals(originalUrl, "https://test.com")
    }

    @Test
    fun `Cache 된 데이터가 없다면 데이터베이스를 통해 조회하고 캐시에 적재한다`() {
        // given
        val hash = "hash-valie"
        val originalUrl1 = "https://test.com"

        every { shortenUrlCacheRepository.find(hash) } returns null

        mockkStatic(ShortenUrlEntityRepository::findOrThrow)
        every { shortenUrlEntityRepository.findOrThrow(hash) } returns ShortenUrlEntity(123L, originalUrl1, hash)

        every { shortenUrlCacheRepository.save(hash, originalUrl1) } returns Unit

        // when
        val originalUrl = service.retrieveOriginalUrl(hash)

        // then
        assertEquals(originalUrl, originalUrl1)
    }

}
