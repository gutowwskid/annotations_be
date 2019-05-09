package pl.edu.pw.mini.core.security.authentication;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.edu.pw.mini.core.configuration.Constants;
import pl.edu.pw.mini.core.tools.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private TokenHandler tokenHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String rawToken = request.getHeader(Constants.TOKEN);
        if(StringUtils.isEmpty(rawToken)) {
            SecurityContextHolder.getContext().setAuthentication(prepareContextForAnonymousUser(request));
            filterChain.doFilter(request, response);
            return;
        }

        Token token;
        try {
            token = tokenHandler.parseToken(rawToken, request);
        } catch (Throwable ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(prepareContextFromJwtToken(token));
        filterChain.doFilter(request, response);
    }

    private Context prepareContextForAnonymousUser(HttpServletRequest request) {
        Context context = new Context();
        context.setProfile(Constants.ANONYMOUS_PROFILE);
        context.setIp(request.getRemoteAddr());
        context.setAuthorities((Collections.singletonList(new SimpleGrantedAuthority(Constants.ANONYMOUS_ROLE))));
        return context;
    }

    private Context prepareContextFromJwtToken(Token token) {
        Context context = new Context();
        context.setUserId(token.getUserId());
        context.setProfile(token.getProfile());
        context.setToken(token);
        context.setIp(token.getIp());
        context.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority(token.getProfile())));
        context.setUserName(token.getUserName());
        context.setExternalToken(token.getExternalToken());
        return context;
    }
}
