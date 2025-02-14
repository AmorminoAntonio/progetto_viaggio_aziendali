package com.example.progettoSettimanaleSpringWebData.repositories;

import com.example.progettoSettimanaleSpringWebData.models.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
  /*  @Query("SELECT v FROM Viaggio v WHERE v.destinazione = :destinazione")
    List<Viaggio> findTravelWithSameDestination(@Param("destinazione") String destinazione);*/
}
