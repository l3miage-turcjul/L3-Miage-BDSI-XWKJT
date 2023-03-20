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
    private LinkedHashSet<String> prenoms; 
    
    @Column(nullable = false)
    private String motDePasse;

    @NonNull
    @ManyToMany
    private SortedSet<AdressePostale> adresses;

    @OneToMany
    private SortedSet<Image> images;

    @OneToMany
    private SortedSet<Impression> impressions;

    Client(String nom,String prenom[], AdressePostale adresse,String email,String motDePasse){
        setNom(nom);
        setPrenom(prenom);
        addAdresse(adresse);
        setEmail(email);
        setMotDePasse(motDePasse);
        this.images=new TreeSet<Image>();
        this.adresses=new TreeSet<AdressePostale>();
        this.impressions=new TreeSet<Impression>();
    }

    //setters
    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPrenom(String prenom[]){
        this.prenoms = new LinkedHashSet<String>();
        this.prenoms.addAll(prenoms);
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void addAdresse(AdressePostale adresse){
        this.adresses.add(adresse);
    }

    public void setMotDePasse(String motDePasse){
        this.motDePasse = motDePasse;
    }

    public void setImages(TreeSet<Image> images){
        this.images = images;
    }

    public void addImage(Image image){
        this.images.add(image);
    }

    public void setImpressions(TreeSet<Impression> impressions){
        this.impressions = impressions;
    }

    public void addImpression(Impression impression){
        this.impressions.add(impression);
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

    public AdressePostale getLastAdresse(){
        return this.adresses.iterator().next();
    }

    public AdressePostale getAdresse(int num){
        if (num < this.adresses.size()){
            return (AdressePostale) this.adresses.toArray()[num];}
        else {
            throw new IllegalArgumentException("Le numéro de l'adresse n'existe pas");
        }
    }

    public String getEmail(){
        return this.email;
    }

    public String getMotDePasse(){
        return this.motDePasse;
    }

    public Set<Image> getImages(){
        return this.images;
    }

    public Image getLastImage(){
        return this.images.iterator().next();
    }

    public Image getImage(int num){
        if (num < this.images.size()){
            for(int nb=0;nb<num;nb++){
                this.images.iterator().next();
                if(nb==num){
                    return this.images.iterator().next();
                }
            }
        }
        else {
            throw new IllegalArgumentException("Le numéro de l'image n'existe pas");
        }
        return null;
    }
    
    public Set<Impression> getImpressions(){
        return this.impressions;
    }
    
    public Impression getLastImpression(){
        return this.impressions.iterator().next();
    }



}
