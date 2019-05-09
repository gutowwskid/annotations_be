package pl.edu.pw.mini.core.security.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class TokenHandler {
    private static final String USER_ID = "userId";
    private static final String PROFILE = "profile";
    private static final String IP = "ip";
    private static final String USER_NAME = "userName";
    private static final String EXTERNAL_TOkEN = "externalToken";

    private final SecurityProperties properties;
    private final Key signingKey;

    public TokenHandler(SecurityProperties properties) {
        this.properties = properties;
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(properties.getSecretKey());
        signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

    public String getTokenWithUser(String userId, String profile, String userName, String externalToken, HttpServletRequest request) {
        Date expires = new Date(System.currentTimeMillis() + properties.getTokenValidity());

        return Jwts.builder()
                .claim(USER_ID, userId)
                .claim(PROFILE, profile)
                .claim(IP, request.getRemoteAddr())
                .claim(USER_NAME, userName)
                .claim(EXTERNAL_TOkEN, externalToken)
                .setExpiration(expires)
                .setIssuer(properties.getIssuer())
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString())
                .signWith(signingKey)
                .compact();
    }
    public Token parseToken(String rawToken, HttpServletRequest request) {
        Claims claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(rawToken).getBody();
        if(!claims.get(IP).equals("*") && !claims.get(IP).equals(request.getRemoteAddr())) {
            throw new JwtException("Different user IP!");
        }

        return Token.builder()
                .userId(claims.get(USER_ID, String.class))
                .profile(claims.get(PROFILE, String.class))
                .userName(claims.get(USER_NAME, String.class))
                .externalToken(claims.get(EXTERNAL_TOkEN, String.class))
                .ip(claims.get(IP, String.class))
                .expiration(claims.getExpiration())
                .issuer(claims.getIssuer())
                .issuedAt(claims.getIssuedAt())
                .id(claims.getId())
                .tokenString(rawToken)
                .build();
    }
}
