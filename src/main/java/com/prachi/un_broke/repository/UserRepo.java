package com.prachi.un_broke.repository;

import com.prachi.un_broke.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.user_name = ?1")
    User findByUsername(String username);
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}
