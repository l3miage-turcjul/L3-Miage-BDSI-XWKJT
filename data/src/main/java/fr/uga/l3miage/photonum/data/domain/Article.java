package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Article {
    
    @Column
    private float prix;

    @Column
    private Format format;

    @Column
    private int quantite;

    public Article(float prix, Format format, int quantite){
        calculPrix();
        setFormat(format);
        this.quantite = quantite;
    }
    
    public void setFormat(Format format){
        calculPrix();
        this.format = format;
    }

    public Format getFormat(){
        return this.format;
    }

    //Todo : à implémenter 
    public float getPrix(){
        return this.prix; 
    }

    public int getQuantite(){
        return this.quantite;
    }

    public void setQuantite(int quantite){
        this.quantite = quantite;
    }

    public void calculPrix(){
        //TODO : à implémenter
    }
}
