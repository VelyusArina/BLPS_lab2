package ru.artemiyandarina.blps_lab2.schemas.petition;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PetitionBase {
    @Schema(example = "Результаты проверки.")
    @NotBlank
    @Size(max = 50)
    private String title;

    @Schema(example = "Очень важные результаты проверки.")
    @NotBlank
    @Size(max = 512)
    private String description;

}
