package fr.uga.l3miage.photonum.data.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
@DiscriminatorValue("Cadre")
public class Cadre extends Impression{
    @Column(nullable=false)
    private String miseEnPage;

    @NonNull
    @ManyToMany
    private List<Photo> photosDeCadre;

    Cadre(long id, Format format,String miseEnPage){
        super(id, format);
        this.miseEnPage = miseEnPage;
		this.photosDeCadre = new ArrayList<Photo>();
    }

	public String getMiseEnPage() {
		return miseEnPage;
	}

	public void setMiseEnPage(String miseEnPage) {
		this.miseEnPage = miseEnPage;
	}

	public List<Photo> getPhotosDeCadre() {
		return photosDeCadre;
	}

	public void setPhotosDeCadre(List<Photo> photosDeCadre) {
		this.photosDeCadre = photosDeCadre;
	}
}