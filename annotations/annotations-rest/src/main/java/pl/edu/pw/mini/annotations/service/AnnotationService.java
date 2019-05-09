package pl.edu.pw.mini.annotations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.annotations.AnnotationCreationDto;
import pl.edu.pw.mini.annotations.AnnotationInfoDto;
import pl.edu.pw.mini.annotations.AnnotationSearchCriteria;
import pl.edu.pw.mini.annotations.external.AddAnnotationExternal;
import pl.edu.pw.mini.annotations.external.AnnotationInfoExternal;
import pl.edu.pw.mini.common.external.ListResponseExternal;
import pl.edu.pw.mini.core.tools.StringUtils;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;

import java.util.List;

@Service
public class AnnotationService {

    @Autowired
    private AnnotationRestInvoker restInvoker;

    @Autowired
    private AddAnnotationExternalAssembler addAnnotationExternalAssembler;

    @Autowired
    private AnnotationInfoDtoAssembler annotationInfoDtoAssembler;

    @Autowired
    private AnnotationCriteriaExternalAssembler annotationCriteriaExternalAssembler;

    public JsonListChunk<AnnotationInfoDto> getAnnotations(JsonListRequest<AnnotationSearchCriteria> criteria) {
        ListResponseExternal<AnnotationInfoExternal> list = restInvoker.getAnnotations(annotationCriteriaExternalAssembler.toCriteriaExternal(criteria));
        List<AnnotationInfoDto> annotationInfoList = annotationInfoDtoAssembler.toDtoList(list.getResults());

        return new JsonListChunk<>(
                annotationInfoList,
                list.getCount(),
                StringUtils.notEmpty(list.getNext())
        );
    }

    public Long createAnnotation(AnnotationCreationDto creationDto) {
        AddAnnotationExternal external = addAnnotationExternalAssembler.toAddAnnotationExternal(creationDto);
        AnnotationInfoExternal annotationInfoExternal = restInvoker.addAnnotation(external);
        return annotationInfoExternal.getId();
    }
}
