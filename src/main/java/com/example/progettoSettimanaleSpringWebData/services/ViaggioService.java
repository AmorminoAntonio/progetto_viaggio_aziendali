package com.example.progettoSettimanaleSpringWebData.services;

import com.example.progettoSettimanaleSpringWebData.enumerated.StatoDelViaggio;
import com.example.progettoSettimanaleSpringWebData.models.dto.ViaggioDTO;
import com.example.progettoSettimanaleSpringWebData.exceptions.NotFoundException;
import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.models.entities.Viaggio;
import com.example.progettoSettimanaleSpringWebData.repositories.DipendenteRepository;
import com.example.progettoSettimanaleSpringWebData.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViaggioService {
    @Autowired
    ViaggioRepository viaggioRepo;
    @Autowired
    DipendenteRepository dipendenteRepo;


    public Long saveViaggio(ViaggioDTO viaggioDTO) {
        Viaggio viaggioInserito = entity_dto(viaggioDTO);
        Viaggio viaggioSalvato = viaggioRepo.save(viaggioInserito);
        return viaggioSalvato.getId();
    }

    public Page<ViaggioDTO> getAllViaggio(Pageable page) {
        Page<Viaggio> listaViaggi = viaggioRepo.findAll(page);
        List<ViaggioDTO> listaDto = new ArrayList<>();

        for (Viaggio viaggio : listaViaggi.getContent()) {
            ViaggioDTO dto = dto_entity(viaggio);
            listaDto.add(dto);
        }

        return new PageImpl<>(listaDto);
    }

    public ViaggioDTO findViaggioById(long id) {
        Optional<Viaggio> viaggio = viaggioRepo.findById(id);

        if (viaggio.isPresent()) {
            return dto_entity(viaggio.get());
        } else {
            throw new NotFoundException("Nessun viaggio trovato con l'id: " + id);
        }
    }

    public void updateViaggio(ViaggioDTO viaggioDTO, long id) {
        Optional<Viaggio> viaggioTrovato = viaggioRepo.findById(id);

        if (viaggioTrovato.isPresent()) {
            Viaggio viaggio = viaggioTrovato.get();
            viaggio.setDestinazione(viaggioDTO.getDestinazione());
            viaggio.setData(viaggioDTO.getData());
            viaggio.setStato(viaggioDTO.getStato());
            viaggioRepo.save(viaggio);
        } else {
            throw new NotFoundException("Errore nella modifica del viaggio inserito. Nessun viaggio trovato con l'id: " + id);
        }
    }

    public String deleteViaggio(long id) {
        Optional<Viaggio> viaggioTrovato = viaggioRepo.findById(id);
        if (viaggioTrovato.isPresent()) {
            viaggioRepo.delete(viaggioTrovato.get());
            return "Viaggio con id: " + id + " eliminato con successo!";
        } else {
            throw new NotFoundException("Errore nel delete! Nessun viaggio trovato con id: " + id);
        }
    }

    public void addDipendente(long viaggioId, long dipendenteId) {
        Optional<Viaggio> viaggio = viaggioRepo.findById(viaggioId);
        Optional<Dipendente> dipendente = dipendenteRepo.findById(dipendenteId);
        if (viaggio.isPresent() && dipendente.isPresent()) {
            Viaggio viaggioDaSalvare = viaggio.get();
            viaggioDaSalvare.setDipendente(dipendente.get());
            viaggioRepo.save(viaggioDaSalvare);
        } else {
            throw new NotFoundException("Viaggio o Dipendente non trovato con id: " + dipendenteId);
        }
    }

    public void modificaStatoViaggio(long viaggioId, StatoDelViaggio statoViaggio) {
        Optional<Viaggio> viaggio = viaggioRepo.findById(viaggioId);
        if (viaggio.isPresent()) {
            Viaggio viaggioDaSalvare = viaggio.get();
            viaggioDaSalvare.setStato(statoViaggio);
            viaggioRepo.save(viaggioDaSalvare);
        } else {
            throw new NotFoundException("Viaggio non trovato con id: " + viaggioId);
        }
    }

    //Entity ---> DTO
    public Viaggio entity_dto(ViaggioDTO viaggioDTO) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        viaggio.setData(viaggioDTO.getData());
        viaggio.setStato(viaggioDTO.getStato());
        viaggio.setDipendente(viaggioDTO.getDipendente());
        return viaggio;
    }

    //DTO ---> Entity
    public ViaggioDTO dto_entity(Viaggio viaggio) {
        ViaggioDTO viaggioDTO = new ViaggioDTO();
        viaggioDTO.setDestinazione(viaggio.getDestinazione());
        viaggioDTO.setData(viaggio.getData());
        viaggioDTO.setStato(viaggio.getStato());
        viaggioDTO.setDipendente(viaggio.getDipendente());
        return viaggioDTO;
    }

}