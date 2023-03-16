package fr.uga.l3miage.photonum.data.domain;


import jakarta.persistence.*;


@Entity
public abstract class Impression {

    @Id
    @GeneratedValue
    private Long id; // remplacer car String si besoincolu

    @Column
    private float prix;

    @Column
    private Format format;


    Impression(Long id, Format format){
        setId(id);
        setFormat(format);
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }

    public void setFormat(Format format){
        this.format = format;
    }


    //getters
    public Long getId(){
        return this.id;
    }

    public Format getFormat(){
        return this.format;
    }

    //Todo : à implémenter 
    public float getPrix(){
        return 0; 
    }
}
