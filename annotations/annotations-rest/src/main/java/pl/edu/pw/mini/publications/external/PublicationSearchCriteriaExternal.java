package pl.edu.pw.mini.publications.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.common.external.ListRequestExternal;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PublicationSearchCriteriaExternal extends ListRequestExternal {
    private String name;
    private Date created_before;
    private Date created_after;
    private Date publication_date_before;
    private Date publication_date_after;
    private String source;
    private String status;
    private Boolean annotated_by_me;
    private Long min_annotators;
    private Long max_annotators;
}
