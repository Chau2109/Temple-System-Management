package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.DonDangKys;
import com.bezkoder.springjwt.repository.DonDangKyRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InterfaceDonDangKy {
//    ResponseEntity<?> addDonDangKy(DonDangKys donDangKys);
    public ResponseEntity<?> addDonDangKy(int daoTrangId);
    ResponseEntity<?> deleteDonDangKy(int id);
    public ResponseEntity<?> updateDonDangKy(DonDangKys donDangKys);
    public Page<DonDangKys> donDangKySortingPaging(Integer pageNumber, Integer pageSize, String sortProperty);
    ResponseEntity<?> duyetDon(int id);



}
