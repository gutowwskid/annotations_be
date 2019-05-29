package pl.edu.pw.mini.annotations.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.annotations.AnnotationDto;
import pl.edu.pw.mini.users.external.UserExternal;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationInfoExternal {
    private Long id;
    private UserExternal user;
    private Long page;
    private Map<String, Object> data;
    private Date created;
    private Boolean is_used;
    private String annotation_status;
    private List<String> tags;
}
