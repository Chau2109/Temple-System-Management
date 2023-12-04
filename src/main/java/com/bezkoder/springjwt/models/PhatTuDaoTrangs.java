package com.bezkoder.springjwt.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "PhatTuDaoTrangs")
public class PhatTuDaoTrangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phatTuDaoTrang;
    @Column(name = "daThamGia")
    private boolean daThamGia;
    @Column(name = "lyDoKhongThamGia")
    private String lyDoKhongThamGia;
    @Column(name = "daoTrangId")
    private int daoTrangId;
    @Column(name = "id")
    private int id;
    @ManyToOne()
    @JoinColumn(name = "id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

    @ManyToOne()
    @JoinColumn(name = "daoTrangId", insertable = false, updatable = false)
    @JsonBackReference
    private DaoTrangs daoTrangs;

    public int getPhatTuDaoTrang() {
        return phatTuDaoTrang;
    }

    public void setPhatTuDaoTrang(int phatTuDaoTrang) {
        this.phatTuDaoTrang = phatTuDaoTrang;
    }

    public boolean isDaThamGia() {
        return daThamGia;
    }

    public void setDaThamGia(boolean daThamGia) {
        this.daThamGia = daThamGia;
    }

    public String getLyDoKhongThamGia() {
        return lyDoKhongThamGia;
    }

    public void setLyDoKhongThamGia(String lyDoKhongThamGia) {
        this.lyDoKhongThamGia = lyDoKhongThamGia;
    }

    public int getDaoTrangId() {
        return daoTrangId;
    }

    public void setDaoTrangId(int daoTrangId) {
        this.daoTrangId = daoTrangId;
    }

    public int getId() {
        return id;
    }

    public void setId(int phatTuId) {
        this.id = phatTuId;
    }
}
