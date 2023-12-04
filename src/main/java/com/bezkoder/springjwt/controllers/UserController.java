package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.Exception.UserNotFoundException;
import com.bezkoder.springjwt.models.Response;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/users")

public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @GetMapping("/findbyfirstname")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> findByFirstname(@RequestParam String firstname) {
        Pageable paging = PageRequest.of(0, 10);
        return userService.findByFirstname(firstname);
    }
    @GetMapping("/findbyemail")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/findbygioitinh")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> findByGioiTinh(@RequestParam int gioiTinh) {
        return userService.findByGioiTinh(gioiTinh);
    }

    @GetMapping("/findbyphapdanh")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public User findByPhapDanh(@RequestParam String phapDanh) {
        return userService.findByPhapDanh(phapDanh);
    }

    @GetMapping("/findbyisactive")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> findByIsActive(@RequestParam boolean isActive) {
        return userService.findByIsActive(isActive);
    }

    @GetMapping(value = "/pagingAndSortingPhatTu/{pageNumber}/{pageSize}/{sortProperty}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Page<User> phatTuPage(@PathVariable Integer pageNumber,
                                   @PathVariable Integer pageSize,
                                   @PathVariable String sortProperty){
        return userService.phatTuSortingAndPaging(pageNumber, pageSize, sortProperty);
    }
    @PostMapping("/adduser")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Response<User> addNewPhatTu(@RequestBody User user){
        return userService.addNewPhatTu(user);
    }

    @PutMapping("/deleteuser/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Response<User> deletePhatTu(@PathVariable int userId){
        return userService.deletePhatTu(userId);
    }
    @PutMapping("/updateuser/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User updatePhatTu(@RequestBody User newUser, @PathVariable int userId ){
        return userService.updatePhatTu(newUser, userId);
    }

    @GetMapping("/findbyusername")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Optional<User> findByUserName(@RequestParam String username){
        return userService.findByUsername(username);
    }

    @GetMapping("/checkexistsbyusername")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean existsByUserName(@RequestParam String username){
        return userService.existsByUsername(username);
    }

    @GetMapping("/checkexistsbyemail")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean existsByEmail(@RequestParam String email){
        return userService.existsByEmail(email);
    }

    @GetMapping("/findById/{userId}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public User findById(@PathVariable int userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}

