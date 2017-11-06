package de.hilling.training.annotations.processing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ValidValueObjectSelfReferencedTest {

    public static final String EXPECTED = "sample";

    @Test
    public void createFromReferencedObject() {
        ValidValueObjectSelfReferencedBuilder builder = ValidValueObjectSelfReferenced.builder();
        assertNotNull(builder);
    }

    @Test
    public void useBuilder() {
        ValidValueObjectSelfReferenced sample = ValidValueObjectSelfReferenced.builder().withName(EXPECTED).build();
        assertEquals(EXPECTED, sample.getName());
    }
}
