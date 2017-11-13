package de.hilling.training.annotations;

import java.time.LocalDate;

import org.immutables.serial.Serial;
import org.immutables.value.Value;

@Value.Immutable
public interface Person {

    /**
     * @return Vorname.
     */
    String getFirstName();

    /**
     * @return Familienname.
     */
    String getLastName();

    /**
     * @return Geburtsdatum.
     */
    LocalDate getBirthDay();

    //String getLocation();
}
