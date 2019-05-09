package pl.edu.pw.mini.publications.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.common.external.ListResponseExternal;
import pl.edu.pw.mini.core.invoker.rest.Rest;
import pl.edu.pw.mini.core.invoker.rest.RestInvoker;
import pl.edu.pw.mini.core.tools.FluentMapBuilder;
import pl.edu.pw.mini.publications.external.PageInfoExternal;
import pl.edu.pw.mini.publications.external.PagesSearchCriteriaExternal;
import pl.edu.pw.mini.publications.external.PublicationInfoExternal;
import pl.edu.pw.mini.publications.external.PublicationSearchCriteriaExternal;

import java.util.Map;

@Component
public class PublicationsRestInvoker {

    @Autowired
    private RestInvoker restInvoker;

    @Rest
    @Autowired
    private ObjectMapper objectMapper;

    public ListResponseExternal<PublicationInfoExternal> getPublicationList(PublicationSearchCriteriaExternal searchCriteriaExternal) {
        Map<String, String> params = objectMapper.convertValue(searchCriteriaExternal, new TypeReference<Map<String, String>>() {});
        String url = restInvoker.createRequestUrl("/publications", params);
        return restInvoker.get(url, params, new ParameterizedTypeReference<ListResponseExternal<PublicationInfoExternal>>() {});
    }

    public PublicationInfoExternal getPublicationDetails(String id) {
        Map<String, String> params = FluentMapBuilder.<String, String>aMap().with("id", id).build();
        return restInvoker.get("/publications/{id}", params, PublicationInfoExternal.class);
    }

    public ListResponseExternal<PageInfoExternal> getPublicationPages(PagesSearchCriteriaExternal searchCriteriaExternal) {
        Map<String, String> params = objectMapper.convertValue(searchCriteriaExternal, new TypeReference<Map<String, String>>() {});
        String url = restInvoker.createRequestUrl("/publications/pages", params);
        return restInvoker.get(url, params, new ParameterizedTypeReference<ListResponseExternal<PageInfoExternal>>() {});
    }
}
