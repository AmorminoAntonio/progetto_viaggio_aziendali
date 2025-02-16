package com.example.progettoSettimanaleSpringWebData.services;

import com.example.progettoSettimanaleSpringWebData.exceptions.NotFoundException;
import com.example.progettoSettimanaleSpringWebData.models.dto.PrenotazioneDTO;
import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.models.entities.Prenotazione;
import com.example.progettoSettimanaleSpringWebData.models.entities.Viaggio;
import com.example.progettoSettimanaleSpringWebData.repositories.DipendenteRepository;
import com.example.progettoSettimanaleSpringWebData.repositories.PrenotazioneRepository;
import com.example.progettoSettimanaleSpringWebData.repositories.ViaggioRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {
    @Autowired
    PrenotazioneRepository prenotazioneRepo;
    @Autowired
    DipendenteRepository dipendenteRepo;
    @Autowired
    ViaggioRepository viaggioRepo;

    public void creaPrenotazione(PrenotazioneDTO prenotazioneDTO) throws BadRequestException {
        Optional<Dipendente> dipendenteTrovato = dipendenteRepo.findById(prenotazioneDTO.getDipendente_id());
        Optional<Viaggio> viaggioTrovato = viaggioRepo.findById(prenotazioneDTO.getViaggio_id());
        if (dipendenteTrovato.isPresent() && viaggioTrovato.isPresent()) {
            Dipendente dipendente = dipendenteTrovato.get();
            Viaggio viaggio = viaggioTrovato.get();
            LocalDate dataPrenotazione = prenotazioneDTO.getData();

            List<Prenotazione> prenotazioniEsistentiConLaStessaData = prenotazioneRepo.findByDataAndDipendente(dataPrenotazione, dipendente);
            List<Prenotazione> prenotazioniDelDipendente = prenotazioneRepo.findByDipendente(dipendente);

            if (!prenotazioniEsistentiConLaStessaData.isEmpty()) {
                throw new BadRequestException("Il dipendente selezionato ha gia una prenotazione per questa data");
            } else if (!prenotazioniDelDipendente.isEmpty()) {
                throw new BadRequestException("Questo dipendente non può prenotare altri viaggi, perchè ha già prenotato un viaggio!");
            } else {
                Prenotazione prenotazione = new Prenotazione();
                prenotazione.setDipendente(dipendente);
                prenotazione.setViaggio(viaggio);
                prenotazione.setData(prenotazioneDTO.getData());
                prenotazione.setNotePreferenze(prenotazioneDTO.getNotePreferenze());
                prenotazioneRepo.save(prenotazione);
            }
        } else {
            throw new NotFoundException(" L'id del viaggio o del dipendente non è stato trovato");
        }
    }


    public Page<PrenotazioneDTO> getAllPrenotazioni(Pageable page) {
        Page<Prenotazione> listaPrenotazioni = prenotazioneRepo.findAll(page);
        List<PrenotazioneDTO> listaDto = new ArrayList<>();

        for (Prenotazione prenotazione : listaPrenotazioni.getContent()) {
            PrenotazioneDTO dto = entity_dto(prenotazione);
            listaDto.add(dto);
        }
        return new PageImpl<>(listaDto);
    }

    public String deletePrenotazione(long id) {
        Optional<Prenotazione> prenotazioneTrovato = prenotazioneRepo.findById(id);
        if (prenotazioneTrovato.isPresent()) {
            prenotazioneRepo.delete(prenotazioneTrovato.get());
            return "Prenotazione con id: " + id + " eliminato con successo!";
        } else {
            throw new NotFoundException("Errore nel delete! Nessuna prenotazione trovata con id: " + id);
        }

    }

    //Entity --> DTO
    public PrenotazioneDTO entity_dto(Prenotazione prenotazione) {
        PrenotazioneDTO prenotazioneDTO = new PrenotazioneDTO();
        prenotazioneDTO.setDipendente_id(prenotazione.getDipendente().getId());
        prenotazioneDTO.setViaggio_id(prenotazione.getViaggio().getId());
        prenotazioneDTO.setData(prenotazione.getData());
        prenotazioneDTO.setNotePreferenze(prenotazione.getNotePreferenze());
        return prenotazioneDTO;
    }
}
