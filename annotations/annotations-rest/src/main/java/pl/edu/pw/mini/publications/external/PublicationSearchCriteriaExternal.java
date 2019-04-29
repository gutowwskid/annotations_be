package pl.edu.pw.mini.publications.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationSearchCriteriaExternal {
    private Long page;

    @JsonProperty("page-size")
    private Long page_size;

    private String name;
    private Date created_before;
    private Date created_after;
    private Date publication_date_before;
    private Date publication_date_after;
    private String source;
    private String status;
}
