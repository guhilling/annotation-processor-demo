package de.hilling.training.annotations.processing;

@ValueObject
public class ValidValueObjectSelfReferenced {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ValidValueObjectSelfReferencedBuilder builder() {
        return new ValidValueObjectSelfReferencedBuilder();
    }

}
