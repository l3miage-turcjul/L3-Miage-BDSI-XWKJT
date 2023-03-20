package fr.uga.l3miage.photonum.data.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("Tirage")
public class Tirage extends Impression{

    @ManyToMany
    private List<Photo> photosDeTirage;

    public Tirage(Long id){
        super(id);
        this.photosDeTirage = new ArrayList<Photo>();
    }
    
    public void setPhotosDeTirage(List<Photo> photosDeTirage) {
        this.photosDeTirage = photosDeTirage;
    }
    
    public List<Photo> getPhotosDeTirage() {
        return photosDeTirage;
    }
    

}