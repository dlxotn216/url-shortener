package io.taesu.urlshortner.interfaces

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@WebMvcTest(ShortenUrlCreateController::class)
class ShortenUrlCreateControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc


    @BeforeEach
    fun setUp(
        webApplicationContext: WebApplicationContext,
    ) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build()
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
            .andExpect(MockMvcResultMatchers.status().isBadRequest)

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
