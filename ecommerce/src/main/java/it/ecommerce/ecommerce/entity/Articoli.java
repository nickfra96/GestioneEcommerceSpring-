package it.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articoli")
@Data
public class Articoli implements Serializable {

    private static final long serialVersionUID = 3194336106255966338L;

    @Id
    @NotNull
    @Column(name = "id")
    private long id;

    @Column(name = "nome")
    @Size(min = 6, max = 30, message = "{Size.Articoli.nome.Validation}")
    @NotNull(message = "{NotNull.Articoli.nome.Validation}")
    private String nome;

    @Column(name = "descrizione")
    @Size(min = 15, max = 500, message = "{Size.Articoli.descrizione.Validation}")
    @NotNull (message = "{NotNull.Articoli.descrizione.Validation}")
    private String descrizione;

    @Column(name = "prezzo")
    @Min(value = (long) 0.1, message = "{Min.Articoli.prezzo.Validation}")
    @NotNull(message = "{NotNull.Articoli.prezzo.Validation}")
    @Positive(message = "{Positive.Articoli.prezzo.Validation}")
    private long prezzo;

    @Column(name = "quantità")
    @NotNull(message = "{NotNull.Articoli.quantita.Validation}")
    @Max(value = 99, message = "{Min.Articoli.quantita.Validation}")
    @PositiveOrZero(message = "{PositiveOrZero.Articoli.quantita.Validation}")
    private long quantita;

    @Column(name = "image")
    private String image;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "articoli",  orphanRemoval = true)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<DettaglioArticolo> dettaglioArticolo = new ArrayList<>();

    @ManyToOne

    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria")
    @EqualsAndHashCode.Exclude
    private Categoria categoria;

    @Version
    @Column(name = "version")
    private Integer version;

    /*
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable
            (name = "dettaglioordine",
                    joinColumns = {@JoinColumn(name = "id_ordine")},
                    inverseJoinColumns = {@JoinColumn(name = "id_articolo")})
    private List<Ordine> listaOrdini = new ArrayList<>();
*/



@ManyToOne
    @JoinColumn(name = "id_ordine", referencedColumnName = "id_ordine")
@JsonBackReference
    private Ordine o;
}
