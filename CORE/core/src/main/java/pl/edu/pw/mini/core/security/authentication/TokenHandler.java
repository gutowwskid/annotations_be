package pl.edu.pw.mini.core.security.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class TokenHandler {

    private final SecurityProperties properties;
    private final Key signingKey;

    public TokenHandler(SecurityProperties properties) {
        this.properties = properties;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(properties.getSecretKey());
        signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public String getTokenWithUser(String userId, String profile, HttpServletRequest request) {
        Date expires = new Date(System.currentTimeMillis() + properties.getTokenValidity());

        return Jwts.builder()
                .claim("userId", userId)
                .claim("profile", profile)
                .claim("ip", request.getRemoteAddr())
                .setExpiration(expires)
                .setIssuer(properties.getIssuer())
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .signWith(signingKey)
                .compact();
    }
    public Token parseToken(String rawToken, HttpServletRequest request) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(rawToken).getBody();
        if(!claims.get("ip").equals(request.getRemoteAddr())) {
            throw new JwtException("Different user IP!");
        }

        return Token.builder()
                .userId(claims.get("userId", String.class))
                .profile(claims.get("profile", String.class))
                .ip(claims.get("ip", String.class))
                .expiration(claims.getExpiration())
                .issuer(claims.getIssuer())
                .issuedAt(claims.getIssuedAt())
                .id(claims.getId())
                .tokenString(rawToken)
                .build();
    }
}
