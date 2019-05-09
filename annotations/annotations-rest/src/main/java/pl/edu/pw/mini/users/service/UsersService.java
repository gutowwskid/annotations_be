package pl.edu.pw.mini.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.core.security.authentication.TokenHandler;
import pl.edu.pw.mini.core.tools.MockLogger;
import pl.edu.pw.mini.core.tools.StringWrapper;
import pl.edu.pw.mini.model.Profile;
import pl.edu.pw.mini.users.LoginRequestDto;
import pl.edu.pw.mini.users.LoginResponseDto;
import pl.edu.pw.mini.users.external.LoginResponseExternal;

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
        LoginResponseExternal externalResponse = usersRestInvoker.login(loginRequestExternalAssembler.toLoginRequestExternal(loginRequestDto));
        String jwtToken = tokenHandler.getTokenWithUser(
                externalResponse.getId().toString(),
                getProfileFromResponse(externalResponse).name(),
                externalResponse.getFirst_name() + " " + externalResponse.getLast_name(),
                externalResponse.getToken(),
                request
        );

        return StringWrapper.fromValue(jwtToken);
    }

    private Profile getProfileFromResponse(LoginResponseExternal externalResponse) {
        if(externalResponse.getIs_superannotator()) {
            return Profile.SUPER_ANNOTATOR;
        } else if(externalResponse.getIs_annotator()) {
            return Profile.ANNOTATOR;
        }
        return Profile.USER;
    }
}
