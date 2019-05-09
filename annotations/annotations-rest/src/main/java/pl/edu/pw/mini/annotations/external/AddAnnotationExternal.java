package pl.edu.pw.mini.annotations.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAnnotationExternal {
    private Long page;
    private String data;
}
