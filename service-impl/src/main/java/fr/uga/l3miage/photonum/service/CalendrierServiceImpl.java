package fr.uga.l3miage.photonum.service;



import fr.uga.l3miage.photonum.data.domain.Calendrier;
//import fr.uga.l3miage.photonum.service.CalendrierService;
import fr.uga.l3miage.photonum.data.repo.CalendrierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;



@Transactional
@Service
public class CalendrierServiceImpl implements CalendrierService {

    private final CalendrierRepository calendrierRepository;
   

    @Autowired
    public CalendrierServiceImpl(CalendrierRepository calendrierRepository) {
        this.calendrierRepository = calendrierRepository;
    }



    @Override
    public Calendrier save(Calendrier calendrier) {
        return calendrierRepository.save(calendrier);
    }

    @Override
    public Calendrier get(Long id) throws EntityNotFoundException {
        return calendrierRepository.get(id);
    }


    @Override
    public Collection<Calendrier> list() {
        return calendrierRepository.all();
    }

    
    public Calendrier getCalendarById(Long id) throws EntityNotFoundException{
        Calendrier calendrier = get(id);
        if(calendrier == null){
            throw new EntityNotFoundException("le calendrier d'id=%d est introuvable".formatted(id));
        }
        return calendrier;
    }

    @Override
    public Calendrier update(Calendrier calendrier) {
        return calendrierRepository.save(calendrier);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException{
        Calendrier calendrier = get(id);
        if (calendrier == null) {
            throw new EntityNotFoundException("le calendrier d'id=%d est introuvable".formatted(id));
        }

        calendrierRepository.delete(calendrier);
    }



    



}
