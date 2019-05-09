package pl.edu.pw.mini.publications.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.tools.DtoAssembler;
import pl.edu.pw.mini.publications.PageInfoDto;
import pl.edu.pw.mini.publications.PageStatus;
import pl.edu.pw.mini.publications.external.PageInfoExternal;

import java.util.Optional;

@Component
public class PageInfoDtoAssembler extends DtoAssembler<PageInfoExternal, PageInfoDto> {

    @Override
    public PageInfoDto toDto(PageInfoExternal input) {
        PageInfoDto dto = new PageInfoDto();
        dto.setId(input.getId());
        dto.setPublicationId(input.getPublication());
        dto.setNumber(input.getNumber());
        Optional.of(input).map(PageInfoExternal::getAnnotation_status).map(PageStatus::getByLabel).ifPresent(dto::setStatus);
        dto.setImageUrl(input.getImage());
        return dto;
    }
}
