package it.ecommerce.ecommerce.repository;


import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.entity.Utente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdiniRepository extends CrudRepository<Ordine, Long> {

    //Ordine findOrdineById_ordine (int idOrdine);
    List<Ordine> findAll ();
    List<Ordine> findByData(Date data);
    List<Ordine> findByDataBetween(Date date1, Date date2);
    @Query("select o.id_ordine from Ordine o where o.id_ordine =?1")
    Ordine findOrdineById_ordine(long idOrdine);



    @Query(value = "select o from Ordine o where o.utente.email = ?1" )
    List<Ordine> findOrdineByUtente(String email);

    @Query("select o.listaArticoli from Ordine o where o.utente.email = ?1")
    List<Articoli> findByListaArticoliByutente (String email);

    @Query(value = "select o,o.listaArticoli from Ordine o where o.utente.email = ?1" )
    List<Object> findOrdineByUtentee(String email);





}
