package com.bezkoder.springjwt.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DonDangKys")
public class DonDangKys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donDangKyId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ngayGuiDon")
    private LocalDateTime ngayGuiDon;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ngayXuLy")
    private LocalDateTime ngayXuLy;
    @Column(name = "nguoiXuLy")
    private int nguoiXuLy;
    @Column(name = "trangThaiDon")
    private boolean trangThaiDon;
    @Column(name = "daoTrangId")
    private int daoTrangId;
    @Column(name = "userId")
    private int userId;
    @ManyToOne()
    @JoinColumn(name = "daoTrangId", insertable = false, updatable = false)
    @JsonBackReference
    private DaoTrangs daoTrangs;
    //1 phatTu co nhieu donDangKys
    @ManyToOne()
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    public int getDonDangKyId() {
        return donDangKyId;
    }

    public void setDonDangKyId(int donDangKyId) {
        this.donDangKyId = donDangKyId;
    }

    public LocalDateTime getNgayGuiDon() {
        return ngayGuiDon;
    }

    public void setNgayGuiDon(LocalDateTime ngayGuiDon) {
        this.ngayGuiDon = ngayGuiDon;
    }

    public LocalDateTime getNgayXuLy() {
        return ngayXuLy;
    }

    public void setNgayXuLy(LocalDateTime ngayXuLy) {
        this.ngayXuLy = ngayXuLy;
    }

    public int getNguoiXuLy() {
        return nguoiXuLy;
    }

    public void setNguoiXuLy(int nguoiXuLy) {
        this.nguoiXuLy = nguoiXuLy;
    }

    public boolean getTrangThaiDon() {
        return trangThaiDon;
    }

    public void setTrangThaiDon(boolean trangThaiDon) {
        this.trangThaiDon = trangThaiDon;
    }

    public int getDaoTrangId() {
        return daoTrangId;
    }

    public void setDaoTrangId(int daoTrangId) {
        this.daoTrangId = daoTrangId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DaoTrangs getDaoTrangs() {
        return daoTrangs;
    }

    public void setDaoTrangs(DaoTrangs daoTrangs) {
        this.daoTrangs = daoTrangs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
