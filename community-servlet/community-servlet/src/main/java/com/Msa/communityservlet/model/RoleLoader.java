package com.Msa.communityservlet.model;

import com.Msa.communityservlet.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleLoader implements ApplicationRunner {

    @Autowired
    @Qualifier("roleServicer")
    private RoleService roleService;

    @Override
    public void run(ApplicationArguments args) {
        Role role = new Role(RoleName.ROLE_USER);
        roleService.saveOrUpdate(role);

        role = new Role(RoleName.ROLE_ADMIN);
        roleService.saveOrUpdate(role);
    }
}
