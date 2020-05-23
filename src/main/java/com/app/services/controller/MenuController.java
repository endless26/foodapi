package com.app.services.controller;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.services.APICode;
import com.app.services.JsonResult;
import com.app.services.ServicesApplication;
import com.app.services.model.Menu;
import com.app.services.service.MenuService;

@RestController
@RequestMapping(Menu.menu_ep)
public class MenuController {
    @Autowired
    private MenuService service;

    // ALL Data
    @RequestMapping(value=APICode.list, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAll(@RequestBody String json) {
    	JSONObject jsonReturn = new JSONObject();
    	
    	List<Menu> object = service.findAllByOrderByMenuIdAsc();
    	jsonReturn = JsonResult.processJsonMenu(object);
    	
    	return ResponseEntity.ok(jsonReturn.toString());
    }

    // Detail
    @RequestMapping(value=APICode.detail, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getById(@PathVariable(value = "id") Long id,@RequestBody String json){
    	JSONObject jsonReturn = new JSONObject();
    	try {
            Menu object = service.findFristByMenuId(id);
            if(object != null) {
            	jsonReturn = service.updateView(object);
            	List<Menu> obList = Arrays.asList(object);
            	jsonReturn = JsonResult.processJsonMenu(obList);
            }else {
            	jsonReturn.put(APICode.STR_RETURN_STATUS, APICode.MENU_UNDEFINED_ID);
            }
            
		} catch (Exception e) {
	  		jsonReturn.put(APICode.OBJECT_ERROR, ServicesApplication.GetMessageOfException(e));
		}
        return ResponseEntity.ok(jsonReturn.toString());
    }

    // Create
    @RequestMapping(value=APICode.create, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> create(@RequestBody String json) {
    	JSONObject ob = new JSONObject(json);
    	JSONObject jsonReturn = new JSONObject();
    	
  		if(	!ob.has(Menu.menu_json_name) ||
			ob.get(Menu.menu_json_name).equals(null) ||
			ob.getString(Menu.menu_json_name).equals("null")) {
  			jsonReturn.put(APICode.STR_RETURN_STATUS, APICode.MENU_MISSING_NAME);
  		}else if(!ob.has(Menu.menu_json_idct) ||
  				ob.get(Menu.menu_json_idct).equals(null)) {
	  			jsonReturn.put(APICode.STR_RETURN_STATUS, APICode.MENU_MISSING_CATEGORY);
  		}else if(!ob.has(Menu.menu_json_idrg) ||
  				ob.get(Menu.menu_json_idrg).equals(null)) {
	  			jsonReturn.put(APICode.STR_RETURN_STATUS, APICode.MENU_MISSING_REGION);
  		}else {
  			jsonReturn = service.create(ob);
  		}
	  	return ResponseEntity.ok(jsonReturn.toString());
    }
    
    // Update
    @RequestMapping(value=APICode.update, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> update(@PathVariable(value = "id") Long id,@RequestBody String json) {
    	JSONObject ob = new JSONObject(json);
    	JSONObject jsonReturn = new JSONObject();
    	
    	jsonReturn = service.update(id,ob);
	  	
	  	return ResponseEntity.ok(jsonReturn.toString());
    }

    // Top 5 Menu
    @RequestMapping(value=APICode.topMenu, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> top5Menu(@RequestBody String json) {
    	JSONObject jsonReturn = new JSONObject();
    	
    	List<Menu> object = service.findTop5ByMenuView();
    	jsonReturn = JsonResult.processJsonMenu(object);
    	
    	return ResponseEntity.ok(jsonReturn.toString());
    }

    // Menu By Category
    @RequestMapping(value=APICode.menuByCategory, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> byCategory(@RequestBody String json) {
    	JSONObject ob = new JSONObject(json);
    	JSONObject jsonReturn = new JSONObject();
    	
    	if(	!ob.has(Menu.menu_json_idct) ||
			ob.get(Menu.menu_json_idct).equals(null) ||
			ob.getInt(Menu.menu_json_idct) == 0) {
  			jsonReturn.put(APICode.STR_RETURN_STATUS, APICode.MENU_MISSING_CATEGORY);
  		}
    	
    	List<Menu> object = service.findByCategoryId(ob.getLong(Menu.menu_json_idct));
    	jsonReturn = JsonResult.processJsonMenu(object);
    	
    	return ResponseEntity.ok(jsonReturn.toString());
    }
    
    // Menu All Region
    @RequestMapping(value=APICode.menuAllRegion, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> allRegion(@RequestBody String json) {
    	JSONObject ob = new JSONObject(json);
    	JSONObject jsonReturn = new JSONObject();
    	
    	jsonReturn = service.allRegion();
    	
    	return ResponseEntity.ok(jsonReturn.toString());
    }
}