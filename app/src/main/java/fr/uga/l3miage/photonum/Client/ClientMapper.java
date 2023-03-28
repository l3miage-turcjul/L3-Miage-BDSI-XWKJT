package fr.uga.l3miage.photonum.Client;

import java.util.Collection;

import org.mapstruct.Mapper;

import ch.qos.logback.core.net.server.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO entityToDTO (Client client);
    Collection<ClientDTO> entityToDTO(Iterable<Client> client);

    Client dtoToEntity(ClientDTO client);
    Collection<Client> dtoToEntity(Iterable<ClientDTO> clients);
}
