package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.DettaglioArticolo;
import it.ecommerce.ecommerce.repository.DettaglioArticoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DettaglioArticoloServiceImp implements DettaglioArticoloService {

    @Autowired
    DettaglioArticoloRepository dettaglioArticoloRepository;


    @Override
    public DettaglioArticolo findByArticoli(Articoli a) {
        return dettaglioArticoloRepository.findByArticoli(a);
    }

    @Override
    public List<DettaglioArticolo> findByCpu(String cpu) {
        return dettaglioArticoloRepository.findByCpu(cpu);
    }

    @Override
    public List<DettaglioArticolo> findByDisplay(String display) {
        return dettaglioArticoloRepository.findByDisplay(display);
    }

    @Override
    public List<DettaglioArticolo> findByHd(String hd) {
        return dettaglioArticoloRepository.findByHd(hd);
    }

    @Override
    public List<DettaglioArticolo> findByRam(String ram) {
        return dettaglioArticoloRepository.findByRam(ram);
    }

    @Override
    public List<DettaglioArticolo> findBySo(String sistemaOperativo) {
        return dettaglioArticoloRepository.findBySo(sistemaOperativo);
    }

    @Override
    @Transactional
    public void inserisciDettaglioArticolo(DettaglioArticolo dettaglioArticolo) {


        dettaglioArticoloRepository.save(dettaglioArticolo);

    }

    @Override
    @Transactional
    public void deleteDettaglioArticolo(DettaglioArticolo dettaglioArticolo) {
        dettaglioArticoloRepository.delete(dettaglioArticolo);

    }

}
