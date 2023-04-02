package fr.uga.l3miage.photonum.Client;

import java.util.Collection;

import fr.uga.l3miage.photonum.AdressePostale.AdressePostaleDTO;
import fr.uga.l3miage.photonum.Commande.CommandeDTO;
import fr.uga.l3miage.photonum.Image.ImageDTO;
import fr.uga.l3miage.photonum.impression.ImpressionDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;

public record ClientDTO (
    Long id,

    @NotBlank
    String email,

    @NotBlank
    String nom,

    String prenoms,

    @NotBlank
    String motDePasse,

    @Null
    Collection<AdressePostaleDTO> adresses,

    @Null
    Collection<ImageDTO> images,

    @Null
    Collection<ImpressionDTO> impressions,

    @Null
    Collection<CommandeDTO> commandes
)
{}
