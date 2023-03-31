package fr.uga.l3miage.photonum.data.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class Photo{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String parametresRetouche;

    @Column
    private String description;

    @Nullable
    @ManyToOne
    private Image image; 

    public Photo(Image image, String parametresRetouche, String description){
        this.image = image;
        this.parametresRetouche = parametresRetouche;
        this.description = description;
    }

    public Photo() {

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

    //GETTER ET SETTER DE L4ATTRIBUT ID
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    
}