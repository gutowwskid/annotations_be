package pl.edu.pw.mini.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.invoker.rest.RestInvoker;
import pl.edu.pw.mini.users.LoginRequestDto;
import pl.edu.pw.mini.users.LoginResponseDto;
import pl.edu.pw.mini.users.external.LoginRequestExternal;
import pl.edu.pw.mini.users.external.LoginResponseExternal;

import java.util.Collections;

@Component
public class UsersRestInvoker {

    @Autowired
    private RestInvoker restInvoker;

    public LoginResponseExternal login(LoginRequestExternal loginRequestExternal) {
        return restInvoker.post("/users/login", Collections.emptyMap(), loginRequestExternal, LoginResponseExternal.class);
    }
}
