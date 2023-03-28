package fr.uga.l3miage.photonum.AdressePostale;

import jakarta.validation.constraints.NotBlank;

public record AdressePostaleDTO(
    Long id,

    @NotBlank
    String adresse,

    @NotBlank
    String codePostal,

    @NotBlank
    String ville,

    @NotBlank
    String pays
)
{}
