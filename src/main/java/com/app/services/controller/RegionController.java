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
import com.app.services.model.Region;
import com.app.services.service.RegionService;

@RestController
@RequestMapping(Region.region_ep)
public class RegionController {
    @Autowired
    public RegionService service;

    // ALL Data
    @RequestMapping(value=APICode.list, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAll(@RequestBody String json) {
    	JSONObject jsonReturn = new JSONObject();
    	
    	List<Region> object = service.findAllByOrderByRegionIdAsc();
    	jsonReturn = JsonResult.processJsonRegion(object);
    	
    	return ResponseEntity.ok(jsonReturn.toString());
    }

    // List Active
    @RequestMapping(value=APICode.listActive, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> listActive(@RequestBody String json) {
    	JSONObject jsonReturn = new JSONObject();
    	
    	List<Region> object = service.findByRegionStatusOrderByRegionIdAsc(APICode.STATUS_ACTIVE);
    	jsonReturn = JsonResult.processJsonRegion(object);
    	
    	return ResponseEntity.ok(jsonReturn.toString());
    }
    
    // Detail
    @RequestMapping(value=APICode.detail, method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getById(@PathVariable(value = "id") Long id,@RequestBody String json){
    	JSONObject jsonReturn = new JSONObject();
    	try {
            Region object = service.findFirstByRegionId(id);
            if(object != null) {
            	List<Region> obList = Arrays.asList(object);
            	jsonReturn = JsonResult.processJsonRegion(obList);
            }else {
            	jsonReturn.put(APICode.STR_RETURN_STATUS, APICode.REGION_UNDEFINED_ID);
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
    	
  		if(	!ob.has(Region.region_json_name) ||
			ob.get(Region.region_json_name).equals(null) ||
			ob.getString(Region.region_json_name).equals("null")) {
  			jsonReturn.put(APICode.STR_RETURN_STATUS, APICode.REGION_MISSING_NAME);
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
    	
    	jsonReturn = service.update(id, ob);
    	
	  	return ResponseEntity.ok(jsonReturn.toString());
    }
}