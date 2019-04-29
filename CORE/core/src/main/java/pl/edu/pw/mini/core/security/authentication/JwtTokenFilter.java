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
        try {
        } catch (Throwable ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(prepareContextFromJwtToken(rawToken));
        filterChain.doFilter(request, response);
    }

    private Context prepareContextForAnonymousUser(HttpServletRequest request) {
        Context context = new Context();
        context.setProfile(Constants.ANONYMOUS_PROFILE);
        context.setIp(request.getRemoteAddr());
        context.setAuthorities((Collections.singletonList(new SimpleGrantedAuthority(Constants.ANONYMOUS_ROLE))));
        return context;
    }

    private Context prepareContextFromJwtToken(String token) {
        Context context = new Context();
        context.setToken(Token.builder().tokenString(token).build());
        context.setAuthorities(new ArrayList<>()); //TODO MOCK!
        return context;
    }
}
