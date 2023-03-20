package fr.uga.l3miage.photonum.data.domain;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Commande {
    
    @Column
    private Date dateCommande;

    @Column
    private int prixTotal;

    @OneToMany
    private Set<Article> articles;

    Commande(Date dateCommande, int prixTotal, Set<Article> articles){
        setDateCommande(dateCommande);
        setPrixTotal(prixTotal);
        setArticles(articles);
    }

    //GETTERS
    public Date getDateCommande(){
        return this.dateCommande;
    }

    public int getPrixtotal(){
        return this.prixTotal;
    }

    public Set<Article> getArticles(){
        return this.articles;
    }

    //SETTERS
    public void setDateCommande(Date dateCommande){
        this.dateCommande = dateCommande;
    }

    public void setPrixTotal(int prixTotal){
        this.prixTotal = prixTotal;
    }

    public void setArticles(Set<Article> articles){
        this.articles = articles;
    }
}
