package pl.edu.pw.mini.annotations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationCreationDto {
    private Long pageId;
    private AnnotationDto annotation;
}
