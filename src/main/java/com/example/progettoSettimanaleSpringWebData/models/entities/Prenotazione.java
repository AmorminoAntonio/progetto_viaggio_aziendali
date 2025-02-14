package com.example.progettoSettimanaleSpringWebData.models.entities;

import com.example.progettoSettimanaleSpringWebData.enumerated.PreferenzaDiVolo;
import com.example.progettoSettimanaleSpringWebData.enumerated.TipoAlloggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Prenotazioni")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenotazione")
    private long id;

    @Column(nullable = false)
    private LocalDate dataPrenotazione;
    private PreferenzaDiVolo preferenzaDiVolo;
    private TipoAlloggio tipoAlloggio;
}
