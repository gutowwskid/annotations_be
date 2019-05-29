package pl.edu.pw.mini.annotations.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.AnnotationCreationDto;
import pl.edu.pw.mini.annotations.external.AddAnnotationExternal;
import pl.edu.pw.mini.core.invoker.rest.Rest;
import pl.edu.pw.mini.core.tools.ExternalAssembler;

import java.util.Map;

@Component
public class AddAnnotationExternalAssembler extends ExternalAssembler<AnnotationCreationDto, AddAnnotationExternal> {

    @Rest
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public AddAnnotationExternal toExternal(AnnotationCreationDto input) {
        AddAnnotationExternal external = new AddAnnotationExternal();
        external.setPage(input.getPageId());

        external.setData(objectMapper.convertValue(input.getAnnotation(), new TypeReference<Map<String, Object>>() {}));
        external.setAnnotations_used(input.getAnnotationsUsed());
        external.setTags(input.getTags());
        return external;
    }
}
