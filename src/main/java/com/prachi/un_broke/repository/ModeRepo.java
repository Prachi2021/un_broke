package com.prachi.un_broke.repository;


import com.prachi.un_broke.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeRepo extends JpaRepository<SubCategory, Integer> {
    @Query("SELECT m FROM Mode m WHERE s.user_id.user_id = :userId")
    List<SubCategory> findByUserId(@Param("userId") int userId);
}
