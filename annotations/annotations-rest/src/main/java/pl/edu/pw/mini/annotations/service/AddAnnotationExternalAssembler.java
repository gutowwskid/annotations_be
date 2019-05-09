package pl.edu.pw.mini.annotations.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.AnnotationCreationDto;
import pl.edu.pw.mini.annotations.external.AddAnnotationExternal;
import pl.edu.pw.mini.core.exceptions.BusinessException;
import pl.edu.pw.mini.core.invoker.rest.Rest;

@Component
public class AddAnnotationExternalAssembler {

    @Rest
    @Autowired
    private ObjectMapper objectMapper;

    public AddAnnotationExternal toAddAnnotationExternal(AnnotationCreationDto creationDto) {
        AddAnnotationExternal external = new AddAnnotationExternal();
        external.setPage(creationDto.getPageId());
        try {
            external.setData(objectMapper.writeValueAsString(creationDto.getAnnotation()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BusinessException("SERIALIZATION_ERROR", e.getMessage());
        }
        return external;
    }
}
