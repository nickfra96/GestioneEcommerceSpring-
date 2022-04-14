package it.ecommerce.ecommerce.repository;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, String> {

    Categoria findCategoriaByCategoria (String categoria);
    List<Categoria> findAll ();
    @Query("select c.articoli from Categoria c where c.categoria =?1")
    List<Articoli> findByArticoli (String categoria);





}//CategoriaRepository
