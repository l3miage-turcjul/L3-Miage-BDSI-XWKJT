package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.Column;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("Album")
public class Album extends Impression{
    
	@Column(nullable=false)
    private String titre;

	@Nullable
    @OneToMany
    private Set<Page> pagesAlbum;

	@Nullable
    @ManyToOne
    private Photo photoCouverture;

    public Album(long id,String titre){
        super(id);
        this.titre = titre;
		this.pagesAlbum = new HashSet<Page>();
    }

	public Album() {
		super();
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

	public void addPage(Page page){
		this.pagesAlbum.add(page);
	}
}