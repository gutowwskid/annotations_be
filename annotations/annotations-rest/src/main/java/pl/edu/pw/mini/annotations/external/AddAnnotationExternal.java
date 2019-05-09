package pl.edu.pw.mini.annotations.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.annotations.AnnotationDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAnnotationExternal {
    private Long page;
    private AnnotationDto data;
}
