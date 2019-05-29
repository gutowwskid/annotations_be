package pl.edu.pw.mini.users.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestEternal {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
}
