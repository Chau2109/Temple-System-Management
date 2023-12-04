package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.DaoTrangs;
import com.bezkoder.springjwt.models.Response;
import org.springframework.data.domain.Page;

import java.net.DatagramPacket;
import java.util.List;

public interface InterfaceDaoTrang {
    public List<DaoTrangs> findAll();
    public Response<DaoTrangs> addNewDaoTrang(DaoTrangs daoTrangs);
    public DaoTrangs updateDaoTrang(DaoTrangs daoTrang, int daoTrangId);
    public Response<DaoTrangs> deleteDaoTrang(int id);
    public Page<DaoTrangs> daoTrangSortingPaging(Integer pageNumber, Integer pageSize, String sortProperty);
}
