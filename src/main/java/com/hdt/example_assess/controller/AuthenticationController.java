package com.hdt.example_assess.controller;

import com.hdt.example_assess.entity.Role;
import com.hdt.example_assess.entity.User;
import com.hdt.example_assess.jwt.JwtTokenProvider;
import com.hdt.example_assess.response.LoginRequest;
import com.hdt.example_assess.response.LoginResponse;
import com.hdt.example_assess.response.SuccessfulResponse;
import com.hdt.example_assess.service.RoleService;
import com.hdt.example_assess.service.UserCustomerService;
import com.hdt.example_assess.utils.common.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    UserCustomerService userCustomerService;
    @Autowired
    RoleService roleService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(value = "/thuc")
    public String getStr() {
        return "thuc";
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực từ username và password.
        System.out.println(loginRequest.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Trả về jwt cho người dùng.
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @GetMapping(value = "/test")
    public String test() {
        return "test ok";
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@Valid @RequestBody LoginRequest loginRequest) {
//        // Admin account
//        if (userCustomerService.findByUserName("admin") == null) {
//            User admin = new User();
//            admin.setUserName("admin");
//            admin.setPassword(passwordEncoder.encode("thuc123"));
//            List<Role> roles = new ArrayList<>();
//            roles.add(roleService.findByName("ROLE_ADMIN"));
//            roles.add(roleService.findByName("ROLE_USER"));
//            admin.setRoles(roles);
//            userCustomerService.save(admin);
//        }
//
//        // Member account
//        if (userCustomerService.findByUserName("user") == null) {
//            User user = new User();
//            user.setUserName("user");
//            user.setPassword(passwordEncoder.encode("thuc123"));
//            List<Role> roles = new ArrayList<>();
//            roles.add(roleService.findByName("ROLE_USER"));
//            user.setRoles(roles);
//            userCustomerService.save(user);
//        }
        // Member account
        User user = null;
        if (userCustomerService.findByUserName(loginRequest.getUsername()) == null) {
            user = new User();
            user.setUserName(loginRequest.getUsername());
            log.info(loginRequest.getPassword());
            user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findByName("ROLE_USER"));
            user.setRoles(roles);
            userCustomerService.save(user);
        }
        return new ResponseEntity(new SuccessfulResponse("success", "Register Ok"), HttpStatus.OK);
    }

    @GetMapping(value = "/create-role")
    public ResponseEntity createRole() {
        // Roles
        if (roleService.findByName("ROLE_ADMIN") == null) {
            roleService.save(new Role("ROLE_ADMIN"));
        }

        if (roleService.findByName("ROLE_USER") == null) {
            roleService.save(new Role("ROLE_USER"));
        }
        return new ResponseEntity(new SuccessfulResponse("success", "OK"), HttpStatus.OK);
    }


    @GetMapping(value = "/privatead")
    public String getPrivate() {
        return "Accept user take data from privatead";
    }

    @GetMapping(value = "/privateuser")
    public String getPrivateuser() {
        return "Accept user take data from privateuser";
    }

    @GetMapping(value = "/publicAuthen")
    public String getpublicAuthen() {
        return "Accept user take data from publicAuthen";
    }
}
