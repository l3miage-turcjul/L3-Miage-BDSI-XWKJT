package fr.uga.l3miage.photonum.data.domain;


import jakarta.persistence.*;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeImpression")
//@DiscriminatorValue("Impression")
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
        calculPrix();
        setFormat(format);
    }

    //setters
    public void setId(Long id){
        calculPrix();
        this.id = id;
    }

    public void setFormat(Format format){
        calculPrix();
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
        return this.prix; 
    }

    public void calculPrix(){
        //TODO : à implémenter
    }
}
