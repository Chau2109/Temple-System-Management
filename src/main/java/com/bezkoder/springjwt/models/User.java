package com.bezkoder.springjwt.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", 
    uniqueConstraints = { 
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email") 
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "userId")
  private int id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;
  @Column(name = "daHoanTuc")
  private boolean daHoanTuc;
  @Column(name = "gioiTinh")
  private int gioiTinh;
  @Column(name = "lastname")
  private String lastname;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "ngayCapNhap")
  private LocalDate ngayCapNhap;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "ngayHoanTuc")
  private LocalDate ngayHoanTuc;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "ngaySinh")
  private LocalDate ngaySinh;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "ngayXuatGia")
  private LocalDate ngayXuatGia;
  @Column(name = "phapDanh")
  private String phapDanh;
  @Column(name = "soDienThoai")
  private String soDienThoai;
  @Column(name = "firstname")
  private String firstname;
  @Column(name = "chuaId")
  private int chuaId;
  @Column(name = "kieuThanhVienId")
  private int kieuThanhVienId;
  @Column(name = "isActive")
  private boolean isActive;
  @Column(name = "reset_password_token")
  private String resetPasswordToken;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonManagedReference
  private List<PhatTuDaoTrangs> phatTuDaoTrangsList;
  //1 phatTu co Many Token
  @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
  private RefreshToken token;
  //1 PhatTu co nhieu donDangKys
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  @JsonManagedReference
  private List<DonDangKys> donDangKysList;
  //1 kieuThanhVien co nhieu phatTu
  @ManyToOne()
  @JoinColumn(name = "kieuThanhVienId", insertable = false, updatable = false)
  @JsonBackReference
  private KieuThanhViens kieuThanhViens;
  //1 chua co nhieu phatTu
  @ManyToOne()
  @JoinColumn(name = "chuaId", insertable = false, updatable = false)
  @JsonBackReference
  private Chuas chuas;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable( name = "user_roles",
        joinColumns = @JoinColumn(name = "userId"),
        inverseJoinColumns = @JoinColumn(name = "roleId"))
  private Set<Role> roles = new HashSet<>();


  public User() {
  }
//
//  public User(String username, String email, String password) {
//    this.username = username;
//    this.email = email;
//    this.password = password;
//  }

  public User(String username, String email, String password, boolean daHoanTuc, int gioiTinh, String lastname, LocalDate ngayCapNhap, LocalDate ngayHoanTuc, LocalDate ngaySinh, LocalDate ngayXuatGia, String phapDanh, String soDienThoai, String firstname, int chuaId, int kieuThanhVienId, boolean isActive,Set<String> roles) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.daHoanTuc = daHoanTuc;
    this.gioiTinh = gioiTinh;
    this.lastname = lastname;
    this.ngayCapNhap = ngayCapNhap;
    this.ngayHoanTuc = ngayHoanTuc;
    this.ngaySinh = ngaySinh;
    this.ngayXuatGia = ngayXuatGia;
    this.phapDanh = phapDanh;
    this.soDienThoai = soDienThoai;
    this.firstname = firstname;
    this.chuaId = chuaId;
    this.kieuThanhVienId = kieuThanhVienId;
    this.isActive = isActive;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public boolean isDaHoanTuc() {
    return daHoanTuc;
  }

  public void setDaHoanTuc(boolean daHoanTuc) {
    this.daHoanTuc = daHoanTuc;
  }

  public int getGioiTinh() {
    return gioiTinh;
  }

  public void setGioiTinh(int gioiTinh) {
    this.gioiTinh = gioiTinh;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public LocalDate getNgayCapNhap() {
    return ngayCapNhap;
  }

  public void setNgayCapNhap(LocalDate ngayCapNhap) {
    this.ngayCapNhap = ngayCapNhap;
  }

  public LocalDate getNgayHoanTuc() {
    return ngayHoanTuc;
  }

  public void setNgayHoanTuc(LocalDate ngayHoanTuc) {
    this.ngayHoanTuc = ngayHoanTuc;
  }

  public LocalDate getNgaySinh() {
    return ngaySinh;
  }

  public void setNgaySinh(LocalDate ngaySinh) {
    this.ngaySinh = ngaySinh;
  }

  public LocalDate getNgayXuatGia() {
    return ngayXuatGia;
  }

  public void setNgayXuatGia(LocalDate ngayXuatGia) {
    this.ngayXuatGia = ngayXuatGia;
  }

  public String getPhapDanh() {
    return phapDanh;
  }

  public void setPhapDanh(String phapDanh) {
    this.phapDanh = phapDanh;
  }

  public String getSoDienThoai() {
    return soDienThoai;
  }

  public void setSoDienThoai(String soDienThoai) {
    this.soDienThoai = soDienThoai;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public int getChuaId() {
    return chuaId;
  }

  public void setChuaId(int chuaId) {
    this.chuaId = chuaId;
  }

  public int getKieuThanhVienId() {
    return kieuThanhVienId;
  }

  public void setKieuThanhVienId(int kieuThanhVienId) {
    this.kieuThanhVienId = kieuThanhVienId;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  public List<PhatTuDaoTrangs> getPhatTuDaoTrangsList() {
    return phatTuDaoTrangsList;
  }

  public void setPhatTuDaoTrangsList(List<PhatTuDaoTrangs> phatTuDaoTrangsList) {
    this.phatTuDaoTrangsList = phatTuDaoTrangsList;
  }

  public RefreshToken getToken() {
    return token;
  }

  public void setToken(RefreshToken token) {
    this.token = token;
  }

  public List<DonDangKys> getDonDangKysList() {
    return donDangKysList;
  }

  public void setDonDangKysList(List<DonDangKys> donDangKysList) {
    this.donDangKysList = donDangKysList;
  }

  public KieuThanhViens getKieuThanhViens() {
    return kieuThanhViens;
  }

  public void setKieuThanhViens(KieuThanhViens kieuThanhViens) {
    this.kieuThanhViens = kieuThanhViens;
  }

  public Chuas getChuas() {
    return chuas;
  }

  public void setChuas(Chuas chuas) {
    this.chuas = chuas;
  }

  public String getResetPasswordToken() {
    return resetPasswordToken;
  }

  public void setResetPasswordToken(String resetPasswordToken) {
    this.resetPasswordToken = resetPasswordToken;
  }
}
