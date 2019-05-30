package pl.edu.pw.mini.annotations.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.AnnotationDto;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GenericAnnotationDeserializer {

    public AnnotationDto deserialize(Object object) {
        AnnotationDto dto = new AnnotationDto();
        if(object instanceof Collection<?>) {
            Collection<Map<String, Object>> objectCollection = (Collection<Map<String, Object>>) object;
            if(objectCollection.isEmpty()) {
                return dto;
            } else if(objectCollection.size() == 1) {
                return deserialize(objectCollection.stream().findAny().get());
            } else {
                setError(dto, "Annotation is a Collection!");
            }
        } else if(object instanceof Map<?, ?>) {
            Map<String, Object> mapObject = (Map<String, Object>) object;
            dto.setText(deserializeString(mapObject, "text"));
            dto.setX1(deserializeDouble(mapObject, "x1", dto));
            dto.setX2(deserializeDouble(mapObject, "x2", dto));
            dto.setY1(deserializeDouble(mapObject, "y1", dto));
            dto.setY2(deserializeDouble(mapObject, "y2", dto));
            dto.setType(deserializeListString(mapObject, "type", dto));
            dto.setSubRegions(deserializeListAnnotation(mapObject, "subRegions", dto));
        } else {
            setError(dto, "Unrecognized annotation!");
            return dto;
        }
        return dto;
    }
    private String deserializeString(Map<String, Object> mapObject, String name) {
        Object value = mapObject.get(name);
        if(Objects.isNull(value)) {
            return null;
        }
        if(value instanceof String) {
            return (String) value;
        } else {
            return value.toString();
        }
    }

    private Double deserializeDouble(Map<String, Object> mapObject, String name, AnnotationDto dto) {
        Object value = mapObject.get(name);
        if(Objects.isNull(value)) {
            return null;
        }
        if(value instanceof Double) {
            return (Double) value;
        } else {
            setError(dto, "Invalid property: " + name);
            return null;
        }
    }

    private List<String> deserializeListString(Map<String, Object> mapObject, String name, AnnotationDto dto) {
        Object value = mapObject.get(name);
        if(Objects.isNull(value)) {
            return null;
        }
        if(value instanceof Collection<?>) {
            Collection<?> collectionValue = (Collection<?>) value;
            if(collectionValue.isEmpty()) {
                return null;
            } else if(collectionValue.stream().findAny().get() instanceof String) {
                return collectionValue.stream().map(s -> (String) s).collect(Collectors.toList());
            } else {
                setError(dto, "Invalid field: type, found Collection<?>, required: List<String>");
                return null;
            }
        } else if(value instanceof String) {
            return Collections.singletonList((String) value);
        } else {
            setError(dto, "Invalid type, found Object, required: List<String>");
            return null;
        }
    }

    private List<AnnotationDto> deserializeListAnnotation(Map<String, Object> mapObject, String name, AnnotationDto dto) {
        Object value = mapObject.get(name);
        if(Objects.isNull(value)) {
            return null;
        }
        if(value instanceof Collection<?>) {
            return ((Collection<Object>) value).stream().map(this::deserialize).collect(Collectors.toList());
        } else if(value instanceof AnnotationDto) {
            return Collections.singletonList(deserialize(value));
        } else {
            return null;
        }
    }

    private void setError(AnnotationDto dto, String error) {
        if(Objects.isNull(dto.getErrors())) {
            dto.setErrors(new ArrayList<>());
        }
        dto.getErrors().add(error);
    }
}