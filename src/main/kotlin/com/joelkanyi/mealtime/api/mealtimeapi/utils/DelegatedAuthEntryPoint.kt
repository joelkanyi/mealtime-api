package com.joelkanyi.mealtime.api.mealtimeapi.utils

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerExceptionResolver
import java.util.*

@Component
class DelegatedAuthEntryPoint(
    private val handlerExceptionResolver: HandlerExceptionResolver
) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.contentType = "application/json"
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        response.outputStream.println(  "{ "
                + "\"timestamp\": \""+ Date() + "\","
                + "\"message\": \""+ authException.message + "\","
                + "\"status\": \""+ response.status + "\""
                + "}")    }
}