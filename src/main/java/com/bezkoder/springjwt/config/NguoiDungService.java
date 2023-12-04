package com.bezkoder.springjwt.config;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class NguoiDungService implements UserDetailsService {
    @Autowired
    private UserRepository nguoiDungRepo;
    public NguoiDungService(UserRepository nguoiDungRepo) {
        this.nguoiDungRepo = nguoiDungRepo;
    }


    @Override
    @Transactional
    public NguoiDungDetail loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = nguoiDungRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
//        return user.map(NguoiDungDetail::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
        return NguoiDungDetail.build(user);
    }
//    public String addUser(User userInfo) {
//        userInfo.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
//        nguoiDungRepo.save(userInfo);
//        return "Thêm user thành công!";
//    }
}