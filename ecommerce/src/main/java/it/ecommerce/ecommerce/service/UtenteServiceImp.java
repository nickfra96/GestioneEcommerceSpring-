package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.entity.Utente;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UtenteServiceImp implements UtenteService {

    @Autowired
    UtenteRepository utenteRepository;


    @Override
    public Iterable<Utente> getUtenti() {
        return utenteRepository.findAll();
    }


    @Override
    @Transactional
    public void delUtente(Utente utente) {
        utenteRepository.delete(utente);

    }

    @Override
    @Transactional
    public void insUtente(Utente utente) {

        utenteRepository.save(utente);

    }

    @Override
    public Utente findByEmailLike(String email) {
        return utenteRepository.findDistinctByEmailLike(email);
    }

    @Override
    public List<Ordine> findOrdineByUtente(String email) {
        return utenteRepository.findOrdineByUtente(email);
    }

    @Override
    public List<Utente> findByLastnameAndFirstname(String nome, String cognome) {
        return utenteRepository.findUtenteByNomeAndCognome(nome, cognome);
    }

    @Override
    public List<Utente> findByAgeGreaterThan(int eta) {
        return utenteRepository.findUtenteByEtaGreaterThan(eta);
    }

    @Override
    public List<Utente> findByAgeLessThan(int eta) {
        return utenteRepository.findUtenteByEtaLessThan(eta);
    }

    @Override
    public List<Utente> findByCountryLike(String country) {
        return utenteRepository.findUtenteByCitta(country);
    }

    @Override
    public boolean existsByEmail(String email)  {

        return utenteRepository.existsByEmail(email);
    }

    @Override
    public List<Utente> findAll() {
        return utenteRepository.findAll();
    }



}//UtenteServiceImplementation

