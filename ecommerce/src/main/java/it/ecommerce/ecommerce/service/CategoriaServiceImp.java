package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Categoria;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoriaServiceImp implements CategoriaService{

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public void inserisciCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);

    }

    @Override
    @Transactional
    public void deleteCategoria(Categoria categoria) {
        categoriaRepository.delete(categoria);

    }

    @Override
    public Categoria findByCategoriaLike(String categoria) {
        return categoriaRepository.findCategoriaByCategoria(categoria);
    }

    @Override
    public List<Categoria> findAll (){
        return categoriaRepository.findAll();
    }

    @Override
    public List<Articoli> findByArticoli(String categoria) throws NotFoundException {

        Categoria c = categoriaRepository.findCategoriaByCategoria(categoria);
        if (c != null) {
            return categoriaRepository.findByArticoli(categoria);
        }
        throw  new NotFoundException("La categoria  non esiste");
    }


}
