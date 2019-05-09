package pl.edu.pw.mini.annotations.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.common.external.ListRequestExternal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationSearchCriteriaExternal extends ListRequestExternal {
    private Long page_id;
    private Long page__publication_id;
}
