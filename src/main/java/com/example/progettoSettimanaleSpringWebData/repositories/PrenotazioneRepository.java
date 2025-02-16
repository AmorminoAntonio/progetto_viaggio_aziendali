package com.example.progettoSettimanaleSpringWebData.repositories;

import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.models.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByDataAndDipendente(LocalDate data, Dipendente dipendente);

    List<Prenotazione> findByDipendente(Dipendente dipendente);
}
