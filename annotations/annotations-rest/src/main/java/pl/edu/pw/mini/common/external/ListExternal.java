package pl.edu.pw.mini.common.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListExternal<T> {
    private Long count;
    private String next;
    private String previous;
    private List<T> results;
}
