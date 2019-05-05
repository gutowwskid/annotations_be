package pl.edu.pw.mini.publications.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.common.external.ListRequestExternal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PagesSearchCriteriaExternal extends ListRequestExternal {
    private Long publication_id;
    private String status;
}
