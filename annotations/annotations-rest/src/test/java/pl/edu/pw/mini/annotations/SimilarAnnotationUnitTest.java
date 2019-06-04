package pl.edu.pw.mini.annotations;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.pw.mini.annotations.service.AnnotationService;

import java.util.ArrayList;
import java.util.Collections;


public class SimilarAnnotationUnitTest {

    @Test
    public void firstContainSecondTest1() {
        AnnotationDto first = new AnnotationDto();
        first.setX1(0.1);
        first.setY1(0.3);
        first.setX2(0.4);
        first.setY2(0.6);
        first.setType(Collections.singletonList("TYPE1"));
        first.setSubRegions(new ArrayList<>());

        AnnotationDto second = new AnnotationDto();
        second.setX1(0.2);
        second.setY1(0.3);
        second.setX2(0.3);
        second.setY2(0.5);
        second.setType(Collections.singletonList("TYPE1"));
        second.setSubRegions(new ArrayList<>());

        Assert.assertFalse(new AnnotationService().isSimilarAnnotation(first, second));
    }
    @Test
    public void firstContainSecondTest2() {
        AnnotationDto first = new AnnotationDto();
        first.setX1(0.1);
        first.setY1(0.3);
        first.setX2(0.81);
        first.setY2(0.91);
        first.setType(Collections.singletonList("TYPE2"));
        first.setSubRegions(new ArrayList<>());

        AnnotationDto second = new AnnotationDto();
        second.setX1(0.11);
        second.setY1(0.31);
        second.setX2(0.8);
        second.setY2(0.9);
        second.setType(Collections.singletonList("TYPE2"));
        second.setSubRegions(new ArrayList<>());

        Assert.assertTrue(new AnnotationService().isSimilarAnnotation(first, second));
    }

    @Test
    public void testTest() {
        AnnotationDto first = new AnnotationDto();
        first.setX1(0.09057138480392157);
        first.setY1(0.06212735732977567);
        first.setX2(0.48885569852941174);
        first.setY2(0.2960004737630626);
        first.setType(Collections.singletonList("TYPE2"));
        first.setSubRegions(new ArrayList<>());

        AnnotationDto second = new AnnotationDto();
        second.setX1(0.08605877246732026);
        second.setY1(0.04817391485565468);
        second.setX2(0.5842205371732027);
        second.setY2(0.2960004737630626);
        second.setType(Collections.singletonList("TYPE2"));
        second.setSubRegions(new ArrayList<>());

        Assert.assertTrue(new AnnotationService().isSimilarAnnotation(first, second));
    }
}
