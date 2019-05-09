package pl.edu.pw.mini.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.core.security.authorization.AllowAll;
import pl.edu.pw.mini.core.tools.StringWrapper;
import pl.edu.pw.mini.users.service.UsersService;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @AllowAll
    @RequestMapping(path = "/users/login", method = RequestMethod.POST, produces = "application/json")
    public StringWrapper login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        return usersService.login(loginRequestDto, request);
    }
}
