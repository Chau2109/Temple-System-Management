package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "DaoTrangs")
public class DaoTrangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int daoTrangId;
    @Column(name = "daKetThuc")
    private boolean daKetThuc;
    @Column(name = "noiDung")
    private String noiDung;
    @Column(name = "noiToChuc")
    private String noiToChuc;
    @Column(name = "soThanhVienThamGia")
    private int soThanhVienThamGia;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "thoiGianToChuc")
    private LocalDateTime thoiGianToChuc;
    @Column(name = "nguoiTruTri")
    private int nguoiTruTri;
    //1 daoTrang co nhieu phatTuDaoTrang
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "daoTrangs")
    @JsonManagedReference
    private List<PhatTuDaoTrangs> phatTuDaoTrangsList;
    //1 daoTrang co nhieu donDangKys
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "daoTrangs")
    @JsonManagedReference
    private List<DonDangKys>donDangKyslist;

    public int getDaoTrangId() {
        return daoTrangId;
    }

    public void setDaoTrangId(int daoTrangId) {
        this.daoTrangId = daoTrangId;
    }

    public boolean isDaKetThuc() {
        return daKetThuc;
    }

    public void setDaKetThuc(boolean daKetThuc) {
        this.daKetThuc = daKetThuc;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNoiToChuc() {
        return noiToChuc;
    }

    public void setNoiToChuc(String noiToChuc) {
        this.noiToChuc = noiToChuc;
    }

    public int getSoThanhVienThamGia() {
        return soThanhVienThamGia;
    }

    public void setSoThanhVienThamGia(int soThanhVienThamGia) {
        this.soThanhVienThamGia = soThanhVienThamGia;
    }

    public LocalDateTime getThoiGianToChuc() {
        return thoiGianToChuc;
    }

    public void setThoiGianToChuc(LocalDateTime thoiGianToChuc) {
        this.thoiGianToChuc = thoiGianToChuc;
    }

    public int getNguoiTruTri() {
        return nguoiTruTri;
    }

    public void setNguoiTruTri(int nguoiTruTri) {
        this.nguoiTruTri = nguoiTruTri;
    }

    public List<PhatTuDaoTrangs> getPhatTuDaoTrangsList() {
        return phatTuDaoTrangsList;
    }

    public void setPhatTuDaoTrangsList(List<PhatTuDaoTrangs> phatTuDaoTrangsList) {
        this.phatTuDaoTrangsList = phatTuDaoTrangsList;
    }

    public List<DonDangKys> getDonDangKyslist() {
        return donDangKyslist;
    }

    public void setDonDangKyslist(List<DonDangKys> donDangKyslist) {
        this.donDangKyslist = donDangKyslist;
    }
}
