package com.securityecommerce.controllers;

import com.securityecommerce.models.ERole;
import com.securityecommerce.models.Role;
import com.securityecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Vérifiez d'abord si les rôles existent déjà
        if (roleRepository.count() == 0) {
            // Si les rôles n'existent pas, créez-les et enregistrez-les dans la base de données

            roleRepository.save(new Role(ERole.ROLE_CUSOTMER));
            roleRepository.save(new Role(ERole.ROLE_PROVIDER));
        }
    }
}
