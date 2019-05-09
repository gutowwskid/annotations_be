package pl.edu.pw.mini.annotations.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationInfoExternal {
    private Long id;
    private Long user;
    private Long page;
    private String data;
    private Date created;
    private Boolean is_used;
    private String annotation_status;
}
