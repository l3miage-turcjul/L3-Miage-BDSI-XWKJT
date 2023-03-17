package fr.uga.l3miage.photonum.data.domain;

public enum Format{
    _10X15MAT("10X15MAT",1),
    _10X13BRIL("10X13BRIL",1.2f),
    CALA4BEIL("CALA4BEIL",3),
    CADA3MAT("CADA3MAT",2.5f);
    String nom;
    float prix;

    Format(String nom, float prix){
        this.nom = nom;
        this.prix = prix;
    }
}
