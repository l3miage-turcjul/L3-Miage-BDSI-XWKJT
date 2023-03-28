package fr.uga.l3miage.photonum.Image;

import java.util.Collection;

import fr.uga.l3miage.photonum.Page.PageDTO;
import jakarta.validation.constraints.Null;

public record ImageDTO (
    @Null
    Collection<PageDTO> pagesCalendrier
)
{}
