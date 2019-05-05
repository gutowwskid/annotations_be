package pl.edu.pw.mini.publications.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.common.external.ListResponseExternal;
import pl.edu.pw.mini.core.tools.StringUtils;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.publications.PageInfoDto;
import pl.edu.pw.mini.publications.PublicationInfoDto;
import pl.edu.pw.mini.publications.PagesSearchCriteria;
import pl.edu.pw.mini.publications.PublicationSearchCriteria;
import pl.edu.pw.mini.publications.external.PageInfoExternal;
import pl.edu.pw.mini.publications.external.PublicationInfoExternal;

import java.util.List;

@Service
public class PublicationsService {

    @Autowired
    private PublicationsRestInvoker publicationsRestInvoker;

    @Autowired
    private PublicationInfoDtoAssembler publicationInfoAssembler;

    @Autowired
    private PublicationSearchCriteriaExternalAssembler publicationSearchCriteriaExternalAssembler;

    @Autowired
    private PagesSearchCriteriaExternalAssembler pagesSearchCriteriaExternalAssembler;

    @Autowired
    private PageInfoDtoAssembler pageInfoDtoAssembler;

    public JsonListChunk<PublicationInfoDto> getPublicationList(JsonListRequest<PublicationSearchCriteria> publicationRequest) {
        ListResponseExternal<PublicationInfoExternal> list =  publicationsRestInvoker.getPublicationList(publicationSearchCriteriaExternalAssembler.toPublicationSearchCriteriaExternal(publicationRequest));
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

    public JsonListChunk<PageInfoDto> getPublicationPages(JsonListRequest<PagesSearchCriteria> publicationPageRequest) {
        ListResponseExternal<PageInfoExternal> list = publicationsRestInvoker.getPublicationPages(pagesSearchCriteriaExternalAssembler.toPagesSearchCriteriaExternal(publicationPageRequest));
        List<PageInfoDto> pageInfoList = pageInfoDtoAssembler.toDtoList(list.getResults());

        return new JsonListChunk<>(
                pageInfoList,
                list.getCount(),
                StringUtils.notEmpty(list.getNext())
        );
    }
}
