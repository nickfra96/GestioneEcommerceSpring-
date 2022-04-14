package it.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Data
public class Categoria  implements Serializable{


    private static final long serialVersionUID = 3829571042744485453L;

    @Id
    @Column(name = "categoria")
    @Size(min = 3, max = 20, message = "{Size.Categoria.categoria.Validation}")
    @NotNull (message = "{NotNull.Categoria.categoria.Validation}")
    @Pattern(regexp = "^[\\p{L}\\s.’\\-,]+$", message = "{Pattern.Categoria.categoria.Validation}")
    private String categoria;


    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "categoria",orphanRemoval = true)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Articoli> articoli = new HashSet<>();

}//Categoria
