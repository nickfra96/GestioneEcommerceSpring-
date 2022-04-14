package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.Categoria;
import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.exception.NotFoundException;
import it.ecommerce.ecommerce.repository.ArticoliRepository;
import org.hibernate.dialect.lock.LockingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceImp implements ArticoliService {

    @Autowired
    ArticoliRepository articoliRepository;

    @Override
    public Iterable<Articoli> selTutti() {
        return articoliRepository.findAll();
    }

    @Override
    public List<Articoli> selByDescrizione(String descrizione) {
        return articoliRepository.SelByDescrizioneLike(descrizione);
    }

    @Override
    public List<Articoli> selByDescrizioneLike(String descrizione, Pageable pageable) {
        return articoliRepository.findArticoliByDescrizioneLike(descrizione, pageable);
    }

    @Override
    public Articoli selById(long id) {
        return articoliRepository.findArticoliById(id);
    }

    @Override
    @Transactional
    public void delArticolo(Articoli articolo) {
articoliRepository.delete(articolo);
    }

    @Override
    @Transactional
    public void insArticolo(Articoli articolo) {

        articoliRepository.save(articolo);

    }


    @Override
    public List<Articoli> findArticoliByPrezzo(long prezzo)  {

        return articoliRepository.findArticoliByPrezzo(prezzo);
    }

    @Override
    public List<Articoli> findArticoliByPrezzoBefore(long prezzo) throws NotFoundException {
        return articoliRepository.findArticoliByPrezzoBefore(prezzo);
    }

    @Override
    public List<Articoli> findArticoliByPrezzoAfter(long prezzo) throws NotFoundException {
        return articoliRepository.findArticoliByPrezzoAfter(prezzo);
    }

    @Override
    public List<Articoli> findArticoliByPrezzoBetween(long prezzo1, long prezzo2) {
        return articoliRepository.findArticoliByPrezzoBetween(prezzo1,prezzo2);
    }

    @Override
    public List<Articoli> findByCategoria(Categoria categoria) {
        return articoliRepository.findByCategoria(categoria);
    }

/*
    public void update(int qnt, int idArt) {
        articoliRepository.updateQnt(idArt,qnt);
    }*/


}
