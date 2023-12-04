package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
//      @Query(nativeQuery = true, value = "SELECT * FROM users p WHERE p.firstname = :firstname")
  public List<User> findByFirstname( String firstname);
//      @Query(nativeQuery = true, value = "SELECT * FROM users p WHERE p.email = :email ")
  public User findByEmail(String email);
//      @Query(nativeQuery = true, value = "SELECT * FROM users p WHERE p.phapDanh = :phapDanh")
  public User findByPhapDanh(String phapDanh);
//      @Query(nativeQuery = true, value = "SELECT * FROM users p WHERE p.gioiTinh = :gioiTinh")
  public List<User>findByGioiTinh(int gioiTinh);
//      @Query(nativeQuery = true, value = "SELECT * FROM users p WHERE p.isActive = :isActive")
  public List<User> findByIsActive(boolean isActive);
//      @Query(nativeQuery = true, value = "SELECT * FROM users p WHERE p.username =  :username")
  public Optional<User> findByUsername(String username);
  public Boolean existsByUsername(String username);

  public Boolean existsByEmail(String email);

  public User findByResetPasswordToken(String token);


}
