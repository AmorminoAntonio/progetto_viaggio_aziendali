package com.example.progettoSettimanaleSpringWebData.models.entities;

import com.example.progettoSettimanaleSpringWebData.enumerated.StatoDelViaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Viaggi")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String destinazione;
    private LocalDate dataViaggio;
    private StatoDelViaggio statoDelViaggio;

    @OneToMany
    @JoinColumn(name = "id_viaggio")
    List<Prenotazione> prenotazioneList;
}
