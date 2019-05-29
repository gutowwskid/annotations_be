package pl.edu.pw.mini.users.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.users.RegisterRequestDto;
import pl.edu.pw.mini.users.external.RegisterRequestEternal;

@Component
public class RegisterRequestEternalAssembler {

    public RegisterRequestEternal toExternal(RegisterRequestDto input) {
        RegisterRequestEternal external = new RegisterRequestEternal();
        external.setUsername(input.getUsername());
        external.setPassword(input.getPassword());
        external.setFirst_name(input.getFirstName());
        external.setLast_name(input.getLastName());
        external.setEmail(input.getEmail());
        return external;
    }
}
