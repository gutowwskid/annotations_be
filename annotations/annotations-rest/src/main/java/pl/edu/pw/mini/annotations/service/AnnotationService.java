package pl.edu.pw.mini.annotations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pw.mini.annotations.AnnotationCreationDto;
import pl.edu.pw.mini.annotations.AnnotationDto;
import pl.edu.pw.mini.annotations.AnnotationInfoDto;
import pl.edu.pw.mini.annotations.AnnotationSearchCriteria;
import pl.edu.pw.mini.annotations.external.AddAnnotationExternal;
import pl.edu.pw.mini.annotations.external.AnnotationInfoExternal;
import pl.edu.pw.mini.annotations.external.AnnotationSearchCriteriaExternal;
import pl.edu.pw.mini.common.external.ListResponseExternal;
import pl.edu.pw.mini.core.tools.ContextService;
import pl.edu.pw.mini.core.tools.StringUtils;
import pl.edu.pw.mini.model.JsonListChunk;
import pl.edu.pw.mini.model.JsonListRequest;
import pl.edu.pw.mini.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Long> createAnnotation(List<AnnotationCreationDto> creationDto) {
        if(!ContextService.getContext().getProfile().equals(Profile.SUPER_ANNOTATOR.name())) {
            List<Long> pages = creationDto.stream().map(AnnotationCreationDto::getPageId).distinct().collect(Collectors.toList());
            List<AnnotationInfoDto> existingAnnotations = pages.stream().map(this::getAnnotationsByPage).flatMap(List::stream).collect(Collectors.toList());
            //TODO
        }

        List<AddAnnotationExternal> external = addAnnotationExternalAssembler.toExternalList(creationDto);
        List<AnnotationInfoExternal> annotationInfoExternal = restInvoker.addAnnotation(external);
        return annotationInfoExternal.stream().map(AnnotationInfoExternal::getId).collect(Collectors.toList());
    }
    private List<AnnotationInfoDto> getAnnotationsByPage(Long pageId) {
        Long pageNumber = 1L;
        List<AnnotationInfoExternal> finalList = new ArrayList<>();
        while (true) {
            AnnotationSearchCriteriaExternal external = AnnotationSearchCriteriaExternal.builder().page_id(pageId).page(pageNumber).page_size(1000L).build();
            ListResponseExternal<AnnotationInfoExternal> list = restInvoker.getAnnotations(external);
            finalList.addAll(list.getResults());
            pageNumber++;
            if(StringUtils.isEmpty(list.getNext())) {
                break;
            }
        }
        return annotationInfoDtoAssembler.toDtoList(finalList);
    }

    public Boolean isSimilarAnnotation(AnnotationDto first, AnnotationDto second) {
        if(!isSimilarAnnotationType(first, second)) {
            return false;
        }
        if(!areSimilarAnnotationCoordinates(first, second)) {
            return false;
        }
        return areSimilarAnnotationSubRegions(first, second);
    }

    private Boolean isSimilarAnnotationType(AnnotationDto first, AnnotationDto second) {
        return first.getType().containsAll(second.getType()) && second.getType().containsAll(first.getType());
    }

    private Boolean areSimilarAnnotationCoordinates(AnnotationDto first, AnnotationDto second) {
        Double mutualWidth = getMutualWidth(first, second);
        if(mutualWidth < 0.0) {
            return false;
        }
        Double mutualHeight = getMutualHeight(first, second);
        if(mutualHeight < 0.0) {
            return false;
        }
        double mutualSurfaceArea = mutualWidth * mutualHeight;
        double firstSurfaceArea = (first.getX2() - first.getX1()) * (first.getY2() - first.getY1());
        double secondSurfaceArea = (second.getX2() - second.getX1()) * (second.getY2() - second.getY1());
        if(mutualSurfaceArea < 0.9 * firstSurfaceArea) {
            return false;
        }
        return mutualSurfaceArea >= 0.9 * secondSurfaceArea;
    }

    private Double getMutualWidth(AnnotationDto first, AnnotationDto second) {
        return Math.min(first.getX2(), second.getX2()) - Math.max(first.getX1(), second.getX1());
    }

    private Double getMutualHeight(AnnotationDto first, AnnotationDto second) {
        return Math.min(first.getY2(), second.getY2()) - Math.max(first.getY1(), second.getY1());
    }

    private Boolean areSimilarAnnotationSubRegions(AnnotationDto first, AnnotationDto second) {
        return true; //TODO MOCK!
    }

}
