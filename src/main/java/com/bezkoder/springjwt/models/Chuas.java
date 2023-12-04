package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Chuas")
public class Chuas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chuaId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "capNhap")
    private LocalDateTime capNhap;
    @Column(name = "diaChi")
    private String diaChi;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ngayThanhLap")
    private LocalDateTime ngayThanhLap;
    @Column(name = "tenChua")
    private String tenChua;
    @Column(name = "truTri")
    private int truTri;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chuas")
    @JsonManagedReference
    private List<User> userList;

    public int getChuaId() {
        return chuaId;
    }

    public void setChuaId(int chuaId) {
        this.chuaId = chuaId;
    }

    public LocalDateTime getCapNhap() {
        return capNhap;
    }

    public void setCapNhap(LocalDateTime capNhap) {
        this.capNhap = capNhap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDateTime getNgayThanhLap() {
        return ngayThanhLap;
    }

    public void setNgayThanhLap(LocalDateTime ngayThanhLap) {
        this.ngayThanhLap = ngayThanhLap;
    }

    public String getTenChua() {
        return tenChua;
    }

    public void setTenChua(String tenChua) {
        this.tenChua = tenChua;
    }

    public int getTruTri() {
        return truTri;
    }

    public void setTruTri(int truTri) {
        this.truTri = truTri;
    }
}
