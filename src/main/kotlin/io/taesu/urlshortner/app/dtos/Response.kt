package io.taesu.urlshortner.app.dtos

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
data class SuccessResponse<T>(
    val result: T
)
