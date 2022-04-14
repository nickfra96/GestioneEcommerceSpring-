package it.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "ordini")
@Data
public class Ordine implements Serializable {

    private static final long serialVersionUID = 3964125290104699064L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordine")
    @NotNull (message = "{NotNull.Ordine.id_ordine.Validation}")
    private long id_ordine;

    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @NotNull(message = "{NotNull.Ordine.data.Validation}")
    private Date data;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "utente", referencedColumnName = "email")
    @JsonIgnore
    private Utente utente;

    @Column(name = "quantita")
    private int quantita;

    @Column(name = "prezzototale")
    private long prezzoTotale;

    @Version
    private  Integer version;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "o",orphanRemoval = true)
    @JsonManagedReference
    List<Articoli>listaArticoli = new ArrayList<>();





}//ordine
