package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private float prix;

    @Column
    private Format format;

    @Column
    private int quantite;

    @ManyToOne
    private Impression impression;

    public Article(float prix, Format format, int quantite, Impression impression){
        calculPrix();
        setFormat(format);
        this.quantite = quantite;
        setImpression(impression);
    }

    public Article() {

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

    public void setImpression(Impression impression){
        this.impression = impression;
    }

    public Impression getImpression(){
        return this.impression;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }
}
