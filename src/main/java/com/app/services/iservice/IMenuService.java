package com.app.services.iservice;

import java.util.List;

import org.springframework.expression.ParseException;

import com.app.services.model.Menu;

public interface IMenuService {
    List<Menu> findAllByOrderByMenuIdAsc() throws ParseException;
	Menu findFristByMenuId(long id) throws ParseException;
	Menu findFirstByCategoryIdAndRegionIdAndMenuName(long categoryId, long regionId,String menuName) throws ParseException;
	List<Menu> findTop5ByMenuView();
	List<Menu> findByCategoryId(long categoryId);
	List<Menu> findByRegionId(long regionId);
}
