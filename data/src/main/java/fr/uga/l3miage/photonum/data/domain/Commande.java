package fr.uga.l3miage.photonum.data.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Commande {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private Date dateCommande;

    @Column
    private int prixTotal;

    @OneToMany
    private Set<Article> articles;

    @ManyToOne
    private AdressePostale commandeLivreeA;

    public Commande(Date dateCommande, int prixTotal, Set<Article> articles, AdressePostale adresseLivraison) {
        setDateCommande(dateCommande);
        setPrixTotal(prixTotal);
        setArticles(articles);
        setAdresseLivraison(adresseLivraison);
    }

    // GETTERS
    public Date getDateCommande() {
        return this.dateCommande;
    }

    public int getPrixtotal() {
        return this.prixTotal;
    }

    public Set<Article> getArticles() {
        return this.articles;
    }

    // SETTERS
    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public AdressePostale getAdresseLivraison() {
        return commandeLivreeA;
    }

    public void setAdresseLivraison(AdressePostale adresseLivraison) {
        this.commandeLivreeA = adresseLivraison;
    }

    public void addArticle(Article article) {
        if (articles == null) {
            this.articles = new HashSet<>();
        }
        this.articles.add(article);
    }

    public Long getId() {
        return id;
    }
}
