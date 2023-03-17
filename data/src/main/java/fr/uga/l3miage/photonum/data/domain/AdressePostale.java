package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AdressePostale{

    //ATTRIBUTS
    @Column(nullable=false)
    private String adresse;

    @Column(nullable=false)
    private String codePostale;

    @Column(nullable=false)
    private String ville;

    @Column(nullable=false)
    private String pays;

    AdressePostale(String adresse, String codePostale, String ville, String pays){
        this.adresse = adresse;
        this.codePostale = codePostale;
        this.ville = ville;
        this.pays = pays;
    }


    //GETTERS
    public String getAdresse(){return adresse;}

    public String getCodePostale(){return codePostale;}

    public String getVille(){return ville;}

    public String getPays(){return pays;}



    //SETTERS
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public void setCodePostale(String codePostale){
        this.codePostale = codePostale;
    }

    public void setVille(String ville){
        this.ville = ville;
    }

    public void setPays(String pays){
        this.pays = pays;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdressePostale adressePostale = (AdressePostale) o;
        return (Objects.equals(adresse, adressePostale.adresse) && Objects.equals(codePostale, adressePostale.codePostale) && Objects.equals(ville, adressePostale.ville) && Objects.equals(pays, adressePostale.pays));
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse, codePostale, ville, pays);
    }
}

