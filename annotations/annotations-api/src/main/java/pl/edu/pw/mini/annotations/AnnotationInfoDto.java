package pl.edu.pw.mini.annotations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.edu.pw.mini.users.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationInfoDto {
    private Long id;
    private UserDto creator;
    private Long pageId;
    private String status; //TODO enum
    private LocalDate creationDate;
    private AnnotationDto data;
    private List<String> tags;
}
