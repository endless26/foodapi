package com.app.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.services.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long>{
	List<Menu> findAllByOrderByMenuIdAsc();
	Menu findFirstByMenuId(long id);
	Menu findFirstByCategoryIdAndRegionIdAndMenuName(long categoryId,long regionId,String menuName);
	List<Menu> findTop5ByOrderByMenuViewDesc();
	List<Menu> findByCategoryId(long categoryId);
	List<Menu> findByRegionId(long regionId);
}