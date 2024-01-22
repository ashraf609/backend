package com.dev.springboot.service.impl;


import com.dev.springboot.dto.SingupRequest;
import com.dev.springboot.model.Admin;
import com.dev.springboot.repo.AdminRepository;
import com.dev.springboot.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createAdmin(SingupRequest singupRequest) {

        if(adminRepository.existsByCin(singupRequest.getCin()))
        {
            return false;
        }

        Admin admin = new Admin();

        BeanUtils.copyProperties(singupRequest , admin);
        String HashPassword = passwordEncoder.encode(singupRequest.getPassword());

        admin.setPassword(HashPassword);

        adminRepository.save(admin);
        return true;
    }
}
