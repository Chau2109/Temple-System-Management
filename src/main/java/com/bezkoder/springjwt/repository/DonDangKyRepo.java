package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.DaoTrangs;
import com.bezkoder.springjwt.models.DonDangKys;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface DonDangKyRepo extends JpaRepository<DonDangKys, Integer> {
//    @Query(nativeQuery = true, value = "SELECT * FROM dondangkys d WHERE d.userId = :userId AND d.daoTrangId = :daoTrangId")
    public List<DonDangKys> findByUserIdAndDaoTrangId(int userId, int daoTrangId);
    public List<DonDangKys> findByUserId(int userId);
    public Boolean existsByUserId(int userId);
    public Boolean existsByDaoTrangId(int daoTrangId);

}
