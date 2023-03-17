package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

@Entity
public class Photo{

    @Column
    private String parametresRetouche;

    @Column
    private String description;

    @ManyToOne
    private Image image; 

    public Photo(Image image, String parametresRetouche, String description){
        this.image = image;
        this.parametresRetouche = parametresRetouche;
        this.description = description;
    }


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