package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.DettaglioArticolo;

import java.util.List;

public interface DettaglioArticoloService {

    DettaglioArticolo findByArticoli(Articoli a);
    List<DettaglioArticolo> findByCpu (String cpu);
    List<DettaglioArticolo> findByDisplay (String display);
    List<DettaglioArticolo> findByHd (String hd);
    List<DettaglioArticolo> findByRam (String ram);
    List<DettaglioArticolo> findBySo (String sistemaOperativo);
    public void inserisciDettaglioArticolo (DettaglioArticolo dettaglioArticolo);
    public void deleteDettaglioArticolo (DettaglioArticolo dettaglioArticolo);
}
