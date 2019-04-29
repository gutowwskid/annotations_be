package pl.edu.pw.mini.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.users.LoginRequestDto;
import pl.edu.pw.mini.users.LoginResponseDto;

@Service
public class UsersService {

    @Autowired
    private UsersRestInvoker usersRestInvoker;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return usersRestInvoker.login(loginRequestDto);
    }
}
