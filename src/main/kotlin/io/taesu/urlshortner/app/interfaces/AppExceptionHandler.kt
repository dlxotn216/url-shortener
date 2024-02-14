package io.taesu.urlshortner.app.interfaces

import io.taesu.urlshortner.app.dtos.FailResponse
import io.taesu.urlshortner.app.exceptions.AppRuntimeException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * Created by itaesu on 2024/02/14.
 *
 * @author Lee Tae Su
 * @version url-shortner
 * @since url-shortner
 */
@RestControllerAdvice
class AppExceptionHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler(AppRuntimeException::class)
    fun handleAppRuntimeException(e: AppRuntimeException): FailResponse {
        return FailResponse(e.errorCode, e.message ?: "Unexpected Error")
    }
}
