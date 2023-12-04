package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.Exception.ChuaNotFoundException;
import com.bezkoder.springjwt.Exception.UserNotFoundException;
import com.bezkoder.springjwt.models.Chuas;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.ChuaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Optional;

@Service
public class ChuaService implements InterfaceChua{
    @Autowired
    private ChuaRepo chuaRepo;
    @Override
    public ResponseEntity<?> addChua(Chuas chua) {
        chuaRepo.save(chua);
        return ResponseEntity.ok("Add Chua thanh cong");
    }

    @Override
    public ResponseEntity<?> deleteChua(int id) {
        Optional<Chuas> chuasOptional = chuaRepo.findById(id);
        if(chuasOptional.isEmpty()){
            ErrorResponse errorResponse = new ErrorResponseException(HttpStatus.BAD_REQUEST, new Throwable("User khong ton tai"));;
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }else{
            chuaRepo.delete(chuasOptional.get());
            return ResponseEntity.ok("Xoa Chua thanh cong");
        }
    }

    @Override
    public List<Chuas> findAll() {
        return chuaRepo.findAll();
    }

    @Override
    public Chuas updateChua(Chuas newChua, int chuaId) {

        return chuaRepo.findById(chuaId).map(chua -> {
            chua.setCapNhap(newChua.getCapNhap());
            chua.setDiaChi(newChua.getDiaChi());
            chua.setTenChua(newChua.getTenChua());
            chua.setTruTri(newChua.getTruTri());
            return chuaRepo.save(chua);
        }).orElseThrow(() -> new ChuaNotFoundException(chuaId));
    }




    @Override
    public Page<Chuas> chuaSortingPaging(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if(null != sortProperty){
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
        }else{
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "tenChua");
        }
        return chuaRepo.findAll(pageable);
    }
}
