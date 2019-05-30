package pl.edu.pw.mini.annotations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationCreationDto {
    private Long pageId;
    private AnnotationDto annotation;
    private List<Long> annotationsUsed;
    private List<String> tags;
}
