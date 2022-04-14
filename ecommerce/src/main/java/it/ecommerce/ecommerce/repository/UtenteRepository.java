package it.ecommerce.ecommerce.repository;

import it.ecommerce.ecommerce.entity.Ordine;
import it.ecommerce.ecommerce.entity.Utente;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.From;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UtenteRepository extends CrudRepository<Utente,String> {

   // @Query(value = "SELECT * FROM Utente WHERE email LIKE :email", nativeQuery = true)
    Utente findDistinctByEmailLike( String email);
    List<Utente> findUtenteByNomeAndCognome(String nome,String cognome);
    List<Utente> findUtenteByEta (int eta);
    List<Utente> findUtenteByEtaGreaterThan(int eta);
    List<Utente> findUtenteByEtaLessThan(int eta);
    List<Utente> findUtenteByCitta (String citta);
    boolean existsByEmail(String email);

    @Query("select o from Ordine o where o.utente.email =?1")
    List<Ordine> findOrdineByUtente (String email);

    List<Utente> findAll ();




}//UtenteRepository
