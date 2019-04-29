package pl.edu.pw.mini.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.invoker.rest.RestInvoker;
import pl.edu.pw.mini.users.LoginRequestDto;
import pl.edu.pw.mini.users.LoginResponseDto;

import java.util.Collections;

@Component
public class UsersRestInvoker {

    @Autowired
    private RestInvoker restInvoker;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return restInvoker.post("/users/login", Collections.emptyMap(), loginRequestDto, LoginResponseDto.class);
    }
}
