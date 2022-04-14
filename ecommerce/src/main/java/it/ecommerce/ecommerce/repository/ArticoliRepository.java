package it.ecommerce.ecommerce.repository;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Categoria;
import it.ecommerce.ecommerce.entity.Ordine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.List;

@Repository
public interface ArticoliRepository   extends PagingAndSortingRepository<Articoli, Long> {



    @Query(value = "SELECT * FROM articoli WHERE descrizione LIKE :desArt", nativeQuery = true)
    List<Articoli> SelByDescrizioneLike(@Param("desArt") String descrizione);
    Articoli findArticoliById(long id);
    List<Articoli> findArticoliByNome(String nome);
    List<Articoli> findArticoliByDescrizioneLike(String descrizione, Pageable pageable);
    List<Articoli> findArticoliByPrezzo(long prezzo);
    List<Articoli> findArticoliByPrezzoBefore(long prezzo);
    List<Articoli> findArticoliByPrezzoAfter(long prezzo);
    List<Articoli> findArticoliByPrezzoBetween(long prezzo1,long prezzo2);
    List<Articoli> findByCategoria(Categoria categoria);
   /* @Modifying
    @Query("update Articoli a set a.quantita =?1 where a.id=?2")
     void updateQnt (int qnt,int cod);*/






}//ArticoliRepository
