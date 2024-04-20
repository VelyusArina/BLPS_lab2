package ru.artemiyandarina.blps_lab2.schemas.petition;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class PetitionCreate extends PetitionBase {
    @Size(min = 3, max = 30)
    String country;
}
