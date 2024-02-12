package com.myblog1.myblog1.controller;

import com.myblog1.myblog1.PayLoad.LoginDto;
import com.myblog1.myblog1.PayLoad.SignUpDto;
import com.myblog1.myblog1.entity.Role;
import com.myblog1.myblog1.repository.RoleRepository;
import com.myblog1.myblog1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myblog1.myblog1.entity.User;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository ur;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository rp;
    @Autowired
    private AuthenticationManager am;

    @PostMapping("/signin")
    public ResponseEntity<?> registerUser(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword());
        Authentication authenticate = am.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    return new ResponseEntity<>("user signed in successfully!",HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){
        if(ur.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        if(ur.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken", HttpStatus.BAD_REQUEST);
        }

        User user=new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role roles=rp.findByName(signUpDto.getRoleType()).get();
        Set<Role> convertRoleToSet=new HashSet<>();
        convertRoleToSet.add(roles);
        user.setRoles( convertRoleToSet);

        ur.save(user);
        return new ResponseEntity<>("user register successfully", HttpStatus.OK);

    }
}
