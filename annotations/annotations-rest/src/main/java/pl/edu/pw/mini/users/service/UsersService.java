package pl.edu.pw.mini.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.core.security.authentication.TokenHandler;
import pl.edu.pw.mini.core.tools.MockLogger;
import pl.edu.pw.mini.core.tools.StringWrapper;
import pl.edu.pw.mini.users.LoginRequestDto;
import pl.edu.pw.mini.users.LoginResponseDto;

import javax.servlet.http.HttpServletRequest;

@Service
public class UsersService {

    @Autowired
    private UsersRestInvoker usersRestInvoker;

    @Autowired
    private LoginRequestExternalAssembler loginRequestExternalAssembler;

    @Autowired
    private TokenHandler tokenHandler;

    public StringWrapper login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        String externalToken = usersRestInvoker.login(loginRequestExternalAssembler.toLoginRequestExternal(loginRequestDto)).getToken();
        String jwtToken = tokenHandler.getTokenWithUser(MockLogger.getString("ID"),
                MockLogger.getString("ANNOTATOR"),
                MockLogger.getString("Jan Kowalski"),
                externalToken,
                request
                );


        return StringWrapper.fromValue(jwtToken);
    }
}
