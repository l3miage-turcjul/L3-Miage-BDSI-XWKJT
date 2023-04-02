package fr.uga.l3miage.photonum.data.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

@NamedQuery(name = "toutes-les-adresses", query = "SELECT ad FROM AdressePostale ad")
@NamedQuery(name = "find-adresse-by-country", query = "SELECT ad from AdressePostale ad where ad.pays = :Pays")
@Entity
public class AdressePostale {

    @Id
    @GeneratedValue
    private Long id;
    // ATTRIBUTS
    @Column(nullable = false)
    private String adresse;

    @Column(nullable = false)
    private String codePostal;

    @Column(nullable = false)
    private String ville;

    @Column(nullable = false)
    private String pays;

    @Nullable
    @ManyToMany(mappedBy = "adresses")
    private SortedSet<Client> clients;

    public AdressePostale(String adresse, String codePostal, String ville, String pays) {
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
        this.clients = new TreeSet<Client>();
    }

    public AdressePostale() {

    }

    // GETTERS
    public String getAdresse() {
        return adresse;
    }

    public String getCodePostale() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public String getPays() {
        return pays;
    }

    public Long getId() {
        return id;
    }

    public SortedSet<Client> getClients() {
        return this.clients;
    }

    // SETTERS
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCodePostale(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AdressePostale adressePostale = (AdressePostale) o;
        return (Objects.equals(adresse, adressePostale.adresse) && Objects.equals(codePostal, adressePostale.codePostal)
                && Objects.equals(ville, adressePostale.ville) && Objects.equals(pays, adressePostale.pays));
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresse, codePostal, ville, pays);
    }
}
