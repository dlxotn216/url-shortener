package io.taesu.urlshortner.app.exceptions

import io.taesu.urlshortner.app.dtos.ErrorCode

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
open class AppRuntimeException(
    message: String,
    val errorCode: ErrorCode,
): RuntimeException(message)

class EntityNotFoundException(message: String): AppRuntimeException(message, ErrorCode.ENTITY_NOT_FOUND)
