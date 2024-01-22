package com.dev.springboot.service.jwt;


import com.dev.springboot.model.Admin;
import com.dev.springboot.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AdminServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;


    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String cin) throws UsernameNotFoundException {

        Admin admin;
        admin = adminRepository.findByCin(cin)
                .orElseThrow(() -> new UsernameNotFoundException("user not found" + cin));
        return new User(admin.getCin() , admin.getPassword() , Collections.emptyList());
    }
}
