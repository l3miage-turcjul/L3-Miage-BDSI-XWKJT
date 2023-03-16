package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.*;
import java.util.*;

import org.springframework.lang.NonNull;


@Entity
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;
    
    @Column(nullable = false)
    private String nom;

    @ElementCollection
    private SortedSet<String> prenoms; 
    
    @Column(nullable = false)
    private String motDePasse;

    @NonNull
    @ManyToMany
    private AdressePostale adresse;

    @OneToMany
    private Set<Image> images;

    Client(String nom,String prenom, AdressePostale adresse,String email,String motDePasse){
        setNom(nom);
        setPrenom(prenom);
        setAdresse(adresse);
        setEmail(email);
        setMotDePasse(motDePasse);
    }

    //setters
    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPrenom(String prenom){
        this.prenoms.add(prenom);
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAdresse(AdressePostale adresse){
        this.adresse = adresse;
    }

    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }

    public void setImages(Set<Image> images){
        this.images = images;
    }

    public void addImage(Image image){
        this.images.add(image);
    }

    //getters

    public String getNom(){
        return this.nom;
    }

    public String getFirstPrenom(){
        return this.prenoms.iterator().next();
    }

    //TODO : refaire cette fonction de manière plus propre
    public String getPrenom(int num){
        String prenom = "";
        for(int nb=0;nb<num;nb++){
            this.prenoms.iterator().next();
            if(nb==num){
                return this.prenoms.iterator().next();
            }
        }
        return prenom;
    }

    public AdressePostale getAdresse(){
        return this.adresse;
    }

    public String getEmail(){
        return this.email;
    }

    public String getMotDePasse(){
        return this.motDePasse;
    }

}
