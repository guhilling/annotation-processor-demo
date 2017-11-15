package de.hilling.training.annotations;

import java.time.LocalDate;
import java.util.Optional;

import org.immutables.value.Value;

// tag::person[]
@Value.Immutable
public interface Person {
    String getFirstName();
    String getLastName();
    LocalDate getBirthDay();
    Optional<String> getLocation();
}
// end::person[]