package com.example.progettoSettimanaleSpringWebData.repositories;

import com.example.progettoSettimanaleSpringWebData.models.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
    Optional<Viaggio> findByDestinazione(String destinazione);
}
