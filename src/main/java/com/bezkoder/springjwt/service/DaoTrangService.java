package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.Exception.UserNotFoundException;
import com.bezkoder.springjwt.models.DaoTrangs;
import com.bezkoder.springjwt.models.Response;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.DaoTrangRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DaoTrangService implements InterfaceDaoTrang {
    @Autowired
    DaoTrangRepo daoTrangRepo;
    @Override
    public Response<DaoTrangs> addNewDaoTrang(DaoTrangs daoTrangs) {
        Response<DaoTrangs> resp = new Response<>();
        daoTrangRepo.save(daoTrangs);
        resp.setStatus(1);
        return resp;
    }

    @Override
    public DaoTrangs updateDaoTrang(DaoTrangs newDaoTrang, int daoTrangId) {
        return daoTrangRepo.findById(daoTrangId).map(daoTrang -> {
            daoTrang.setNoiDung(newDaoTrang.getNoiDung());
            daoTrang.setDaKetThuc(newDaoTrang.isDaKetThuc());
            daoTrang.setNoiToChuc(newDaoTrang.getNoiToChuc());
            daoTrang.setThoiGianToChuc(newDaoTrang.getThoiGianToChuc());
            daoTrang.setNguoiTruTri(newDaoTrang.getNguoiTruTri());
            daoTrang.setSoThanhVienThamGia(newDaoTrang.getSoThanhVienThamGia());
            return daoTrangRepo.save(daoTrang);
        }).orElseThrow(() -> new UserNotFoundException(daoTrangId));
    }

    @Override
    public Response<DaoTrangs> deleteDaoTrang(int id) {
        Response<DaoTrangs> resp = new Response<>();
        Optional<DaoTrangs> daoTrangsOptional = daoTrangRepo.findById(id);
        if (daoTrangsOptional.isEmpty()){
            resp.setStatus(0);
            resp.setError("Dao trang does not exist");
        }else{
            DaoTrangs daoTrangs = daoTrangsOptional.get();
            daoTrangRepo.delete(daoTrangs);
            resp.setStatus(1);
        }
        return resp;
    }

    @Override
    public Page<DaoTrangs> daoTrangSortingPaging(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if(null != sortProperty){
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
        }else{
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "soThanhVienThamGia");
        }
        return daoTrangRepo.findAll(pageable);

    }
    public List<DaoTrangs> findAll(){
        return daoTrangRepo.findAll();
    }

}
