package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Categoria;
import it.ecommerce.ecommerce.exception.NotFoundException;

import java.util.List;

public interface CategoriaService {

     void inserisciCategoria(Categoria categoria);
     void deleteCategoria (Categoria categoria);
     Categoria findByCategoriaLike (String categoria);
     List<Categoria> findAll ();
     List<Articoli> findByArticoli(String categoria) throws NotFoundException;

}//CategoriaService
