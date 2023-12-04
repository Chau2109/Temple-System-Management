package com.bezkoder.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
//  @Query(nativeQuery = true, value = "SELECT * FROM roles r WHERE r.name = :name")
//  Optional<Role> findByRoleName(ERole name);
  Optional<Role> findByName(ERole name);
}
