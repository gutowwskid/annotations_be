package pl.edu.pw.mini.publications.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoExternal {
    private Long id;
    private Long publication;
    private Long number;
    private String status;
    private String image;
}
