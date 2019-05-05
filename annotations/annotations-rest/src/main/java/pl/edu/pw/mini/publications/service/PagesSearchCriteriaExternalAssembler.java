package pl.edu.pw.mini.publications.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.publications.PagesSearchCriteria;
import pl.edu.pw.mini.publications.external.PagesSearchCriteriaExternal;

import java.util.Optional;

@Component
public class PagesSearchCriteriaExternalAssembler {
    public PagesSearchCriteriaExternal toPagesSearchCriteriaExternal(JsonListRequest<PagesSearchCriteria> request) {
        PagesSearchCriteriaExternal external = new PagesSearchCriteriaExternal();
        external.setPage(request.getPageNumber());
        external.setPage_size(request.getPageSize());

        Optional.of(request).map(JsonListRequest::getSearchCriteria).map(PagesSearchCriteria::getPublicationId).ifPresent(external::setPublication_id);
        Optional.of(request).map(JsonListRequest::getSearchCriteria).map(PagesSearchCriteria::getStatus).ifPresent(external::setStatus);

        return external;
    }
}
