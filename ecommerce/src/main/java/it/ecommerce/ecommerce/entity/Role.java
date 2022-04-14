package it.ecommerce.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Entity
@Table (name = "role")
public class Role  implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "role")
    private String role;
    @ManyToOne
    @JoinColumn(name = "email_user", referencedColumnName = "email")
    @JsonBackReference
    private Utente utente;


}
