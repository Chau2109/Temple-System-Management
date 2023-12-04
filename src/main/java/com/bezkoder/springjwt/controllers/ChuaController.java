package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.Exception.UserNotFoundException;
import com.bezkoder.springjwt.models.Chuas;
import com.bezkoder.springjwt.models.Response;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ChuaRepo;
import com.bezkoder.springjwt.service.ChuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/chua")
public class ChuaController {
    @Autowired
    ChuaService chuaService;

    @Autowired
    ChuaRepo chuaRepo;
    @GetMapping("/findall")
    public List<Chuas> findAll() {

        return chuaService.findAll();
    }
    @GetMapping("/chuaSortingPaging/{pageNumber}/{pageSize}/{sortProperty}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<Chuas> chuaSortingPaging(@PathVariable Integer pageNumber,
                                         @PathVariable Integer pageSize,
                                         @PathVariable String sortProperty){
        return chuaService.chuaSortingPaging(pageNumber,pageSize,sortProperty);
    }
    @PostMapping("/addchua")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> addChua(@RequestBody Chuas chua){
        return chuaService.addChua(chua);
    }

    @DeleteMapping("/deletechua/{chuaId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteChua(@PathVariable int chuaId){
        return chuaService.deleteChua(chuaId);
    }
    @PutMapping("/updatechua/{chuaId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Chuas updateChua(@RequestBody Chuas newChua, @PathVariable int chuaId ){
        return chuaService.updateChua(newChua, chuaId);
    }
    @GetMapping("/findById/{chuaId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Chuas findById(@PathVariable int chuaId){
        return chuaRepo.findById(chuaId).orElseThrow(() -> new UserNotFoundException(chuaId));
    }


}
