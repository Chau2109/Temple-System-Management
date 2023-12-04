package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.DonDangKys;
import com.bezkoder.springjwt.service.DonDangKyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/daotrang/dondangky")
public class DonDangKyController {
    @Autowired
    DonDangKyService donDangKyService;

    @PostMapping("/adddondangky")
    public ResponseEntity<?> addDonDangKy(@RequestParam int daoTrangId){
        return donDangKyService.addDonDangKy(daoTrangId);
    }
    @PutMapping("/deletedondangky")
    public ResponseEntity<?> deleteDonDangKy(@RequestParam int id) {
        return donDangKyService.deleteDonDangKy(id);
    }

    @PutMapping("/updatedaotrang")
    public ResponseEntity<?> updateDonDangKy(@RequestBody DonDangKys newDonDangKy){
        return donDangKyService.updateDonDangKy(newDonDangKy);
    }
    @GetMapping("/donDangKySortingPaging/{pageNumber}/{pageSize}/{sortProperty}")
    public Page<DonDangKys> donDangKySortingPaging(@PathVariable Integer pageNumber,
                                                   @PathVariable Integer pageSize,
                                                   @PathVariable String sortProperty){
        return donDangKyService.donDangKySortingPaging(pageNumber, pageSize, sortProperty);
    }
    @PutMapping("/duyetdon")
    public ResponseEntity<?> duyetDon(@RequestParam int id){
        return donDangKyService.duyetDon(id);

    }

    }


