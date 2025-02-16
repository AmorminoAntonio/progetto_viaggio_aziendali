package com.example.progettoSettimanaleSpringWebData.repositories;

import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
}

