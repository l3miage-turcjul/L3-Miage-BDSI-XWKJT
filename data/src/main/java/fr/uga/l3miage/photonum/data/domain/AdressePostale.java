package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class AdressePostale{

    @Id
    @GeneratedValue
    private Long id;
    //ATTRIBUTS
    @Column(nullable=false)
    private String adresse;

    @Column(nullable=false)
    private String codePostal;

    @Column(nullable=false)
    private String ville;

    @Column(nullable=false)
    private String pays;

    public AdressePostale(String adresse, String codePostal, String ville, String pays){
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public AdressePostale() {

    }


    //GETTERS
    public String getAdresse(){return adresse;}

    public String getCodePostale(){return codePostal;}

    public String getVille(){return ville;}

    public String getPays(){return pays;}



    //SETTERS
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public void setCodePostale(String codePostal){
        this.codePostal = codePostal;
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
        return (Objects.equals(adresse, adressePostale.adresse) && Objects.equals(codePostal, adressePostale.codePostal) && Objects.equals(ville, adressePostale.ville) && Objects.equals(pays, adressePostale.pays));
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse, codePostal, ville, pays);
    }
}

