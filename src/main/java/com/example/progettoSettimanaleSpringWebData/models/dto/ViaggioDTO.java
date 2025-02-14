package com.example.progettoSettimanaleSpringWebData.models.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class ViaggioDTO {

    private String destinazione;

    private String dataViaggio;

    private String statoDelViaggio;
}
