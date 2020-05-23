package com.app.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.services.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{
	List<Category> findAllByOrderByCategoryIdAsc();
	Category findFirstByCategoryId(Long id);
	List<Category> findByCategoryStatusOrderByCategoryIdAsc(int status);
	Category findFirstByCategoryName(String name);
}