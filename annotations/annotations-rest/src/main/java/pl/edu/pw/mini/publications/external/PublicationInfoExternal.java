package pl.edu.pw.mini.publications.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationInfoExternal {
    private Long id;
    private String name;
    private String source;
    private Date created;
    private String local_file;
    private String remote_file;
    private Date publication_date;
    private String status;
}
