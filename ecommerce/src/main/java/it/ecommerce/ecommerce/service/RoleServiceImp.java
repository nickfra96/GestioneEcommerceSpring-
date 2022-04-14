package it.ecommerce.ecommerce.service;

import it.ecommerce.ecommerce.entity.Role;
import it.ecommerce.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService {

    @Autowired
    RoleRepository roleRepository;


    @Override
    @Transactional
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    @Transactional
    public void addRole(Role role) {

        roleRepository.save(role);

    }
}
