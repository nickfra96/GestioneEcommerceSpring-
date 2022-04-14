package it.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "dettaglioarticoli")
@Data
public class DettaglioArticolo  implements Serializable {


    private static final long serialVersionUID = -8951717176911615171L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dettaglio")
    private long id_dettaglio;

    @Column(name = "cpu")
    @Size(min = 5, max = 20, message = "{Size.DettaglioArticolo.cpu.Validation}")
    private String cpu;

    @Column(name = "ram")
    @Size(min = 5, max = 30, message = "{Size.DettaglioArticolo.ram.Validation}")
    private String ram;

    @Column(name = "display")
    @Size(min = 5, max = 40, message = "{Size.DettaglioArticolo.display.Validation}")
    private String display;

    @Column(name = "hd")
    @Size(min = 5, max = 40, message = "{Size.DettaglioArticolo.hd.Validation}")
    private String hd;

    @Column(name = "scheda_video")
    @Size(min = 5, max = 40, message = "{Size.DettaglioArticolo.scheda_video.Validation}")
    private String scheda_video;

    @Column(name = "so")
    @Size(min = 5, max = 40, message = "{Size.DettaglioArticolo.so.Validation}")
    private String so;

   /* @Version
    @Column(name = "version")
    private Integer version;*/

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @JoinColumn(name = "id_articolo", referencedColumnName = "id" )
    private Articoli articoli;
}//dettaglioArticolo
