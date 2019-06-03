package pl.edu.pw.mini.publications.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.tools.DateUtils;
import pl.edu.pw.mini.core.tools.DtoAssembler;
import pl.edu.pw.mini.publications.DocumentStatus;
import pl.edu.pw.mini.publications.PublicationInfoDto;
import pl.edu.pw.mini.publications.external.PublicationInfoExternal;

import java.util.Optional;

@Component
public class PublicationInfoDtoAssembler extends DtoAssembler<PublicationInfoExternal, PublicationInfoDto> {

    @Override
    public PublicationInfoDto toDto(PublicationInfoExternal input) {
        PublicationInfoDto dto = new PublicationInfoDto();
        dto.setId(input.getId());
        dto.setName(input.getName());
        dto.setSource(input.getSource());
        Optional.of(input).map(PublicationInfoExternal::getCreated).map(DateUtils::toLocalDate).ifPresent(dto::setCreated);
        dto.setUrl(input.getLocal_file());
        dto.setSourceUrl(input.getRemote_file());
        Optional.of(input).map(PublicationInfoExternal::getPublication_date).map(DateUtils::toLocalDate).ifPresent(dto::setPublicationDate);
        Optional.of(input).map(PublicationInfoExternal::getAnnotation_status).map(DocumentStatus::getByLabel).ifPresent(dto::setStatus);
        return dto;
    }
}
