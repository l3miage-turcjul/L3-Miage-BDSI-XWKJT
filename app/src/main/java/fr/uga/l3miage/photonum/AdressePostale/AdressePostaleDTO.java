package fr.uga.l3miage.photonum.AdressePostale;

import java.util.Collection;

import fr.uga.l3miage.photonum.Client.ClientDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record AdressePostaleDTO(
    Long id,

    @NotBlank
    String adresse,

    @NotBlank
    String codePostal,

    @NotBlank
    String ville,

    @NotBlank
    String pays,

    @Null
    Collection<ClientDTO> clients
)
{}
