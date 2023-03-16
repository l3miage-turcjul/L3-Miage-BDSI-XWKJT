package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Column(nullable=false)
    private Set<Client> clients;


    //GETTERS
    public String getAdresse(){return adresse;}

    public String getCodePostale(){return codePostale;}

    public String getVille(){return ville;}

    public String getPays(){return pays;}

    public Set<Client> getClients(){return clients;}


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

    public void setClients(Set<Client> clients){
        this.clients = clients;
    }

    public void addClient(Client client) {
        if (this.clients == null) {
            this.clients = new HashSet<>();
        }
        this.clients.add(client);
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

