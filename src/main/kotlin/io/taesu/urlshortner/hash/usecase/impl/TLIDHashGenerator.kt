package io.taesu.urlshortner.hash.usecase.impl

import com.github.f4b6a3.tsid.TsidCreator
import io.taesu.urlshortner.hash.usecase.HashGenerateRequest
import io.taesu.urlshortner.hash.usecase.HashGenerator
import org.springframework.stereotype.Component

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@Component
class TLIDHashGenerator: HashGenerator {
    override fun generate(request: HashGenerateRequest): String {
        return TsidCreator.getTsid().toString()
    }
}
