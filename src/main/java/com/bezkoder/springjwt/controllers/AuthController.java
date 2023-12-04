package com.bezkoder.springjwt.controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.bezkoder.springjwt.config.NguoiDungDetail;
import com.bezkoder.springjwt.jwt.JwtUtils;
import com.bezkoder.springjwt.Exception.TokenRefreshException;
import com.bezkoder.springjwt.models.RefreshToken;
import com.bezkoder.springjwt.payload.request.TokenRefreshRequest;

import com.bezkoder.springjwt.payload.response.TokenRefreshResponse;
import com.bezkoder.springjwt.service.RefreshTokenService;
import com.bezkoder.springjwt.service.RoleService;
import com.bezkoder.springjwt.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

//    @Autowired
//    JwtUtils jwtUtils;

    @GetMapping("/hh")
    public ResponseEntity<?> tesst() {
        return ResponseEntity.ok("ihi");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> logIn(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("Buoc 1");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        System.out.println("123");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        NguoiDungDetail userDetails = (NguoiDungDetail) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        System.out.println("hello");
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());
        ResponseEntity.ok(new MessageResponse("User signed in successfully!"));
        return ResponseEntity.ok(new JwtResponse
                (jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));


    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.isDaHoanTuc(),
                signUpRequest.getGioiTinh(),
                signUpRequest.getLastname(),
                signUpRequest.getNgayCapNhap(),
                signUpRequest.getNgayHoanTuc(),
                signUpRequest.getNgaySinh(),
                signUpRequest.getNgayXuatGia(),
                signUpRequest.getPhapDanh(),
                signUpRequest.getSoDienThoai(),
                signUpRequest.getFirstname(),
                signUpRequest.getChuaId(),
                signUpRequest.getKieuThanhVienId(),
                signUpRequest.isActive(),
                signUpRequest.getRoles());
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleService.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                if(role.equals("admin")){
                    Role adminRole = roleService.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        System.out.println("admin");
                        roles.add(adminRole);

                }else if (role.equals("moderator")) {
                    Role modRole = roleService.findByName(ERole.MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        System.out.println("mod");

                }else if(role.equals("user")){
                    Role userRole = roleService.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        System.out.println("user");
                }
//                switch (role) {
//                    case "admin":
//                        Role adminRole = roleService.findByName(ERole.ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        System.out.println("admin");
//                        roles.add(adminRole);
//                    case "moderator":
//                        Role modRole = roleService.findByName(ERole.MODERATOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(modRole);
//                        System.out.println("mod");
//                    case "user":
//                        Role userRole = roleService.findByName(ERole.USER)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                        System.out.println("user");
//                }
            });
        }
        System.out.println("hellooo");
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
