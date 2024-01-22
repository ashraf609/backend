package com.dev.springboot.service;

import com.dev.springboot.dto.SingupRequest;

public interface AuthService {
    boolean createAdmin(SingupRequest singupRequest);
}
