package pl.edu.pw.mini.users.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.users.LoginRequestDto;
import pl.edu.pw.mini.users.external.LoginRequestExternal;

@Component
public class LoginRequestExternalAssembler {
    public LoginRequestExternal toLoginRequestExternal(LoginRequestDto dto) {
        LoginRequestExternal external = new LoginRequestExternal();
        external.setUsername(dto.getUsername());
        external.setPassword(dto.getPassword());
        return external;
    }
}
