package fr.uga.l3miage.photonum.data.domain;

import java.util.List;

import org.springframework.lang.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;

public class Cadre extends Impression{
    @Column
    private String miseEnPage;

    @NonNull
    @ManyToMany
    private List<Photo> photosDeCadre;

    Cadre(long id, Format format,String miseEnPage){
        super(id, format);
        this.miseEnPage = miseEnPage;
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