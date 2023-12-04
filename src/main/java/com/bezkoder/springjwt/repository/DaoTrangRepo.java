package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Chuas;
import com.bezkoder.springjwt.models.DaoTrangs;
import com.bezkoder.springjwt.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoTrangRepo extends JpaRepository<DaoTrangs, Integer>{
    public List<DaoTrangs> findAll();




}
