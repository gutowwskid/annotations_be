package pl.edu.pw.mini.annotations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationSearchCriteria {
    private Long publicationId;
    private Long pageId;
    private String type; //TODO enum
}
