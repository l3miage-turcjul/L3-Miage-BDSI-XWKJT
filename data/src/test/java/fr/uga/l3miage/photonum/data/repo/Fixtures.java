package fr.uga.l3miage.photonum.data.repo;

import com.github.javafaker.Faker;

import fr.uga.l3miage.photonum.data.domain.AdressePostale;
import fr.uga.l3miage.photonum.data.domain.Album;
import fr.uga.l3miage.photonum.data.domain.Article;
import fr.uga.l3miage.photonum.data.domain.Cadre;
import fr.uga.l3miage.photonum.data.domain.Calendrier;
import fr.uga.l3miage.photonum.data.domain.Client;
import fr.uga.l3miage.photonum.data.domain.Commande;
import fr.uga.l3miage.photonum.data.domain.Format;
import fr.uga.l3miage.photonum.data.domain.Image;
import fr.uga.l3miage.photonum.data.domain.Impression;
import fr.uga.l3miage.photonum.data.domain.Page;
import fr.uga.l3miage.photonum.data.domain.Photo;
import fr.uga.l3miage.photonum.data.domain.Tirage;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.Random;

import javax.xml.crypto.Data;

public class Fixtures {

    private static final Faker FAKER = Faker.instance(new Random(42));

    public static Client newClient() {
        // Initialisation
        Client client = new Client(null, null, null, null, null);
        AdressePostale adressePostale = new AdressePostale(FAKER.address().streetAddress(), FAKER.address().zipCode(),
                FAKER.address().city(), FAKER.address().country());

        // Instanciation
        // Supposons que Id a 5 chiffres
        client.setId(FAKER.number().numberBetween(10000L, 99999L));
        client.setNom(FAKER.name().lastName());
        client.addAdresse(adressePostale);
        client.setEmail(FAKER.internet().emailAddress());
        client.setMotDePasse(FAKER.internet().password());
        return client;
    }

    public static AdressePostale newAdressePostale() {
        AdressePostale adressePostale = new AdressePostale(null, null, null, null);
        adressePostale.setAdresse(FAKER.address().streetAddress());
        adressePostale.setAdresse(FAKER.address().zipCode());
        adressePostale.setAdresse(FAKER.address().cityName());
        adressePostale.setAdresse(FAKER.address().country());
        return adressePostale;
    }

    public static Image newImage() {
        Image image = new Image(null, null, 0, false, null);
        int nbWords = FAKER.number().numberBetween(1, 5); // nombre de mots aléatoire entre 1 et 5
        String infoPDV = FAKER.lorem().words(nbWords).toString();
        double width = FAKER.number().randomDouble(2, 1000, 10000);
        double height = FAKER.number().randomDouble(2, 1000, 10000);
        float resolution = (float) ((width * height) / 1000000.0);

        image.setCheminAcces(FAKER.file().fileName());
        image.setInfoPDV(infoPDV);
        image.setResolution(resolution);
        image.setPartage(FAKER.bool().bool());
        return image;
    }

    public static Photo newPhoto() {
        Photo photo = new Photo(null, null, null);
        int nbWords = FAKER.number().numberBetween(1, 5); // nombre de mots aléatoire entre 1 et 5
        String description = FAKER.lorem().words(nbWords).toString();
        int retouche = FAKER.number().numberBetween(-5, 5);

        photo.setParametresRetouche(Integer.toString(retouche));
        photo.setDescription(description);
        return photo;
    }

    public static Page newPage() {
        Page page = new Page(null, null);
        String miseEnPage = String.join("\n\n", FAKER.lorem().paragraphs(FAKER.number().numberBetween(1, 3)));

        page.setMiseEnPage(miseEnPage);
        page.setNumeroPage(FAKER.number().digits(3));
        return page;
    }

    public static Tirage newTirage() {
        Tirage tirage = new Tirage(null);

        tirage.setId(FAKER.number().numberBetween(10000L, 99999L));
        return tirage;
    }

    public static Album newAlbum() {
        Album album = new Album(0, null);
        album.setId(FAKER.number().numberBetween(10000L, 99999L));
        album.setTitre(FAKER.lorem().words(1).toString());
        return album;
    }

    public static Calendrier newCalendrier() {
        Calendrier calendrier = new Calendrier(null);
        calendrier.setId(FAKER.number().numberBetween(10000L, 99999L));
        return calendrier;
    }

    public static Cadre newCadre() {
        Cadre cadre = new Cadre(0, null);
        cadre.setId(FAKER.number().numberBetween(10000L, 99999L));
        String miseEnPage = String.join("\n\n", FAKER.lorem().paragraphs(FAKER.number().numberBetween(1, 3)));
        cadre.setMiseEnPage(miseEnPage);
        return cadre;
    }
    public static Format randomFormat() {
        return Format.values()[FAKER.number().numberBetween(0, Format.values().length)];
    }
    
    public static Article newArticle(){
        Article article = new Article();
        Format randomFormat = randomFormat();
        article.setFormat(randomFormat);
        article.setQuantite(FAKER.number().numberBetween(1, 100));
        return article; 
    }

    public static Commande newCommande(){
        Commande commande = new Commande();
        commande.setDateCommande((Date) FAKER.date().birthday());
        commande.setAdressePostale(newAdressePostale());
        commande.setPrixTotal(FAKER.number().randomDigitNotZero());
        return commande;
    }
}
