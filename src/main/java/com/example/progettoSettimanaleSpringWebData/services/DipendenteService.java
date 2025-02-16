package com.example.progettoSettimanaleSpringWebData.services;


import com.example.progettoSettimanaleSpringWebData.exceptions.NotFoundException;
import com.example.progettoSettimanaleSpringWebData.models.dto.DipendenteDTO;
import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DipendenteService {
    @Autowired
    DipendenteRepository dipendenteRepo;


    public Long saveDipendente(DipendenteDTO dipendenteDTO) {
        Dipendente dipendenteInserito = dto_entity(dipendenteDTO);
        Dipendente dipendenteSalvato = dipendenteRepo.save(dipendenteInserito);
        return dipendenteSalvato.getId();
    }

    public Page<DipendenteDTO> getAllDipendenti(Pageable page) {
        Page<Dipendente> listaDipendenti = dipendenteRepo.findAll(page);
        List<DipendenteDTO> listaDto = new ArrayList<>();

        for (Dipendente dipendente : listaDipendenti.getContent()) {
            DipendenteDTO dto = entity_dto(dipendente);
            listaDto.add(dto);
        }

        return new PageImpl<>(listaDto);
    }

    public DipendenteDTO findDipendenteById(long id) {
        Optional<Dipendente> dipendente = dipendenteRepo.findById(id);

        if (dipendente.isPresent()) {
            return entity_dto(dipendente.get());
        } else {
            throw new NotFoundException("Nessun dipendente trovato con l'id: " + id);
        }
    }

    public void updateDipendente(DipendenteDTO dipendenteDTO, long id) {
        Optional<Dipendente> dipendenteTrovato = dipendenteRepo.findById(id);

        if (dipendenteTrovato.isPresent()) {
            Dipendente dipendente = dipendenteTrovato.get();
            dipendente.setUsername(dipendenteDTO.getUsername());
            dipendente.setNome(dipendenteDTO.getNome());
            dipendente.setCognome(dipendenteDTO.getCognome());
            dipendente.setEmail(dipendenteDTO.getEmail());
            dipendente.setAvatarDipendente(dipendenteDTO.getAvatarDipendente());
            dipendenteRepo.save(dipendente);
        } else {
            throw new NotFoundException("Errore nella modifica del dipendente inserito. Dipendente non trovato!");
        }
    }

    public String deleteDipendente(long id) {
        Optional<Dipendente> dipendenteTrovato = dipendenteRepo.findById(id);
        if (dipendenteTrovato.isPresent()) {
            dipendenteRepo.delete(dipendenteTrovato.get());
            return "Dipendente con id: " + id + " eliminato con successo!";
        } else {
            throw new NotFoundException("Errore nel delete! Nessun dipendente trovato con id: " + id);
        }

    }

    public Dipendente dto_entity(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setEmail(dipendenteDTO.getEmail());
        dipendente.setAvatarDipendente(dipendenteDTO.getAvatarDipendente());
        return dipendente;
    }

    public DipendenteDTO entity_dto(Dipendente dipendente) {
        DipendenteDTO dipendenteDTO = new DipendenteDTO();
        dipendenteDTO.setUsername(dipendente.getUsername());
        dipendenteDTO.setNome(dipendente.getNome());
        dipendenteDTO.setCognome(dipendente.getCognome());
        dipendenteDTO.setEmail(dipendente.getEmail());
        dipendenteDTO.setAvatarDipendente(dipendente.getAvatarDipendente());
        return dipendenteDTO;
    }
}