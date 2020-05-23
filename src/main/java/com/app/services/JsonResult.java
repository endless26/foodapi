package com.app.services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.services.model.*;

public class JsonResult {
	
	public static JSONObject processJsonCategory(List<Category> category){
		JSONObject json = new JSONObject();
		JSONArray aData = new JSONArray();

		for(Category object : category) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put(Category.category_json_id, object.getCategoryId());
			jsonObject.put(Category.category_json_name, object.getCategoryName());
			jsonObject.put(Category.category_json_status, object.getCategoryStatus());
			
			aData.put(jsonObject);
		}
		json.put(Category.category_json_value, aData);
		
		return json;
	}
	
	public static JSONObject processJsonRegion(List<Region> region){
		JSONObject json = new JSONObject();
		JSONArray aData = new JSONArray();

		for(Region object : region) {
			JSONObject jsonObject = new JSONObject();
			
			jsonObject.put(Category.category_json_id, object.getRegionId());
			jsonObject.put(Category.category_json_name, object.getRegionName());
			jsonObject.put(Category.category_json_status, object.getRegionStatus());
			
			aData.put(jsonObject);
		}
		json.put(Region.region_json_value, aData);
		
		return json;
	}

	public static JSONObject processJsonMenu(List<Menu> menu){
		JSONObject json = new JSONObject();
		JSONArray aData = new JSONArray();

		for(Menu object : menu) {
			JSONObject jsonObject = new JSONObject();

			jsonObject.put(Menu.menu_json_id, object.getMenuId());
			jsonObject.put(Menu.menu_json_idct, object.getCategoryId());
			jsonObject.put(Menu.menu_json_idrg, object.getRegionId());
			jsonObject.put(Menu.menu_json_name, object.getMenuName());
			jsonObject.put(Menu.menu_json_status, object.getMenuStatus());
			
			aData.put(jsonObject);
		}
		json.put(Menu.menu_json_value, aData);
		
		return json;
	}
}
