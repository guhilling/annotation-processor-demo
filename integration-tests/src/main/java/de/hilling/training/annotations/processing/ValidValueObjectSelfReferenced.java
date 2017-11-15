package de.hilling.training.annotations.processing;

@ValueObject
public class ValidValueObjectSelfReferenced {

    private String name;
    private String lastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ValidValueObjectSelfReferencedBuilder builder() {
        return new ValidValueObjectSelfReferencedBuilder();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
