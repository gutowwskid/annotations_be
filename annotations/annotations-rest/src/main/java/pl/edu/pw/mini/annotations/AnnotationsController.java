package pl.edu.pw.mini.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.annotations.service.AnnotationService;
import pl.edu.pw.mini.core.security.authorization.AllowAuthenticated;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;

import java.util.List;

@RestController
public class AnnotationsController {

    @Autowired
    private AnnotationService service;

    @AllowAuthenticated
    @RequestMapping(path = "/annotations/list", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<AnnotationInfoDto> getAnnotations(@RequestBody JsonListRequest<AnnotationSearchCriteria> criteria) {
        return service.getAnnotations(criteria);
    }

    @AllowAuthenticated
    @RequestMapping(path = "/annotations/new", method = RequestMethod.POST, produces = "application/json")
    public List<Long> createAnnotation(@RequestBody List<AnnotationCreationDto> creationDto) {
        return service.createAnnotation(creationDto);
    }
}
