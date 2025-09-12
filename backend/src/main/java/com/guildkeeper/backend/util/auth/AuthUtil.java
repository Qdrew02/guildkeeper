package com.guildkeeper.backend.util.auth;

import com.guildkeeper.backend.dao.users.UserDao;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class AuthUtil {
    private final UserDao userDao;

    public AuthUtil(UserDao userDao) {
        this.userDao = userDao;
    }
    public long getUserIdFromPrincipal (Principal principal) {
        return userDao.getUserByUsername(principal.getName()).getUserId();
    }
}
