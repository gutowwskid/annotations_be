package pl.edu.pw.mini.publications;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoDto {
    private Long id;
    private Long publicationId;
    private Long number;
    private String status;
    private String imageUrl;
}
