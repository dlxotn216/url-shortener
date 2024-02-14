package io.taesu.urlshortner.hash.usecase

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
interface HashGenerator {
    fun generate(request: HashGenerateRequest): String
}

class HashGenerateRequest(
    val salt: String,
) {
    companion object {
        private val EMPTY_HASH_GENERATE_REQUEST = HashGenerateRequest("")
        fun empty() = EMPTY_HASH_GENERATE_REQUEST
    }
}
