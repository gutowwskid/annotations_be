package pl.edu.pw.mini.annotations.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.AnnotationSearchCriteria;
import pl.edu.pw.mini.annotations.external.AnnotationSearchCriteriaExternal;
import pl.edu.pw.mini.model.JsonListRequest;

import java.util.Optional;

@Component
public class AnnotationCriteriaExternalAssembler {

    public AnnotationSearchCriteriaExternal toCriteriaExternal(JsonListRequest<AnnotationSearchCriteria> criteria) {
        AnnotationSearchCriteriaExternal criteriaExternal = new AnnotationSearchCriteriaExternal();
        criteriaExternal.setPage(criteria.getPageNumber());
        criteriaExternal.setPage_size(criteria.getPageSize());
        Optional.of(criteria).map(JsonListRequest::getSearchCriteria).map(AnnotationSearchCriteria::getPublicationId).ifPresent(criteriaExternal::setPage__publication_id);
        Optional.of(criteria).map(JsonListRequest::getSearchCriteria).map(AnnotationSearchCriteria::getPageId).ifPresent(criteriaExternal::setPage_id);
        return criteriaExternal;
    }
}
