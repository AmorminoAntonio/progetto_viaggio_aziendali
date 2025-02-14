package com.example.progettoSettimanaleSpringWebData.controllers;

import com.example.progettoSettimanaleSpringWebData.models.dto.DipendenteDTO;
import com.example.progettoSettimanaleSpringWebData.models.entities.Dipendente;
import com.example.progettoSettimanaleSpringWebData.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    DipendenteService dipendenteService;

    // 1. - POST http://localhost:8080/dipendenti (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Dipendente saveDipendente(@RequestBody DipendenteDTO body) throws Exception {
        return dipendenteService.save(body);
    }

    // 2. - GET http://localhost:8080/dipendenti
    @GetMapping("")
    public Page<Dipendente> getDipendenti(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return dipendenteService.getDipendenti(page, size, sortBy);
    }

    // 3. - GET http://localhost:8080/dipendenti/{id}
    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable long id){
        return dipendenteService.findById(id);
    }

    // 4. - PUT http://localhost:8080/dipendenti/{id} (+ req.body)
    @PutMapping("/{id}")
    public Dipendente findAndUpdate(@PathVariable long id, @RequestBody Dipendente body){
        return dipendenteService.findByIdAndUpdate(id, body);
    }

    // 5. - DELETE http://localhost:8080/dipendenti/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable long id) {
        dipendenteService.findByIdAndDelete(id);
    }
}
