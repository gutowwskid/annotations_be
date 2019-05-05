package pl.edu.pw.mini.publications.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.core.tools.DateUtils;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.Period;
import pl.edu.pw.mini.publications.PublicationSearchCriteria;
import pl.edu.pw.mini.publications.external.PublicationSearchCriteriaExternal;

import java.util.Optional;

@Component
public class PublicationSearchCriteriaExternalAssembler {

    public PublicationSearchCriteriaExternal toPublicationSearchCriteriaExternal(JsonListRequest<PublicationSearchCriteria> request) {
        PublicationSearchCriteriaExternal external = new PublicationSearchCriteriaExternal();
        external.setPage(request.getPageNumber());
        external.setPage_size(request.getPageSize());

        Optional.of(request).map(JsonListRequest::getSearchCriteria).map(PublicationSearchCriteria::getName).ifPresent(external::setName);
        Optional.of(request)
                .map(JsonListRequest::getSearchCriteria)
                .map(PublicationSearchCriteria::getCreationPeriod)
                .map(Period::getFrom)
                .map(DateUtils::toDate)
                .ifPresent(external::setCreated_before);

        Optional.of(request)
                .map(JsonListRequest::getSearchCriteria)
                .map(PublicationSearchCriteria::getCreationPeriod)
                .map(Period::getTo)
                .map(DateUtils::toDate)
                .ifPresent(external::setCreated_after);

        Optional.of(request)
                .map(JsonListRequest::getSearchCriteria)
                .map(PublicationSearchCriteria::getPublicationPeriod)
                .map(Period::getFrom)
                .map(DateUtils::toDate)
                .ifPresent(external::setPublication_date_before);

        Optional.of(request)
                .map(JsonListRequest::getSearchCriteria)
                .map(PublicationSearchCriteria::getPublicationPeriod)
                .map(Period::getTo)
                .map(DateUtils::toDate)
                .ifPresent(external::setPublication_date_after);

        Optional.of(request).map(JsonListRequest::getSearchCriteria).map(PublicationSearchCriteria::getSource).ifPresent(external::setSource);
        Optional.of(request).map(JsonListRequest::getSearchCriteria).map(PublicationSearchCriteria::getStatus).ifPresent(external::setStatus);

        return external;
    }
}
