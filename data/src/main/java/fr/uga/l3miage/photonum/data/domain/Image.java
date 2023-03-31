package fr.uga.l3miage.photonum.data.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;


@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable=false)
    private String cheminAcces;

    @Column
    private String infoPDV;

    @Column
    private float resolution;

    @Column
    private Client proprietaire;

    @Column(nullable=false)
    private boolean partage = false;

    @Nullable
    @OneToMany
    private Set<Photo> photos;

    public Image(String cheminAcces, String infoPDV, float resolution, boolean partage, Set<Photo> photos){
        this.cheminAcces = cheminAcces;
        this.infoPDV = infoPDV;
        this.resolution = resolution;
        this.partage = partage;
        this.photos = photos;
    }

    public Image() {

    }

    public String getCheminAcces() {
        return this.cheminAcces;
    }

    public void setCheminAcces(String cheminAcces){
        this.cheminAcces = cheminAcces;
    }

    public String getInfoPDV() {
        return this.infoPDV;
    }

    public void setInfoPDV(String infoPDV) {
        this.infoPDV = infoPDV;
    }

    public float getResolution() {
        return this.resolution;
    }

    public void setResolution(float resolution){
        this.resolution = resolution;
    }

    public boolean getPartage(){
        return this.partage;
    }

    public void setPartage(boolean partage){
        this.partage = partage;
    }

    public Set<Photo> getPhotos(){
        return this.photos;
    }

    public void setPhotos(Set<Photo> photos){
        this.photos=photos;
    }

    public void addPhoto(Photo photo) {
        if (this.photos == null) {
            this.photos = new HashSet<>();
        }
        this.photos.add(photo);
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setProprietaire(Client client){
        this.proprietaire = client;
    }

    public Client getProprietaire(){
        return proprietaire;
    }
}