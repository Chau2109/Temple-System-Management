package com.bezkoder.springjwt.service;
import com.bezkoder.springjwt.models.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
public interface InterfaceUser {
    public List<User> findByFirstname( String firstname);
    public Page<User> phatTuSortingAndPaging(Integer pageNumber, Integer pageSize, String sortProperty);
    public User findByEmail(String email);
    public User findByPhapDanh(String phapDanh);
    public List<User>findByGioiTinh(int gioiTinh);
    public List<User>findByIsActive(boolean isActive);

    public Optional<User> findByUsername(String username);
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
