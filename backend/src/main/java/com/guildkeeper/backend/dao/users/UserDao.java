package com.guildkeeper.backend.dao.users;

import com.guildkeeper.backend.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {
    User createUser(User user);
    User getUserById(int userId);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    boolean deleteUser(int userId);
}
