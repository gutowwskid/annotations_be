package pl.edu.pw.mini.annotations.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.AnnotationCreationDto;
import pl.edu.pw.mini.annotations.external.AddAnnotationExternal;
import pl.edu.pw.mini.core.exceptions.BusinessException;
import pl.edu.pw.mini.core.invoker.rest.Rest;
import pl.edu.pw.mini.core.tools.ExternalAssembler;

@Component
public class AddAnnotationExternalAssembler extends ExternalAssembler<AnnotationCreationDto, AddAnnotationExternal> {

    @Rest
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public AddAnnotationExternal toExternal(AnnotationCreationDto input) {
        AddAnnotationExternal external = new AddAnnotationExternal();
        external.setPage(input.getPageId());
        /*
        try {
            external.setData(objectMapper.writeValueAsString(input.getAnnotation()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BusinessException("SERIALIZATION_ERROR", e.getMessage());
        }*/
        external.setData(input.getAnnotation());
        return external;
    }
}
