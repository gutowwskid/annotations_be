package pl.edu.pw.mini.annotations.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.external.AddAnnotationExternal;
import pl.edu.pw.mini.annotations.external.AnnotationInfoExternal;
import pl.edu.pw.mini.annotations.external.AnnotationSearchCriteriaExternal;
import pl.edu.pw.mini.common.external.ListResponseExternal;
import pl.edu.pw.mini.core.invoker.rest.Rest;
import pl.edu.pw.mini.core.invoker.rest.RestInvoker;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class AnnotationRestInvoker {

    @Autowired
    private RestInvoker restInvoker;

    @Rest
    @Autowired
    private ObjectMapper objectMapper;

    public ListResponseExternal<AnnotationInfoExternal> getAnnotations(AnnotationSearchCriteriaExternal searchCriteriaExternal) {
        Map<String, String> params = objectMapper.convertValue(searchCriteriaExternal, new TypeReference<Map<String, String>>() {});
        String url = restInvoker.createRequestUrl("/publications/annotations", params);
        return restInvoker.get(url, params, new ParameterizedTypeReference<ListResponseExternal<AnnotationInfoExternal>>() {});
    }

    public List<AnnotationInfoExternal> addAnnotation(List<AddAnnotationExternal> addAnnotationExternal) {
        return restInvoker.post("/publications/annotations", Collections.emptyMap(), addAnnotationExternal, new ParameterizedTypeReference<List<AnnotationInfoExternal>>() {});
    }
}
