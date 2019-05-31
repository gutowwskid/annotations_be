package pl.edu.pw.mini.annotations.service;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.annotations.AnnotationDto;
import pl.edu.pw.mini.core.tools.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class GenericAnnotationDeserializer {

    public Optional<AnnotationDto> deserialize(Object object) {
        List<String> errors = new ArrayList<>();
        Optional<AnnotationDto> dto = deserializeObjectFromList(object, errors, AnnotationDto.class, this::annotationDtoConverter);
        return dto.isPresent() ? dto : Optional.of(AnnotationDto.builder().errors(errors).build());
    }

    private Optional<AnnotationDto> annotationDtoConverter(Object object) {
        if(object instanceof Map<?, ?>) {
            AnnotationDto dto = new AnnotationDto();
            Map<String, Object> mapObject = (Map<String, Object>) object;
            deserializeObjectFromList(mapObject.get("text"), dto.getErrors(), String.class, this::stringConverter).ifPresent(dto::setText);
            deserializeObjectFromList(mapObject.get("x1"), dto.getErrors(), Double.class, this::doubleConverter).ifPresent(dto::setX1);
            deserializeObjectFromList(mapObject.get("x2"), dto.getErrors(), Double.class, this::doubleConverter).ifPresent(dto::setX2);
            deserializeObjectFromList(mapObject.get("y1"), dto.getErrors(), Double.class, this::doubleConverter).ifPresent(dto::setY1);
            deserializeObjectFromList(mapObject.get("y2"), dto.getErrors(), Double.class, this::doubleConverter).ifPresent(dto::setY2);
            deserializeList(mapObject.get("type"), dto.getErrors(), String.class, this::stringConverter).ifPresent(dto::setType);
            deserializeList(mapObject.get("subRegions"), dto.getErrors(), AnnotationDto.class, this::deserialize).ifPresent(dto::setSubRegions);
            deserializeList(mapObject.get("references"), dto.getErrors(), Long.class, this::longConverter).ifPresent(dto::setReferences);
            dto.setAdditionalInfo(mapObject.get("additionalInfo"));
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    private <T> Optional<T> deserializeObjectFromList(Object object, List<String> errors, Class<T> tClass, Function<Object, Optional<T>> converter) {
        if(Objects.isNull(object)) {
            return Optional.empty();
        }
        if(object instanceof Collection<?>) {
            Collection<?> collectionValue = (Collection<?>) object;
            if (collectionValue.isEmpty()) {
                return Optional.empty();
            }
            if(collectionValue.size() == 1) {
                return deserializeObject(collectionValue.stream().findAny(), errors, tClass, converter);
            } else {
                errors.add("Cannot deserialize! Expected: Object, found List<Object>");
                return Optional.empty();
            }
        } else {
            return deserializeObject(object, errors, tClass, converter);
        }
    }

    private <T> Optional<T> deserializeObject(Object object, List<String> errors, Class<T> tClass, Function<Object, Optional<T>> converter) {
        if(Objects.isNull(object)) {
            return Optional.empty();
        } if(tClass.isInstance(object)) {
            return Optional.of(tClass.cast(object));
        } else {
            if(Objects.nonNull(converter)) {
                Optional<T> convertedObject = converter.apply(object);
                if(convertedObject.isPresent()) {
                    return convertedObject;
                }
            }
            errors.add("Cannot deserialize object as " + tClass.toString());
            return Optional.empty();
        }
    }

    private <T> Optional<List<T>> deserializeList(Object object, List<String> errors, Class<T> tClass, Function<Object, Optional<T>> converter) {
        if(Objects.isNull(object)) {
            return Optional.empty();
        }
        if(object instanceof Collection<?>) {
            Collection<?> collectionValue = (Collection<?>) object;
            if(collectionValue.isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(collectionValue.stream().map(o -> deserializeObject(o, errors, tClass, converter)).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList()));
        }  else {
            return deserializeObject(object, errors, tClass, converter).map(Collections::singletonList);
        }
    }

    private Optional<String> stringConverter(Object object) {
        return Optional.ofNullable(object).map(StringUtils::toString);
    }

    private Optional<Double> doubleConverter(Object object) {
        try {
            return stringConverter(object).map(Double::new);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
    private Optional<Long> longConverter(Object object) {
        try {
            return stringConverter(object).map(Long::new);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}