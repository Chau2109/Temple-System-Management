package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Chuas;
import com.bezkoder.springjwt.models.DaoTrangs;
import com.bezkoder.springjwt.models.DonDangKys;
import com.bezkoder.springjwt.models.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InterfaceChua {
    ResponseEntity<?> addChua(Chuas chua);
    ResponseEntity<?> deleteChua(int id);
    public List<Chuas> findAll();
    public Chuas updateChua(Chuas chua,int chuaId);
    public Page<Chuas> chuaSortingPaging(Integer pageNumber, Integer pageSize, String sortProperty);
}
