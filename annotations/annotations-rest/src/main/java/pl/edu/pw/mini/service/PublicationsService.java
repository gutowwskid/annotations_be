package pl.edu.pw.mini.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.common.external.ListExternal;
import pl.edu.pw.mini.core.tools.StringUtils;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.publications.PublicationInfoDto;
import pl.edu.pw.mini.publications.PublicationSearchCriteria;
import pl.edu.pw.mini.publications.external.PublicationInfoExternal;

import java.util.List;

@Service
public class PublicationsService {

    @Autowired
    private PublicationsRestInvoker publicationsRestInvoker;

    @Autowired
    private PublicationInfoAssemblerDto publicationInfoAssembler;

    @Autowired
    private PublicationSearchCriteriaExternalAssembler publicationSearchCriteriaExternalAssembler;

    public JsonListChunk<PublicationInfoDto> getPublicationList(JsonListRequest<PublicationSearchCriteria> publicationRequest) {
        ListExternal<PublicationInfoExternal> list =  publicationsRestInvoker.getPublicationList(publicationSearchCriteriaExternalAssembler.toPublicationSearchCriteriaExternal(publicationRequest));
        List<PublicationInfoDto> publicationInfoList = publicationInfoAssembler.toDtoList(list.getResults());

        return new JsonListChunk<>(
                publicationInfoList,
                list.getCount(),
                StringUtils.notEmpty(list.getNext())
        );
    }

    public PublicationInfoDto getPublicationDetails(Long id) {
        return publicationInfoAssembler.toDto(publicationsRestInvoker.getPublicationDetails(id.toString()));
    }
}
