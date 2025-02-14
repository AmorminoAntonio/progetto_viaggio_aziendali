package com.example.progettoSettimanaleSpringWebData.models.dto;

import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.models.entities.Viaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class PrenotazioneDTO {

    @NotNull(message = "questo è un campo obbligatorio")
    @NotBlank(message = "questo risulta vuoto")
    private Viaggio viaggioId;

    @NotNull(message = "questo è un campo obbligatorio")
    @NotBlank(message = "questo risulta vuoto")
    private Dipendente dipendenteId;

    @NotNull(message = "questo è un campo obbligatorio")
    @NotBlank(message = "questo risulta vuoto")
    private String dataPrenotazione;
}
