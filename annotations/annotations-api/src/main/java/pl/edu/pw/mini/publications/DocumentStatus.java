package pl.edu.pw.mini.publications;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum DocumentStatus {
    NEW("0:new"),
    AUTO_ANNOTATED("1:auto_annotated"),
    ANNOTATED("2:annotated"),
    SUPER_ANNOTATED("3:super_annotated");

    private String label;

    private static Map<String, DocumentStatus> labelMap;

    static {
        labelMap = new HashMap<>();
        for(DocumentStatus documentStatus : values()) {
            labelMap.put(documentStatus.getLabel(), documentStatus);
        }
    }

    public static DocumentStatus getByLabel(String label) {
        return labelMap.get(label);
    }
}
