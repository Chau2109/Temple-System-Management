package com.bezkoder.springjwt.payload.request;

import java.time.LocalDate;
import java.util.Set;

import com.bezkoder.springjwt.models.ERole;
import jakarta.validation.constraints.*;

public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
  private boolean daHoanTuc;
  private int gioiTinh;
  private String lastname;
  private LocalDate ngayCapNhap;
  private LocalDate ngayHoanTuc;
  private LocalDate ngaySinh;
  private LocalDate ngayXuatGia;
  private String phapDanh;
  private String soDienThoai;
  private String firstname;
  private int chuaId;
  private int kieuThanhVienId;
  private boolean isActive;
  private Set<String>roles;

  public Set<String> getRoles() {
    return roles;
  }

  public void setRoles(Set<String> roles) {
    this.roles = roles;
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
}
