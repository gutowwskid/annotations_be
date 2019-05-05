package pl.edu.pw.mini.publications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagesSearchCriteria {
    private Long publicationId;
    private String status;
}
