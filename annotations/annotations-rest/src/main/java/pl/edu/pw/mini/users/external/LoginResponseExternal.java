package pl.edu.pw.mini.users.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseExternal {
    private String token;

    @JsonProperty("fist_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
}
