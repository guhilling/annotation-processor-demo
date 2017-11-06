package de.hilling.training.annotations.processing;

@ValueObject
public class ValidValueObject {

    private String  name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void otherMethod() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
