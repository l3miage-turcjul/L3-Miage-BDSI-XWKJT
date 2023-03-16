package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

@Entity
public abstract class Image {

    @Column(nullable=false)
    private String cheminAcces;

    @Column
    private String infoPDV;

    @Column
    private float resolution;

    @Column
    private boolean partage;

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