package de.hilling.training.annotations;

import java.time.LocalDate;

public class Main {

    public static void main(String args[]) {
        final ImmutablePerson gunnar = ImmutablePerson.builder()
                                                      .birthDay(LocalDate.ofYearDay(1971, 166))
                                                      .firstName("Gunnar")
                                                      .lastName("Hilling")
                                                      .build();
        System.out.println(gunnar);

        ImmutablePerson.builder().from(gunnar)
                       .firstName("Lasse")
                       .build();
    }
}
