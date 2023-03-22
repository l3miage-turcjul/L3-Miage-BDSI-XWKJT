package fr.uga.l3miage.photonum.data.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("Tirage")
public class Tirage extends Impression{

    @Id
    @GeneratedValue
    private Long id;
    
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
    
    public void addPhoto(Photo photo) {
        if (this.photosDeTirage == null) {
            this.photosDeTirage = new ArrayList<>();
        }
        this.photosDeTirage.add(photo);
    }

}