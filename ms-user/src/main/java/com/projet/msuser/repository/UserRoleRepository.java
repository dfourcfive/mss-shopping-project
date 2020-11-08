package com.projet.msuser.repository;

import com.projet.msuser.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    public UserRole findUserRoleByRoleName(String roleName);

}
