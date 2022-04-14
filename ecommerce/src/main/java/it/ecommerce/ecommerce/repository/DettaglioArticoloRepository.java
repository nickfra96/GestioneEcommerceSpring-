package it.ecommerce.ecommerce.repository;

import it.ecommerce.ecommerce.entity.Articoli;
import it.ecommerce.ecommerce.entity.DettaglioArticolo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DettaglioArticoloRepository extends CrudRepository<DettaglioArticolo, Long> {

      DettaglioArticolo findByArticoli(Articoli a);
      List<DettaglioArticolo> findByCpu (String cpu);
      List<DettaglioArticolo> findByDisplay (String display);
      List<DettaglioArticolo> findByHd (String hd);
      List<DettaglioArticolo> findByRam (String ram);
      List<DettaglioArticolo> findBySo (String sistemaOperativo);






}//DettaglioArticoloRepository
