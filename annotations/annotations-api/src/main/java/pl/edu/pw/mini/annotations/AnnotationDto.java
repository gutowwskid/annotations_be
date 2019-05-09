package pl.edu.pw.mini.annotations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationDto {
    private String type; //TODO enum
    private Double x1;
    private Double y1;
    private Double x2;
    private Double y2;
    private String text;
    private List<AnnotationDto> subRegions;
}
