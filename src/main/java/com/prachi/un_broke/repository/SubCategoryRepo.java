package com.prachi.un_broke.repository;

import com.prachi.un_broke.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer> {
}
