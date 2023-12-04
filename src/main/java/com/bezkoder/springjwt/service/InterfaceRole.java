package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;

import java.util.Optional;

public interface InterfaceRole

{
    public Optional<Role> findByName(ERole name);
}
