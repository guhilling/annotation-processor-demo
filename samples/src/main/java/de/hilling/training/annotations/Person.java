package de.hilling.training.annotations;

import java.time.LocalDate;

import org.immutables.value.Value;

// tag::person[]
@Value.Immutable
public interface Person {
    String getFirstName();
    String getLastName();
    LocalDate getBirthDay();
}
// end::person[]