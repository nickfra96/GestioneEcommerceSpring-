package it.ecommerce.ecommerce.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "dettaglioordine")
@Data
public class DettaglioOrdine implements Serializable {

    private static final long serialVersionUID = -8127664240379848525L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_dettaglio_ordine")
    private long dettaglioOrdine;

   private int setQnt;
   private String nome;
   private Date data;



}//DetaglioOrdine
