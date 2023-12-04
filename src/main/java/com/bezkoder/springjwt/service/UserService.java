package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.Exception.UserNotFoundException;
import com.bezkoder.springjwt.models.Response;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements InterfaceUser{
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findByFirstname(String firstname) {
        List<User> phatTuList = userRepository.findByFirstname(firstname);
        for(User p:phatTuList){
            System.out.println(p);
        }
        return phatTuList;
    }

    @Override
    public Page<User> phatTuSortingAndPaging(Integer pageNumber, Integer pageSize, String sortProperty) {
        Pageable pageable = null;
        if(null != sortProperty){
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sortProperty);
        }else{
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, "firstname");
        }
        return userRepository.findAll(pageable);
        //Page<T>findAll(Pageable pageable), findAll return Page

    }

    @Override
    public User findByEmail(String email) {
        Response<User> resp = new Response<>();
        User phatTu = userRepository.findByEmail(email);
        if (phatTu.getEmail().equals(email)){
            return phatTu;
        }else{
            resp.setStatus(0);
            return phatTu;
        }
    }

    @Override
    public User findByPhapDanh(String phapDanh) {
        Response<User> resp = new Response<>();
        User user = userRepository.findByPhapDanh(phapDanh);
        if (user.getPhapDanh().equals(phapDanh)){
            return user;
        }else{
            resp.setStatus(0);
            return user;
        }
    }

    @Override
    public List<User> findByGioiTinh(int gioiTinh) {
        List<User> phatTuList = userRepository.findByGioiTinh(gioiTinh);
        for(User p:phatTuList){
            System.out.println(p);
        }
        return phatTuList;
    }

    @Override
    public List<User> findByIsActive(boolean isActive) {
        List<User> phatTuList = userRepository.findByIsActive(isActive);
        for(User p:phatTuList){
            System.out.println(p);
        }
        return phatTuList;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Response<User> resp = new Response<>();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            resp.setStatus(0);
            resp.setError("User not exists!");
        }
        return userOptional;
    }

    @Override
    public boolean existsByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user.getEmail().equals(email)){
            return true;
        }else{
            return false;
        }
    }


    public Response<User> addNewPhatTu(User user){
        Response<User> resp = new Response<>();
        userRepository.save(user);
        user.setActive(true);
        resp.setStatus(1);
        return resp;
    }
    public Response<User> deletePhatTu(int userId){ //chi co admin
        Response<User> resp = new Response<>();
        Optional<User> phatTuOptional = userRepository.findById(userId);
        if (phatTuOptional.isEmpty()){
            resp.setStatus(0);
            resp.setError("Phat tu khong ton tai");
        }else{
            User user = phatTuOptional.get();
            user.setActive(false);
            userRepository.save(user);
        }
        return resp;

    }
    public User updatePhatTu(User newUser, int userId){  //1 vai thong tin chi phat tu, 1 vai thong tin cho admin
        return userRepository.findById(userId).map(user -> {
                    user.setChuaId(newUser.getChuaId());
                    user.setDaHoanTuc(newUser.isDaHoanTuc());
                    user.setEmail(newUser.getEmail());
                    user.setActive(newUser.isActive());
                    user.setUsername(newUser.getUsername());
                    user.setNgayCapNhap(newUser.getNgayCapNhap());
                    user.setNgayHoanTuc(newUser.getNgayHoanTuc());
                    user.setNgayXuatGia(newUser.getNgayXuatGia());
                    user.setSoDienThoai(newUser.getSoDienThoai());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(userId));

    }

    public void updateResetPasswordToken(String token, String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user != null ){
            user.setResetPasswordToken(token);
            userRepository.save(user);
        }else {
            throw new Exception("Could not find any customer with this email" + email);
        }
    }
    public User getUserByResetPasswordToken(String resetPasswordToken){ //= get
       return  userRepository.findByResetPasswordToken(resetPasswordToken);
    }
    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }


}
