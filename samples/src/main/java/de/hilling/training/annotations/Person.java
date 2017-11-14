package de.hilling.training.annotations;

import java.time.LocalDate;

import org.immutables.value.Value;

@Value.Immutable
public interface Person {

    String getFirstName();

    String getLastName();

    LocalDate getBirthDay();

}
