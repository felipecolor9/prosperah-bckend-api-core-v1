package br.com.prosperah.api.appcore.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CadastralUser {

    private UUID id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private LocalDateTime creationDate;
}