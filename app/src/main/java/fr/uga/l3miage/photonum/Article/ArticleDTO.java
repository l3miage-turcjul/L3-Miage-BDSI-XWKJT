package fr.uga.l3miage.photonum.Article;

import fr.uga.l3miage.photonum.impression.ImpressionDTO;

public record ArticleDTO (
    Long id,
    float prix,
    String format,
    int quantite,
    ImpressionDTO impression

)
{}
