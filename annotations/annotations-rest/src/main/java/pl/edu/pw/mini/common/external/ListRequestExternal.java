package pl.edu.pw.mini.common.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRequestExternal {
    private Long page;

    @JsonProperty("page-size")
    private Long page_size;

}
