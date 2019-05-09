package pl.edu.pw.mini.users.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestExternal {
    private String username;
    private String password;
}
