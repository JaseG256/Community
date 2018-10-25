package com.Msa.communityservlet.service;

import com.Msa.communityservlet.model.Role;
import com.Msa.communityservlet.model.RoleName;

import java.util.Optional;

public interface RoleService extends CRUDService<Role> {

    Optional<Role> findByName(RoleName roleName);
}
