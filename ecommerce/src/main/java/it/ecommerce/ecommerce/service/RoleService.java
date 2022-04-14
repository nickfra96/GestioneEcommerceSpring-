package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Role;
import it.ecommerce.ecommerce.entity.Utente;

public interface RoleService {

    public void deleteRole(Role role);
    public void addRole(Role role);
}
