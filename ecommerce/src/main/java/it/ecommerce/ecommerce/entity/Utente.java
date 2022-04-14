package it.ecommerce.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "utente")
@Data
public class Utente implements Serializable {


    @Id
    @Column(name = "email")
    @NotNull (message = "{NotNull.Utente.email.Validation}")
    //Java email validation by RFC 5322. L'annotazione @Email fa questo
    @Email(message = "{Email.Utente.email.Validation}", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Column(name = "nome")
    @NotNull (message = "{NotNull.Utente.nome.Validation}")
    @Size(min = 3, max = 30, message = "{Size.Utente.nome.Validation}")
    @Pattern(regexp = "^[\\p{L}\\s.’\\-,]+$",message = "{Pattern.Utente.nome.Validation}")
    private  String nome;

    @Column(name = "cognome")
    @NotNull (message = "{NotNull.Utente.cognome.Validation}")
    @Size(min = 3, max = 30, message = "{Size.Utente.cognome.Validation}")
    @Pattern(regexp = "^[\\p{L}\\s.’\\-,]+$",message = "{Pattern.Utente.cognome.Validation}")
    private String cognome;

    @Column(name = "indirizzo")
    @NotNull (message = "{NotNull.Utente.indirizzo.Validation}")
    @Size(min = 10, max = 40, message = "{Size.Utente.indirizzo.Validation}")
    private String indirizzo;

    @Column(name = "citta")
    @NotNull (message = "{NotNull.Utente.citta.Validation}")
    @Size(min = 5, max = 20, message = "{Size.Utente.citta.Validation}")
    @Pattern(regexp = "^[\\p{L}\\s.’\\-,]+$",message = "{Pattern.Utente.citta.Validation}")
    private String citta;

    @Column(name = "eta")
    @Min(value = (int) 12, message = "{Min.Utente.eta.Validation}")
    @Positive (message = "{Positive.Utente.eta.Validation}") //Posso ometterla.. inglobata in @Min
    @NotNull (message = "{NotNull.Utente.eta.Validation}")
    @Pattern (regexp = "[0-9]+", message = "{Pattern.Utente.eta.Validation}",flags = Pattern.Flag.UNICODE_CASE)
    private String eta;

    @Column(name = "image")
    private String image;


    @Column(name = "password")
    @NotNull (message = "{NotNull.Utente.password.Validation}")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Version
    @Column(name = "version")
    private Integer version;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "utente",orphanRemoval = true)

    private Set<Ordine> ordini = new HashSet<>();

    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "utente")

    @JsonIgnore
    private Set<Role> ruoli;




}//Utente
