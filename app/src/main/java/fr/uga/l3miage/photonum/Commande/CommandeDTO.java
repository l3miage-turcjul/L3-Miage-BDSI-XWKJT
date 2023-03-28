package fr.uga.l3miage.photonum.Commande;

import java.sql.Date;
import java.util.Collection;

import fr.uga.l3miage.photonum.AdressePostale.AdressePostaleDTO;
import fr.uga.l3miage.photonum.Article.ArticleDTO;
import jakarta.validation.constraints.Null;

public record CommandeDTO (
    Long id,
    Date dateCommande,
    int prixTotal,

    @Null
    Collection<ArticleDTO> articles,

    @Null
    AdressePostaleDTO commandeLivreeA
)
{}
