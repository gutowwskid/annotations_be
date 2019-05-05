package pl.edu.pw.mini.publications.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.tools.DtoAssebler;
import pl.edu.pw.mini.publications.PageInfoDto;
import pl.edu.pw.mini.publications.external.PageInfoExternal;

@Component
public class PageInfoDtoAssembler extends DtoAssebler<PageInfoExternal, PageInfoDto> {

    @Override
    public PageInfoDto toDto(PageInfoExternal input) {
        PageInfoDto dto = new PageInfoDto();
        dto.setId(input.getId());
        dto.setPublicationId(input.getPublication());
        dto.setNumber(input.getNumber());
        dto.setStatus(input.getStatus());
        dto.setImageUrl(input.getImage());
        return dto;
    }
}
