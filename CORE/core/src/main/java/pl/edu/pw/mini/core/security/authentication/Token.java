package pl.edu.pw.mini.core.security.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    private String userId;
    private String profile;
    private String ip;
    private Date expiration;
    private String issuer;
    private Date issuedAt;
    private String id;
    private String tokenString;
}
