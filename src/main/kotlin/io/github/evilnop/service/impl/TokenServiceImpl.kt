package io.github.evilnop.service.impl

import io.github.evilnop.service.TokenService
import io.github.evilnop.utils.RSAUtils
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.Date

@Service
class TokenServiceImpl(
    private val rsaUtils: RSAUtils
) : TokenService {

    override fun allocate(userId: Int): String = Jwts.builder()
        .setSubject(userId.toString())
        .setExpiration(Date(System.currentTimeMillis() + 864_000_00))
        .signWith(SignatureAlgorithm.RS512, rsaUtils.loadRSAPrivateKey())
        .compact()

    override fun verify(token: String): Int = Jwts.parser()
        .setSigningKey(rsaUtils.loadRSAPublicKey())
        .parseClaimsJws(token)
        .body
        .subject
        .toInt()
}
