package pl.edu.pw.mini.publications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.mini.core.security.authorization.AllowAuthenticated;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.publications.service.PublicationsService;

@RestController
public class PublicationsController {

    @Autowired
    private PublicationsService service;

    @AllowAuthenticated
    @RequestMapping(path = "/publications", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<PublicationInfoDto> getPublicationList(@RequestBody JsonListRequest<PublicationSearchCriteria> publicationRequest) {
        return service.getPublicationList(publicationRequest);
    }

    @AllowAuthenticated
    @RequestMapping(path = "/publications/{id}", method = RequestMethod.GET, produces = "application/json")
    public PublicationInfoDto getPublicationDetails(@PathVariable Long id) {
        return service.getPublicationDetails(id);
    }

    @AllowAuthenticated
    @RequestMapping(path = "/publications/pages", method = RequestMethod.POST, produces = "application/json")
    public JsonListChunk<PageInfoDto> getPublicationPages(@RequestBody JsonListRequest<PagesSearchCriteria> publicationPageRequest) {
        return service.getPublicationPages(publicationPageRequest);
    }
}
