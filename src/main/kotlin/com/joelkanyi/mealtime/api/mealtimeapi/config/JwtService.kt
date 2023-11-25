package com.joelkanyi.mealtime.api.mealtimeapi.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
object JwtService {
    private const val SECRET_KEY = "5411965c8af72c8dc73a309e03ef519f2e3644e400e3a5173b9861d1d699a5e7"
    fun extractUserName(jwtToken: String): String {
        return extractClaim(jwtToken) { it.subject }
    }

    fun <T> extractClaim(jwtToken: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(jwtToken)
        return claimsResolver(claims)
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(mapOf(), userDetails)
    }

    fun isTokenValid(jwtToken: String, userDetails: UserDetails): Boolean {
        val username = extractUserName(jwtToken)
        return username == userDetails.username && !isTokenExpired(jwtToken)
    }

    private fun isTokenExpired(jwtToken: String): Boolean {
        return extractExpiration(jwtToken).before(Date())
    }

    private fun extractExpiration(jwtToken: String): Date {
        return extractClaim(jwtToken) { it.expiration }
    }

    fun generateToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails,
    ): String {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    private fun extractAllClaims(jwtToken: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(jwtToken)
            .body
    }

    private fun getSigningKey(): Key {
        val keyBytes: ByteArray = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}