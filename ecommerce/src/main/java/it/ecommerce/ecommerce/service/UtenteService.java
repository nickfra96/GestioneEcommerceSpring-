package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.entity.Utente;

import java.util.List;

public interface UtenteService {

    public Iterable<Utente> getUtenti();

    public void delUtente(Utente utente);
    public void insUtente(Utente utente);
    public Utente findByEmailLike (String email);
   List<Ordine> findOrdineByUtente (String email);
    public List<Utente>findByLastnameAndFirstname (String nome, String cognome);
    public List<Utente> findByAgeGreaterThan (int eta);
    public List<Utente> findByAgeLessThan (int eta);
    public List<Utente> findByCountryLike (String country);
    public boolean existsByEmail(String email);
    List<Utente> findAll();


}//UtenteService
