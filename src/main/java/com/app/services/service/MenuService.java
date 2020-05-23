package com.app.services.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.app.services.model.Category;
import com.app.services.model.Menu;
import com.app.services.model.Region;

import com.app.services.repository.MenuRepository;

import com.app.services.APICode;
import com.app.services.ServicesApplication;
import com.app.services.iservice.IMenuService;

@Service
public class MenuService implements IMenuService {
	private int errorCode;
	
	@Autowired private MenuRepository repo;
	@Autowired private CategoryService categoryService;
	@Autowired private RegionService regionService;
	
	public JSONObject create(JSONObject ob) {
		JSONObject json = new JSONObject();
		errorCode = APICode.SUCCESS;

		long categoryId = ob.getLong(Menu.menu_json_idct);
		long regionId = ob.getLong(Menu.menu_json_idrg);
		
		Category category = categoryService.findFirstByCategoryId(categoryId);
		if(category == null) {
			errorCode = APICode.CATEGORY_UNDEFINED_ID;
		}

		Region region = regionService.findFirstByRegionId(regionId);
		if(region == null) {
			errorCode = APICode.REGION_UNDEFINED_ID;
		}
		
		if(errorCode == APICode.SUCCESS) {
			String name = ob.getString(Menu.menu_json_name).toLowerCase();
			Menu obMenu = findFirstByCategoryIdAndRegionIdAndMenuName(categoryId, regionId,name);
			if(obMenu == null) {
				try {
					Menu object = new Menu();
					
					object.setMenuName(name);
					object.setCategoryId(categoryId);
					object.setRegionId(regionId);
						
			    	repo.saveAndFlush(object);
			    	
			    	json.put(Menu.menu_json_id, object.getMenuId());
				} catch (Exception e) {
					errorCode = APICode.ROLLBACK_DATA;
			  		json.put(APICode.OBJECT_ERROR, ServicesApplication.GetMessageOfException(e));
				}
			}else {
				errorCode = APICode.MENU_MENU_EXIST;
			}
		}
		
    	json.put(APICode.STR_RETURN_STATUS, errorCode);
		return json;
	}
	
	public JSONObject update(long id ,JSONObject ob) {
		JSONObject json = new JSONObject();
		errorCode = APICode.SUCCESS;
		
		Menu object = repo.findFirstByMenuId(id);
		if(object != null) {
  			if(	ob.has(Menu.menu_json_name) &&
				!ob.get(Menu.menu_json_name).equals(null) ||
				!ob.getString(Menu.menu_json_name).equals("null")) {
	  			object.setMenuName(ob.getString(Menu.menu_json_name));
	  		}
  			
  			try {
  	  			repo.saveAndFlush(object);
  	  			json.put(Menu.menu_json_id, object.getMenuId());	
			} catch (Exception e) {
				errorCode = APICode.ROLLBACK_DATA;
		  		json.put(APICode.OBJECT_ERROR, ServicesApplication.GetMessageOfException(e));
			}
		}else {
			errorCode = APICode.MENU_UNDEFINED_ID;	
  		}
		
    	json.put(APICode.STR_RETURN_STATUS, errorCode);
		return json;
	}
	
	public JSONObject updateView(Menu object) {
		JSONObject json = new JSONObject();
		errorCode = APICode.SUCCESS;
		
		if(object != null) {
			try {
				object.setMenuView(object.getMenuView()+1);
				repo.saveAndFlush(object);	
			} catch (Exception e) {
				errorCode = APICode.ROLLBACK_DATA;
		  		json.put(APICode.OBJECT_ERROR, ServicesApplication.GetMessageOfException(e));
			}
		}else {
			errorCode = APICode.MENU_UNDEFINED_ID;
		}
		
		json.put(APICode.STR_RETURN_STATUS, errorCode);
		return json;
	}
	
	public JSONObject allRegion() {
		JSONObject json = new JSONObject();
		
		JSONArray arRegion = new JSONArray();
		List<Region> object = regionService.findByRegionStatusOrderByRegionIdAsc(1);
		for(Region ob : object) {
			JSONArray arMenu = new JSONArray();
			JSONObject obRegion = new JSONObject();
			
			obRegion.put(Region.region_json_id, ob.getRegionId());
			obRegion.put(Region.region_json_name, ob.getRegionName());
			
			List<Menu> menuObject = repo.findByRegionId(ob.getRegionId());
			for(Menu menu : menuObject) {
				JSONObject obMenu = new JSONObject();
				
				obMenu.put(Menu.menu_json_id, menu.getMenuId());
				obMenu.put(Menu.menu_json_name, menu.getMenuName());
				
				arMenu.put(obMenu);

				obRegion.put(Menu.menu_json_list, arMenu);
			}
			arRegion.put(obRegion);
		}
		json.put(Menu.menu_json_value, arRegion);
		return json;
	}

	@Override
	public List<Menu> findAllByOrderByMenuIdAsc() throws ParseException{
		List<Menu> object = repo.findAllByOrderByMenuIdAsc();
		return object;
	}

	@Override
	public Menu findFristByMenuId(long id) throws ParseException {
		Menu object = repo.findFirstByMenuId(id);
		return object;
	}
	
	@Override
	public Menu findFirstByCategoryIdAndRegionIdAndMenuName(long categoryId,long regionId,String name) throws ParseException {
		Menu object = repo.findFirstByCategoryIdAndRegionIdAndMenuName(categoryId,regionId,name);
		return object;
	}

	@Override
	public List<Menu> findTop5ByMenuView() {
		// TODO Auto-generated method stub
		List<Menu> object = repo.findTop5ByOrderByMenuViewDesc();
		return object;
	}

	@Override
	public List<Menu> findByCategoryId(long categoryId) {
		// TODO Auto-generated method stub
		List<Menu> object = repo.findByCategoryId(categoryId);
		return object;
	}

	@Override
	public List<Menu> findByRegionId(long regionId) {
		// TODO Auto-generated method stub
		List<Menu> object = repo.findByRegionId(regionId);
		return object;
	}
}
