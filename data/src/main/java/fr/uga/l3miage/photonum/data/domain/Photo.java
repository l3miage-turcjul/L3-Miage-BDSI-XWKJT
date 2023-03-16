package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

@Entity
public class Photo extends Image{

    @Column
    private String parametresRetouche;

    @Column
    private String description;


    //GETTER ET SETTER DE L'ATTRIBUT PARAMETRESRETOUCHE
    public String getParametresRetouche(){
        return this.parametresRetouche;
    }

    public void setParametresRetouche(String parametresRetouche){
        this.parametresRetouche = parametresRetouche;
    }

    //GETTER ET SETTER DE L'ATTRIBUT DESCRIPTION
    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    
}