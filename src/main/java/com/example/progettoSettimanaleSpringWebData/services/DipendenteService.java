package com.example.progettoSettimanaleSpringWebData.services;

import com.example.progettoSettimanaleSpringWebData.models.dto.DipendenteDTO;
import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {
    @Autowired
    DipendenteRepository dipendenteRepository;

    // DTO -> ENTITY
    public Dipendente dto_entity(DipendenteDTO dipendenteDto) {
        Dipendente dipendente = new Dipendente();
        dipendente.setName(dipendenteDto.getNome());
        dipendente.setSurname(dipendenteDto.getCognome());
        dipendente.setUsername(dipendenteDto.getUsername());
        dipendente.setEmail(dipendenteDto.getEmail());
        return dipendente;
    }

    // ENTITY -> DTO
    public DipendenteDTO entity_dto(Dipendente dipendente) {
        DipendenteDTO dipendenteDTO = new DipendenteDTO();
        dipendenteDTO.setNome(dipendente.getName());
        dipendenteDTO.setCognome(dipendente.getSurname());
        dipendenteDTO.setUsername(dipendente.getUsername());
        dipendenteDTO.setEmail(dipendente.getEmail());
        return dipendenteDTO;
    }


    public Long saveDipendente(DipendenteDTO dipendenteDTO) {
        Dipendente nuovo = dto_entity(dipendenteDTO);
        return dipendenteRepository.save(nuovo).getId();
    }
}
