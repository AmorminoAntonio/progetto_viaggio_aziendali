package com.example.progettoSettimanaleSpringWebData.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;


@Data
public class PrenotazioneDTO {
    @NotNull(message = "Il campo viaggio è obbligatorio")
    private long viaggio_id;

    @NotNull(message = "Il campo dipendente è obbligatorio")
    private long dipendente_id;

    @NotNull(message = "Il campo data è obbligatorio")
    private LocalDate data;

    @Size(max = 200, message = "Le preferenze possono avere max 200 caratteri")
    private String notePreferenze;
}