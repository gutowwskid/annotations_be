package pl.edu.pw.mini.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.common.external.ListExternal;
import pl.edu.pw.mini.core.invoker.rest.RestInvoker;
import pl.edu.pw.mini.core.tools.FluentMapBuilder;
import pl.edu.pw.mini.publications.external.PublicationInfoExternal;
import pl.edu.pw.mini.publications.external.PublicationSearchCriteriaExternal;

import java.lang.reflect.Type;
import java.util.Map;

@Component
public class PublicationsRestInvoker {

    @Autowired
    private RestInvoker restInvoker;

    @Autowired
    private ObjectMapper objectMapper;

    public ListExternal<PublicationInfoExternal> getPublicationList(PublicationSearchCriteriaExternal searchCriteriaExternal) {
        Map<String, String> params = objectMapper.convertValue(searchCriteriaExternal, new TypeReference<Map<String, String>>() {});
        return restInvoker.get("/publications", params, new ParameterizedTypeReference<ListExternal<PublicationInfoExternal>>() {});
    }

    public PublicationInfoExternal getPublicationDetails(String id) {
        Map<String, String> params = FluentMapBuilder.<String, String>aMap().with("id", id).build();
        return restInvoker.get("/publications/{id}", params, PublicationInfoExternal.class);
    }
}
