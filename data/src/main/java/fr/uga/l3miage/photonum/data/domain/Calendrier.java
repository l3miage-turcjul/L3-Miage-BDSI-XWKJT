package fr.uga.l3miage.photonum.data.domain;

import jakarta.persistence.OneToMany;
import org.springframework.lang.NonNull;
import java.util.*;

public class Calendrier extends Impression {

    @NonNull //12 mois ?
    @OneToMany
    private Set<Page> pageCalendrier;

    Calendrier(Long id, Format format) {
        super(id, format);
    }

    public Set<Page> getPageCalendrier() {
        return pageCalendrier;
    }

    public void setPageCalendrier(Set<Page> pageCalendrier) {
        this.pageCalendrier = pageCalendrier;
    }

    public void addPage(Page page) {
        if(this.pageCalendrier.size()<12){
            this.pageCalendrier.add(page);
        }
        else throw new IllegalArgumentException("Le calendrier ne peut contenir que 12 pages");
    }
}
