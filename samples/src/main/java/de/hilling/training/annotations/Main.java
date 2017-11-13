package de.hilling.training.annotations;

import java.time.LocalDate;

public class Main {

    public static void main(String args[]) {
        final ImmutablePerson person = ImmutablePerson.builder()
                                                      .birthDay(LocalDate.ofYearDay(1971, 166))
                                                      .firstName("Gunnar")
                                                      .lastName("Hilling")
                                                      .build();
        System.out.println(person);
    }
}
