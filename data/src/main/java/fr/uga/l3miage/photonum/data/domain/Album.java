package fr.uga.l3miage.photonum.data.domain;

import org.springframework.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.*;
import java.util.*;

public class Album extends Impression{
    @Column
    private String titre;

    @NonNull
    @OneToMany
    private Set<Page> pagesAlbum;

    @NonNull
    @ManyToOne
    private Photo photoCouverture;

    Album(long id, Format format,String titre){
        super(id, format);
        this.titre = titre;
    }

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Set<Page> getPagesAlbum() {
		return pagesAlbum;
	}

	public void setPagesAlbum(Set<Page> pagesAlbum) {
		this.pagesAlbum = pagesAlbum;
	}

	public Photo getPhotoCouverture() {
		return photoCouverture;
	}

	public void setPhotoCouverture(Photo photoCouverture) {
		this.photoCouverture = photoCouverture;
	}
}