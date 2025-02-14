package com.example.progettoSettimanaleSpringWebData.models.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@NoArgsConstructor
@Data
public class DipendenteDTO {
    @NotNull(message = "Il nome è un campo obbligatorio")
    @NotBlank(message = "Il nome risulta vuoto")
    @Size(min = 3, message = "nome troppo corto")
    @Size(max = 25, message = "nome  troppo lungo")
    private String nome;

    @NotNull(message = "Il cognome è un campo obbligatorio")
    @NotBlank(message = "Il cognome risulta vuoto")
    @Size(min = 3, message = "Cognome troppo corto")
    @Size(max = 25, message = "Cognome  troppo lungo")
    private String cognome;

    @NotNull(message = "'username' è un campo obbligatorio")
    @NotBlank(message = "il campo 'username' risulta vuoto")
    @Size(min = 7, message = "username troppo corto")
    @Size(max = 15, message = "username  troppo lungo")
    private String username;

    @Email(message = "L'indirizzo e-mail non è valido")
    private String email;

    @URL(protocol = "http")
    private String avatarDipendente;
}
