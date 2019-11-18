package com.hdt.example_assess.respository;

import com.hdt.example_assess.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
   Role findByName(String role);
}
