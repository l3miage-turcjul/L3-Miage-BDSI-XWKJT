package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;


@Entity
public class Image {

    @Column(nullable=false)
    private String cheminAcces;

    @Column
    private String infoPDV;

    @Column
    private float resolution;

    @Column(nullable=false)
    private boolean partage;

    public Image(String cheminAcces, String infoPDV, float resolution, boolean partage){
        this.cheminAcces = cheminAcces;
        this.infoPDV = infoPDV;
        this.resolution = resolution;
        this.partage = partage;
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

}