package de.hilling.training.annotations;

import java.time.LocalDate;

public class Nursery {

    public static void main(String args[]) {
        final ImmutablePerson person = ImmutablePerson.builder()
                                                      .birthDay(LocalDate.ofYearDay(1971, 166))
                                                      .firstName("Gunnar")
                                                      .lastName("Hilling")
                                                      .build();
        System.out.println(person);
    }
}
