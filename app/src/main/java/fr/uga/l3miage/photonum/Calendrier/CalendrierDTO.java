package fr.uga.l3miage.photonum.Calendrier;

import java.util.Collection;

import fr.uga.l3miage.photonum.Page.PageDTO;
import jakarta.validation.constraints.NotBlank;

public record CalendrierDTO (
    @NotBlank
    Collection<PageDTO> pagesCalendrier
)
{}
