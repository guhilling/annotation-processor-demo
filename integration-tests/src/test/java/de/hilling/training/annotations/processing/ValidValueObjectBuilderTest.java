package de.hilling.training.annotations.processing;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValidValueObjectBuilderTest {

    public static final String FIRST_NAME = "gunnar";
    public static final String LAST_NAME  = "hilling";

    @Test
    public void buildObject() {
        ValidValueObject valueObject = new ValidValueObjectBuilder().withFirstName(FIRST_NAME).withLastName(LAST_NAME).build();
        assertEquals(FIRST_NAME, valueObject.getFirstName());
        assertEquals(LAST_NAME, valueObject.getLastName());
    }
}
