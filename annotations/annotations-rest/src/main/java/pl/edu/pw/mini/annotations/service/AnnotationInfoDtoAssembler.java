package pl.edu.pw.mini.annotations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.AnnotationDto;
import pl.edu.pw.mini.annotations.AnnotationInfoDto;
import pl.edu.pw.mini.annotations.external.AnnotationInfoExternal;
import pl.edu.pw.mini.core.exceptions.BusinessException;
import pl.edu.pw.mini.core.invoker.rest.Rest;
import pl.edu.pw.mini.core.tools.DateUtils;
import pl.edu.pw.mini.core.tools.DtoAssebler;
import pl.edu.pw.mini.core.tools.MockLogger;
import pl.edu.pw.mini.users.UserDto;

import java.io.IOException;
import java.util.Optional;

@Component
public class AnnotationInfoDtoAssembler extends DtoAssebler<AnnotationInfoExternal, AnnotationInfoDto> {

    @Rest
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public AnnotationInfoDto toDto(AnnotationInfoExternal input) {
        AnnotationInfoDto dto = new AnnotationInfoDto();
        dto.setId(input.getId());
        dto.setCreator(UserDto.builder()
                .displayName(MockLogger.getString("Jank Kowalski"))
                .id(input.getId())
                .build());
        dto.setStatus(input.getAnnotation_status());
        Optional.of(input).map(AnnotationInfoExternal::getCreated).map(DateUtils::toLocalDate).ifPresent(dto::setCreationDate);

        try {
            AnnotationDto annotationDto = objectMapper.readValue(input.getData(), AnnotationDto.class);
            dto.setData(annotationDto);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BusinessException("DESERIALIZATION_ERROR", e.getMessage());
        }
        return dto;
    }
}
