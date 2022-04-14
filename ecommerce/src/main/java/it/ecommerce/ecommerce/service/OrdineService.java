package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.entity.Utente;
import it.ecommerce.ecommerce.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface OrdineService {

    //public Ordine findOrdineById_ordine (int idOrdine);
     List<Ordine> findAll ();
     List<Ordine> findByData(Date data);
     List<Ordine> findByDataBetween(Date date1, Date date2);
     void deleteOrdine (Ordine o);
     void inserisciOrdine (Ordine o);
     List<Ordine> findOrdineByUtente(String utente) throws NotFoundException;
     List<Articoli> findByListaArticoliByutente (String utente) throws NotFoundException;
     Ordine findOrdineById_ordine (long idOrdine);
     List<Object> findOrdineByUtentee (String email);

}
