package io.taesu.urlshortner.interfaces

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.taesu.urlshortner.app.AbstractControllerTest
import io.taesu.urlshortner.app.config.AppHostConfig
import io.taesu.urlshortner.shortenurl.application.ShortenUrlCreateService
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
class ShortenUrlCreateControllerTest: AbstractControllerTest() {
    @MockkBean
    private lateinit var shortenUrlCreateService: ShortenUrlCreateService

    @MockkBean
    private lateinit var appHostConfig: AppHostConfig

    @Test
    fun `단축 URL 생성 API 성공 테스트`() {
        // given
        every { shortenUrlCreateService.create(any()) } returns "hash-value"
        every { appHostConfig.host } returns "https://short-url.com"

        val mvcRequest = MockMvcRequestBuilders.post("/api/v1/shorten-urls")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(""" { "originalUrl": "https://test.com" } """.trimIndent())

        // when
        val perform = this.mockMvc.perform(mvcRequest)

        // then
        perform.andDo(MockMvcResultHandlers.print())
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.result.shortenUrl").value("https://short-url.com/hash-value"))
    }

    @ParameterizedTest
    @MethodSource("requiredExceptionTestCases")
    fun `originalUrl은 필수 입력 항목이다`(body: String) {
        // given
        val mvcRequest = MockMvcRequestBuilders.post("/api/v1/shorten-urls")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(body)

        // when
        val perform = this.mockMvc.perform(mvcRequest)

        // then
        perform.andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest)
    }

    companion object {
        @JvmStatic
        fun requiredExceptionTestCases() = listOf(
            """ { "originalUrl": "" } """.trimIndent(),
            """ { "originalUrl": null } """.trimIndent(),
            """ { "originalUrl": "   " } """.trimIndent(),
        )
    }

}
