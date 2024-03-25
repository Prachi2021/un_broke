package com.prachi.un_broke.service;

import com.prachi.un_broke.dto.UserRegistrationDTO;
import com.prachi.un_broke.model.User;
import com.prachi.un_broke.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getUsers() {
        if(userRepo!=null)
            return userRepo.findAll();
        else
            return null;
    }

    public User getUserById(int id) {
        return userRepo.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepo.findByUsername(email).orElse(null);
    }

    public User createUser(UserRegistrationDTO user){
        User user1 = getUserByEmail(user.getEmail());
        if(user1 == null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
            String pass = encoder.encode(user.getPassword());
            User user2 = new User(user.getUser_name(), user.getEmail(), pass);
            return userRepo.save(user2);
        }
        else
            return null;
    }

    public User updateUser(User user, int id) {
        User user1 = getUserById(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String pass = encoder.encode(user.getPassword());
        if (user1 != null){
            user1.setUser_name(user.getUser_name());
            user1.setEmail(user.getEmail());
            user1.setPassword(pass);
            return userRepo.save(user1);
        }
        else
            return null;

    }
    public List<User> deleteUser(int id){
        User user1 = getUserById(id);
        if(user1 != null) {
            userRepo.delete(user1);
            return getUsers();
        }
        else
            return null;
    }

    public User loadUserByUsername(String username) {
        User user = userRepo.findByEmail(username).orElse(null);
        if (user == null) {
            return null;
        }
        return user;
    }
}
