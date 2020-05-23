package com.app.services.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.app.services.model.Region;
import com.app.services.repository.RegionRepository;
import com.app.services.APICode;
import com.app.services.ServicesApplication;
import com.app.services.iservice.IRegionService;

@Service
public class RegionService implements IRegionService {
	private int errorCode;
	
	@Autowired
	private RegionRepository repo;


	public JSONObject create(JSONObject ob) {
		JSONObject json = new JSONObject();
		errorCode = APICode.SUCCESS;

		String name = ob.getString(Region.region_json_name).toLowerCase();
		Region obRegion = findFirstByRegionName(name);
		if(obRegion == null) {
		  	try {
	  			Region object = new Region();
	  			
	  			object.setRegionName(name);
	        	repo.saveAndFlush(object);
	        	
	        	json.put(Region.region_json_id, object.getRegionId());
	  		}catch ( Exception e ){
	  			errorCode = APICode.ROLLBACK_DATA;
		  		json.put(APICode.OBJECT_ERROR, ServicesApplication.GetMessageOfException(e));
			}
		}else {
			errorCode = APICode.REGION_REGION_EXIST;
		}
		json.put(APICode.STR_RETURN_STATUS, errorCode);
		return json;
	}

	public JSONObject update(long id, JSONObject ob) {
		JSONObject json = new JSONObject();
		
		try {
	  		Region object = findFirstByRegionId(id);
            if(object != null) {
	  			if(	ob.has(Region.region_json_name) ||
					!ob.get(Region.region_json_name).equals(null) ||
					!ob.getString(Region.region_json_name).equals("null")) {
		  			object.setRegionName(ob.getString(Region.region_json_name));
		  		}
	        	repo.saveAndFlush(object);
	        	
	        	json.put(Region.region_json_id, object.getRegionId());
	  			
	  		}else {
	  			errorCode = APICode.REGION_UNDEFINED_ID;	
	  		}
	  	}
		catch ( Exception e ){
			errorCode = APICode.ROLLBACK_DATA;
	  		json.put(APICode.OBJECT_ERROR, ServicesApplication.GetMessageOfException(e));
		}
		
		json.put(APICode.STR_RETURN_STATUS, errorCode);	
		return json;
	}
	
	@Override
	public List<Region> findAllByOrderByRegionIdAsc() {
		List<Region> object = repo.findAllByOrderByRegionIdAsc();
		return object;
	}

	@Override
	public Region findFirstByRegionId(Long id) {
		Region object = repo.findFirstByRegionId(id);
		return object;
	}

	@Override
	public List<Region> findByRegionStatusOrderByRegionIdAsc(int status) throws ParseException {
		List<Region> object = repo.findByRegionStatusOrderByRegionIdAsc(status);
		return object;
	}

	@Override
	public Region findFirstByRegionName(String name) throws ParseException {
		Region object = repo.findFirstByRegionName(name);
		return object;
	}

}
