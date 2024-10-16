package com.sphirye.shared.web.filters

import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.io.IOException

@Component("_CorsFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter : Filter {

    @Value("\${app.security.cors.origins}")
    private lateinit var _corsOrigins: String

    @Value("\${app.security.cors.methods}")
    private lateinit var _corsMethods: String

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {

        val response = res as HttpServletResponse
        val request = req as HttpServletRequest

        val allowedOrigins = _corsOrigins.split(",")

        // Verifica si el origen de la solicitud está permitido
        val originHeader = request.getHeader("Origin")
        if (originHeader != null && allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader)
        }

        response.setHeader("Access-Control-Allow-Methods", _corsMethods)
        response.setHeader(
            "Access-Control-Allow-Headers",
            "X-Requested-With, Authorization, Content-Type, X-Pager-Page, X-Pager-Page-Size"
        )
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Expose-Headers", "X-Total-Count")

        // Permitir credenciales (cookies, Authorization headers, etc.)
        response.setHeader("Access-Control-Allow-Credentials", "true")

        if ("OPTIONS".equals(request.method, ignoreCase = true)) {
            response.status = HttpServletResponse.SC_OK
        } else {
            chain.doFilter(req, res)
        }
    }
}
