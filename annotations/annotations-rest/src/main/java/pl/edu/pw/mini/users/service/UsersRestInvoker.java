package pl.edu.pw.mini.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.invoker.rest.RestInvoker;
import pl.edu.pw.mini.users.external.LoginRequestExternal;
import pl.edu.pw.mini.users.external.UserExternal;

import java.util.Collections;

@Component
public class UsersRestInvoker {

    @Autowired
    private RestInvoker restInvoker;

    public UserExternal login(LoginRequestExternal loginRequestExternal) {
        return restInvoker.post("/users/login", Collections.emptyMap(), loginRequestExternal, UserExternal.class);
    }
}
