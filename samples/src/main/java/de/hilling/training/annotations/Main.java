package de.hilling.training.annotations;

import java.time.LocalDate;

public class Main {

    public static void main(String args[]) {

        // tag::creation[]
        ImmutablePerson gunnar = ImmutablePerson.builder()
                                                .birthDay(LocalDate.of(1971, 15, 6))
                                                .firstName("Gunnar")
                                                .lastName("Hilling").build();
        // end::creation[]
        System.out.println(gunnar);

        ImmutablePerson.builder().from(gunnar).firstName("Lasse").build();
    }
}
