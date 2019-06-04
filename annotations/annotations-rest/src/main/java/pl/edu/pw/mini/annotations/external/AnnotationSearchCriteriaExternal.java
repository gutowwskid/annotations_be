package pl.edu.pw.mini.annotations.external;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pw.mini.common.external.ListRequestExternal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnnotationSearchCriteriaExternal extends ListRequestExternal {
    private Long page_id;
    private Long page__publication_id;
}
