package pl.edu.pw.mini.annotations.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.AnnotationInfoDto;
import pl.edu.pw.mini.annotations.external.AnnotationInfoExternal;
import pl.edu.pw.mini.core.invoker.rest.Rest;
import pl.edu.pw.mini.core.tools.DateUtils;
import pl.edu.pw.mini.core.tools.DtoAssembler;
import pl.edu.pw.mini.users.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AnnotationInfoDtoAssembler extends DtoAssembler<AnnotationInfoExternal, AnnotationInfoDto> {

    @Rest
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GenericAnnotationDeserializer genericAnnotationDeserializer;

    @Override
    public AnnotationInfoDto toDto(AnnotationInfoExternal input) {
        AnnotationInfoDto dto = new AnnotationInfoDto();
        dto.setId(input.getId());

        Optional.of(input).map(AnnotationInfoExternal::getUser).map(user -> UserDto.builder()
                    .displayName(user.getFirst_name() + " " + user.getLast_name())
                    .id(user.getId())
                    .build()).
                ifPresent(dto::setCreator);
        dto.setStatus(input.getAnnotation_status());
        Optional.of(input).map(AnnotationInfoExternal::getCreated).map(DateUtils::toLocalDate).ifPresent(dto::setCreationDate);
        dto.setPageId(input.getPage());
        dto.setTags(input.getTags());
        genericAnnotationDeserializer.deserialize(input.getData()).ifPresent(dto::setData);
        return dto;
    }
}
