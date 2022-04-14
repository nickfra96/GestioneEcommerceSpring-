package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.entity.Utente;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.repository.OrdiniRepository;
import it.ecommerce.ecommerce.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdineServiceImp implements OrdineService {

    @Autowired
    OrdiniRepository ordiniRepository;
    @Autowired
    UtenteRepository utenteRepository;

   /* @Override
    public Ordine findOrdineById_ordine(int idOrdine) {

        return ordiniRepository.findOrdineById_ordine(idOrdine);
    }*/

    @Override
    public List<Ordine> findAll() {
        return ordiniRepository.findAll();
    }

    @Override
    public List<Ordine> findByData(Date data) {
        return ordiniRepository.findByData(data);
    }

    @Override
    public List<Ordine> findByDataBetween(Date date1, Date date2) {
        return ordiniRepository.findByDataBetween(date1,date2);
    }

    @Override
    @Transactional
    public void deleteOrdine(Ordine o) {
        ordiniRepository.delete(o);
    }

    @Override
    @Transactional
    public void inserisciOrdine(Ordine o) {


        ordiniRepository.save(o);

    }

    @Override
    public List<Ordine> findOrdineByUtente(String utente) throws it.ecommerce.ecommerce.exception.NotFoundException{

        if (!utenteRepository.existsByEmail(utente))
        {
            throw new NotFoundException("Utente non esiste");
        }
        return ordiniRepository.findOrdineByUtente(utente);
    }

    @Override
    public List<Articoli> findByListaArticoliByutente(String utente) throws NotFoundException {
        if (!utenteRepository.existsByEmail(utente))
        {
            throw new NotFoundException("Utente non presente in anagrafica");
        }

        return ordiniRepository.findByListaArticoliByutente(utente);
    }

    @Override
    public Ordine findOrdineById_ordine(long idOrdine) {
        return ordiniRepository.findOrdineById_ordine(idOrdine);
    }

    @Override
    public List<Object> findOrdineByUtentee(String email) {
        return ordiniRepository.findOrdineByUtentee(email);
    }
}
