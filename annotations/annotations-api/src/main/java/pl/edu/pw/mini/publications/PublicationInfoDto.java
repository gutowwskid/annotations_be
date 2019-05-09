package pl.edu.pw.mini.publications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationInfoDto {
    private Long id;
    private String name;
    private String source;
    private LocalDate created;
    private String url;
    private String sourceUrl;
    private LocalDate publicationDate;
    private DocumentStatus status;

}
