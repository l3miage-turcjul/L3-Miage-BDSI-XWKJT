package fr.uga.l3miage.photonum.data.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.*;

@Entity
@DiscriminatorValue("Calendrier")
public class Calendrier extends Impression {

     //12 mois ?
    @Nullable
    @OneToMany
    private Set<Page> pagesCalendrier;

    public Calendrier(Long id) {
        super(id);
        this.pagesCalendrier = new HashSet<Page>();
    }

    public Set<Page> getPagesCalendrier() {
        return pagesCalendrier;
    }

    public void setPagesCalendrier(Set<Page> pagesCalendrier) {
        this.pagesCalendrier = pagesCalendrier;
    }

    public void addPage(Page page) {
        if(this.pagesCalendrier.size()<12){
            this.pagesCalendrier.add(page);
        }
        else throw new IllegalArgumentException("Le calendrier ne peut contenir que 12 pages");
    }
}
