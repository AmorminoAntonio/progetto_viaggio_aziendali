package com.example.progettoSettimanaleSpringWebData.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "Dipendenti")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true)
    private String email;

    @OneToMany
    @JoinColumn(name = "id_dipendente")
    private List<Prenotazione> prenotazioneList;


}
