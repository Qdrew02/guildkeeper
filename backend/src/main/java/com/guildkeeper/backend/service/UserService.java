package com.guildkeeper.backend.service;

import com.guildkeeper.backend.model.dto.auth.LoginRequest;
import com.guildkeeper.backend.model.dto.auth.LoginResponse;
import com.guildkeeper.backend.model.dto.auth.RegisterRequest;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Repository;

public interface UserService {

    LoginResponse registerUser(RegisterRequest registerRequest);

    LoginResponse authenticateUser(LoginRequest loginRequest);


}
