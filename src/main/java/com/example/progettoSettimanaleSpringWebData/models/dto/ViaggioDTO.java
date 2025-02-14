package com.example.progettoSettimanaleSpringWebData.models.dto;

import com.example.progettoSettimanaleSpringWebData.enumerated.StatoDelViaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ViaggioDTO {
    @NotNull(message = "Il cognome è un campo obbligatorio")
    @NotBlank(message = "Il cognome risulta vuoto")
    @Size(min = 3, message = "Cognome troppo corto")
    @Size(max = 25, message = "Cognome  troppo lungo")
    private String destinazione;

    @NotNull(message = "Il cognome è un campo obbligatorio")
    @NotBlank(message = "Il cognome risulta vuoto")
    private LocalDate dataViaggio;

    private StatoDelViaggio statoDelViaggio;
}
