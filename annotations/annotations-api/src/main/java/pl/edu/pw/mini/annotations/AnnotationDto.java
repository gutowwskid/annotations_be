package pl.edu.pw.mini.annotations;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnotationDto {
    private List<String> type; //TODO enum
    private Double x1;
    private Double y1;
    private Double x2;
    private Double y2;
    private String text;
    private List<AnnotationDto> subRegions;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;
}
