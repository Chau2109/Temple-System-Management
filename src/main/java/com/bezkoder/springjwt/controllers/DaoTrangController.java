package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.Exception.UserNotFoundException;
import com.bezkoder.springjwt.models.DaoTrangs;
import com.bezkoder.springjwt.models.Response;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.DaoTrangRepo;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.service.DaoTrangService;
import com.bezkoder.springjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/daotrang")
public class DaoTrangController {
    @Autowired
    private DaoTrangRepo daoTrangRepo;
    @Autowired
    private DaoTrangService daoTrangService;
    @GetMapping("/findall")
    public List<DaoTrangs> findAll(){
        return daoTrangService.findAll();
    }

    @PostMapping("/adddaotrang")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response<DaoTrangs> addNewDaoTrang(@RequestBody DaoTrangs daoTrangs) {
        return daoTrangService.addNewDaoTrang(daoTrangs);
    }

    @PutMapping("/updatedaotrang")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public DaoTrangs updateDaoTrang(@RequestBody DaoTrangs newDaoTrang, @PathVariable int daoTrangId ){
        return daoTrangService.updateDaoTrang(newDaoTrang, daoTrangId);
    }

    @PutMapping("/deletedaotrang/{daoTrangId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Response<DaoTrangs> deleteDaoTrang(@PathVariable int daoTrangId) {
        return daoTrangService.deleteDaoTrang(daoTrangId);
    }

    @GetMapping("/daoTrangPagingSorting/{pageNumber}/{pageSize}/{sortProperty}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<DaoTrangs> daoTrangSortingPaging(@PathVariable Integer pageNumber,
                                                 @PathVariable Integer pageSize,
                                                 @PathVariable String sortProperty) {
        return daoTrangService.daoTrangSortingPaging(pageNumber, pageSize, sortProperty);
    }
    @GetMapping("/findById/{daoTrangId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public DaoTrangs findById(@PathVariable int daoTrangId){
        return daoTrangRepo.findById(daoTrangId).orElseThrow(() -> new UserNotFoundException(daoTrangId));
    }
}
