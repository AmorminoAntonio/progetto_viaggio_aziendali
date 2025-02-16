package com.example.progettoSettimanaleSpringWebData.models.dto;


import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.enumerated.StatoDelViaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;


@Data
public class ViaggioDTO {
    @NotNull(message = "Il campo destinazione è obbligatorio")
    @NotBlank(message = "Il campo destinazione non può essere vuoto")
    @Size(min = 3, max = 15, message = "Il nome della destinazione deve essere lungo minimo 4 e massimo 15 caratteri")
    private String destinazione;

    @NotNull(message = "Il campo data è obbligatorio")
    private LocalDate data;

    @NotNull(message = "Il campo stato è obbligatorio")
    private StatoDelViaggio stato;

    private Dipendente dipendente;
}