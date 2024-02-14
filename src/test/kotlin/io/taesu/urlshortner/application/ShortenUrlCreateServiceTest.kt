package io.taesu.urlshortner.application

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntity
import io.taesu.urlshortner.shortenurl.domain.ShortenUrlEntityRepository
import io.taesu.urlshortner.hash.usecase.HashGenerator
import io.taesu.urlshortner.shortenurl.interfaces.dtos.ShortenUrlCreateRequest
import io.taesu.urlshortner.shortenurl.application.ShortenUrlCreateService
import org.assertj.core.api.Assertions.assertThat
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
class ShortenUrlCreateServiceTest {
    @MockK
    private lateinit var hashGenerator: HashGenerator

    @MockK
    private lateinit var shortenUrlEntityRepository: ShortenUrlEntityRepository

    @SpyK
    @InjectMockKs
    private lateinit var shortenUrlCreateService: ShortenUrlCreateService

    @Test
    fun `단축 URL 생성 서비스 테스트`() {
        // given
        every { hashGenerator.generate(any()) } returns "hash-value"
        val slot = slot<ShortenUrlEntity>()
        every { shortenUrlEntityRepository.save(capture(slot)) } answers  { firstArg()}

        // when
        val result = shortenUrlCreateService.create(ShortenUrlCreateRequest("https://test.com"))

        // then
        assertThat(result).isEqualTo("hash-value")
        with(slot.captured) {
            assertThat(originalUrl).isEqualTo("https://test.com")
            assertThat(hash).isEqualTo("hash-value")
        }
    }

}
