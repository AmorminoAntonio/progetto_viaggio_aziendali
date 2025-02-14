package com.example.progettoSettimanaleSpringWebData.services;

import com.example.progettoSettimanaleSpringWebData.exceptions.NotFoundException;
import com.example.progettoSettimanaleSpringWebData.models.dto.ViaggioDTO;
import com.example.progettoSettimanaleSpringWebData.models.entities.Viaggio;
import com.example.progettoSettimanaleSpringWebData.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViaggioService {
    @Autowired
    ViaggioRepository viaggioRepository;

    // DTO -> ENTITY
    public Viaggio dto_entity(ViaggioDTO viaggioDto) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDto.getDestinazione());
        viaggio.setDataViaggio(viaggioDto.getDataViaggio());
        viaggio.setStatoDelViaggio(viaggioDto.getStatoDelViaggio());
        return viaggio;
    }

    // ENTITY -> DTO
    public ViaggioDTO entity_dto(Viaggio viaggio) {
        ViaggioDTO viaggioDTO = new ViaggioDTO();
        viaggioDTO.setDestinazione(viaggio.getDestinazione());
        viaggioDTO.setDataViaggio(viaggio.getDataViaggio());
        viaggioDTO.setStatoDelViaggio(viaggio.getStatoDelViaggio());
        return viaggioDTO;
    }

    public Viaggio save(ViaggioDTO viaggioDTO) {
        Viaggio nuovoViaggio = dto_entity(viaggioDTO);
        return viaggioRepository.save(nuovoViaggio);
    }

    public Viaggio findById(long id) {
        return viaggioRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio findByIdAndUpdate(long id, Viaggio bodyViaggio) {
        Viaggio foundViaggio = this.findById(id);
        foundViaggio.setDestinazione(bodyViaggio.getDestinazione());
        foundViaggio.setDataViaggio(bodyViaggio.getDataViaggio());
        foundViaggio.setStatoDelViaggio(bodyViaggio.getStatoDelViaggio());
        return viaggioRepository.save(foundViaggio);
    }

    public void findByIdAndDelete(long id) {
        Viaggio found = this.findById(id);
        viaggioRepository.delete(found);
    }

    public Page<Viaggio> getViaggi(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return viaggioRepository.findAll(pageable);
    }

}
