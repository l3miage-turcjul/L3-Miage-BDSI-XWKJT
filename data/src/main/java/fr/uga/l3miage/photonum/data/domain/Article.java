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
    private int quantité;

    Article(float prix, Format format, int quantité){
        calculPrix();
        setFormat(format);
        this.quantité = quantité;
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

    public int getQuantité(){
        return this.quantité;
    }

    public void calculPrix(){
        //TODO : à implémenter
    }
}
