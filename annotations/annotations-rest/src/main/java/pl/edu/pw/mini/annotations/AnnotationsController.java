package pl.edu.pw.mini.annotations;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pw.mini.core.security.authorization.AllowAuthenticated;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;

@RestController
public class AnnotationsController {

    @AllowAuthenticated
    @RequestMapping(path = "/annotations/list", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<AnnotationInfoDto> getAnnotations(@RequestBody JsonListRequest<AnnotationSearchCriteria> criteria) {
        return null;
    }

    @AllowAuthenticated
    @RequestMapping(path = "/annotations/new", method = RequestMethod.POST, produces = "application/json")
    public Long createAnnotation(@RequestBody AnnotationCreationDto creationDto) {
        return 0L;
    }
}
