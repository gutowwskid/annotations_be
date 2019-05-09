package pl.edu.pw.mini.users.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseExternal {
    private Long id;
    private String username;
    private String token;
    private String first_name;
    private String last_name;
    private Boolean is_annotator;
    private Boolean is_superannotator;
}
