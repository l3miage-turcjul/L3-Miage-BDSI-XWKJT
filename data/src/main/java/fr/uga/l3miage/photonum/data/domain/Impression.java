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

    Impression(Long id){
        setId(id);
    }

    //setters
    public void setId(Long id){
        this.id = id;
    }

    //getters
    public Long getId(){
        return this.id;
    }

}
