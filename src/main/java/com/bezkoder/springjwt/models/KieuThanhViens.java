package com.bezkoder.springjwt.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "KieuThanhViens")
public class KieuThanhViens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kieuThanhVienId;
    @Column(name = "code")
    private String code;
    @Column(name = "tenKieu")
    private String tenKieu;
    //1 kieuThanhVien co nhieu phatTu;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kieuThanhViens")
    @JsonManagedReference
    private List<User> userList;

    public int getKieuThanhVienId() {
        return kieuThanhVienId;
    }

    public void setKieuThanhVienId(int kieuThanhVienId) {
        this.kieuThanhVienId = kieuThanhVienId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenKieu() {
        return tenKieu;
    }

    public void setTenKieu(String tenKieu) {
        this.tenKieu = tenKieu;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userListList) {
        this.userList = userList;
    }
}
