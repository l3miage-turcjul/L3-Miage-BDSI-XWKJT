package fr.uga.l3miage.photonum.data.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Page {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String miseEnPage;

    @Column(nullable = false)
    private String numeroPage;

    @Nullable
    @OneToMany
    private Set<Photo> photos;

    public Page(String miseEnPage, String numeroPage) {
        this.miseEnPage = miseEnPage;
        this.numeroPage = numeroPage;
        this.photos = new HashSet<Photo>();
    }

    public Page() {

    }

    // GETTERS
    public String getMiseEnPage() {
        return miseEnPage;
    }

    public String getNumeroPage() {
        return numeroPage;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public Long getId() {
        return this.id;
    }

    // SETTERS
    public void setMiseEnPage(String miseEnPage) {
        this.miseEnPage = miseEnPage;
    }

    public void setNumeroPage(String numeroPage) {
        this.numeroPage = numeroPage;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public void addPhoto(Photo photo) {
        if (this.photos == null) {
            this.photos = new HashSet<>();
        }
        this.photos.add(photo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Page page = (Page) o;
        return (Objects.equals(miseEnPage, page.miseEnPage) && Objects.equals(numeroPage, page.numeroPage));
    }

    @Override
    public int hashCode() {
        return Objects.hash(miseEnPage, numeroPage);
    }
}
