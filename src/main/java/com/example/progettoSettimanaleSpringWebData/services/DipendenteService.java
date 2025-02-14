package com.example.progettoSettimanaleSpringWebData.services;


import com.example.progettoSettimanaleSpringWebData.exceptions.NotFoundException;
import com.example.progettoSettimanaleSpringWebData.models.dto.DipendenteDTO;
import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Dipendente> getDipendenti(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return dipendenteRepository.findAll(pageable);
    }


    public Dipendente findById(long id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendente findByIdAndUpdate(long id, Dipendente bodyDipendente) {
        Dipendente foundDipendente = this.findById(id);
        foundDipendente.setEmail(bodyDipendente.getEmail());
        foundDipendente.setName(bodyDipendente.getName());
        foundDipendente.setSurname(bodyDipendente.getSurname());
        foundDipendente.setUsername(bodyDipendente.getUsername());
        return dipendenteRepository.save(foundDipendente);
    }

    public void findByIdAndDelete(long id) {
        Dipendente found = this.findById(id);
        dipendenteRepository.delete(found);
    }

    public Dipendente save(DipendenteDTO dipendenteDTO) {
        Dipendente nuovoDipendente = dto_entity(dipendenteDTO);
        return dipendenteRepository.save(nuovoDipendente);
    }
}
