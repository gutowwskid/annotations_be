package pl.edu.pw.mini.annotations.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnotationExternal {
    private String type;
    private Double x1;
    private Double x2;
    private Double y1;
    private Double y2;
    private String text;
}
