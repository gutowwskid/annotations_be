package pl.edu.pw.mini.annotations.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.annotations.AnnotationDto;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAnnotationExternal {
    private Long page;
    private Map<String, Object> data;
    private List<Long> annotations_used;
    private List<String> tags;
}
