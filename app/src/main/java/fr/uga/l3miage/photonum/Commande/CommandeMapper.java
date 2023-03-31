package fr.uga.l3miage.photonum.Commande;

import java.util.Collection;

import org.mapstruct.Mapper;

import fr.uga.l3miage.photonum.Client.ClientDTO;
import fr.uga.l3miage.photonum.data.domain.Commande;
import jakarta.validation.Valid;

@Mapper(componentModel = "spring")
public interface CommandeMapper {
    CommandeDTO entityToDTO (Commande commande);
    Collection<CommandeDTO> entityToDTO(Iterable<Commande> commandes);

    Commande dtoToEntity(@Valid CommandeDTO commande);
    Collection<Commande> dtoToEntity(Iterable<CommandeDTO> commandes);
}
