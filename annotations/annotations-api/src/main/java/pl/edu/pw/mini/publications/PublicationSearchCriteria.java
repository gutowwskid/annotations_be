package pl.edu.pw.mini.publications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.model.Period;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationSearchCriteria {
    private String name;
    private Period creationPeriod;
    private Period publicationPeriod;
    private String source;
    private String status;
}
