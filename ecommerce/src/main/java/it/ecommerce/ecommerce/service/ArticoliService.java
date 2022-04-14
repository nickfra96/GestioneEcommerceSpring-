package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Categoria;
import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.exception.NotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticoliService {

     Iterable<Articoli> selTutti();
     List<Articoli> selByDescrizione(String descrizione);
     List<Articoli> selByDescrizioneLike(String descrizione, Pageable pageable);
     Articoli selById(long id);
     void delArticolo(Articoli articolo);
     void insArticolo(Articoli articolo);
     List<Articoli> findArticoliByPrezzo(long prezzo) throws NotFoundException;
     List<Articoli> findArticoliByPrezzoBefore (long prezzo) throws NotFoundException;
     List<Articoli> findArticoliByPrezzoAfter (long prezzo) throws NotFoundException;
     List<Articoli> findArticoliByPrezzoBetween (long prezzo1,long prezzo2);
     List<Articoli> findByCategoria (Categoria categoria);
    // void update(int idart);


    // void update(long id, long quantita);
}//ArticoliService
