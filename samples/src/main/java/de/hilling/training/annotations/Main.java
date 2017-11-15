package de.hilling.training.annotations;

import java.time.LocalDate;
import java.time.Month;

public class Main {

    public static void main(String args[]) {

        ImmutablePerson gunnar =
        // tag::creation[]
        ImmutablePerson.builder()
                       .birthDay(LocalDate.of(1971, Month.JUNE, 15))
                       .firstName("Gunnar")
                       .lastName("Hilling")
                       .location("Osnabrück")
                       .build();
        // end::creation[]
        System.out.println(gunnar);

        ImmutablePerson.builder().from(gunnar).firstName("Lasse").build();
    }
}
