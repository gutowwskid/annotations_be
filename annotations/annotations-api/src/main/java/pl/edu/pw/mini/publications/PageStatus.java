package pl.edu.pw.mini.publications;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum PageStatus {
    NEW("0:new"),
    AUTO_ANNOTATED("1:auto_annotated"),
    ANNOTATED("2:annotated"),
    SUPER_ANNOTATED("3:super_annotated");

    private String label;

    private static Map<String, PageStatus> labelMap;

    static {
        labelMap = new HashMap<>();
        for(PageStatus pageStatus : values()) {
            labelMap.put(pageStatus.getLabel(), pageStatus);
        }
    }

    public static PageStatus getByLabel(String label) {
        return labelMap.get(label);
    }
}
