package it.ecommerce.ecommerce.repository;

import it.ecommerce.ecommerce.entity.DettaglioOrdine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DettaglioOrdineRepository extends CrudRepository<DettaglioOrdine,Integer> {


}
