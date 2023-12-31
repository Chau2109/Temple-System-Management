package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Chuas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChuaRepo extends JpaRepository<Chuas, Integer> {
    public List<Chuas> findAll();

}
