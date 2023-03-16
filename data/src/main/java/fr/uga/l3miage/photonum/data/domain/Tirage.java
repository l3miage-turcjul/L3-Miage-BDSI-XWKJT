package fr.uga.l3miage.photonum.data.domain;

import java.util.List;

import jakarta.persistence.ManyToMany;

public class Tirage extends Impression{

    @ManyToMany
    private List<Photo> photosDeTirage;

    Tirage(Long id, Format format){
        super(id, format);
    }
    
    public void setPhotosDeTirage(List<Photo> photosDeTirage) {
        this.photosDeTirage = photosDeTirage;
    }
    
    public List<Photo> getPhotosDeTirage() {
        return photosDeTirage;
    }
    

}