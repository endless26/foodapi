package com.app.services.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.app.services.model.Category;
import com.app.services.repository.CategoryRepository;
import com.app.services.APICode;
import com.app.services.ServicesApplication;
import com.app.services.iservice.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	private int errorCode;
	
	@Autowired
	private CategoryRepository repo;
	
	public JSONObject create(JSONObject ob) {
		JSONObject json = new JSONObject();
		errorCode = APICode.SUCCESS;
		String name = ob.getString(Category.category_json_name).toLowerCase();
		
		Category obCategory = findFirstByCategoryName(name);
		if(obCategory == null) {
		  	try {
	  			Category object = new Category();
	  			
	  			object.setCategoryName(name);
	        	repo.saveAndFlush(object);
	        	
	        	json.put(Category.category_json_id, object.getCategoryId());
	  		}catch ( Exception e ){
	  			errorCode = APICode.ROLLBACK_DATA;
		  		json.put(APICode.OBJECT_ERROR, ServicesApplication.GetMessageOfException(e));
			}
		}else{
			errorCode = APICode.CATEGORY_CATEGORY_EXIST;
		}
		
		json.put(APICode.STR_RETURN_STATUS, errorCode);
		return json;
	}

	public JSONObject update(long id, JSONObject ob) {
		JSONObject json = new JSONObject();
		
		try {
	  		Category object = findFirstByCategoryId(id);
            if(object != null) {
	  			if(	ob.has(Category.category_json_name) ||
					!ob.get(Category.category_json_name).equals(null) ||
					!ob.getString(Category.category_json_name).equals("null")) {
		  			object.setCategoryName(ob.getString(Category.category_json_name));
		  		}
	        	repo.saveAndFlush(object);
	        	
	        	json.put(Category.category_json_id, object.getCategoryId());
	  			
	  		}else {
	  			errorCode = APICode.CATEGORY_UNDEFINED_ID;	
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
	public List<Category> findAllByOrderByCategoryIdAsc() throws ParseException{
		List<Category> object = repo.findAllByOrderByCategoryIdAsc();
		return object;
	}

	@Override
	public Category findFirstByCategoryId(Long id) throws ParseException{
		Category object = repo.findFirstByCategoryId(id);
		return object;
	}

	@Override
	public List<Category> findByCategoryStatusOrderByCategoryIdAsc(int status) throws ParseException {
		List<Category> object = repo.findByCategoryStatusOrderByCategoryIdAsc(status);
		return object;
	}

	@Override
	public Category findFirstByCategoryName(String name) throws ParseException {
		Category object = repo.findFirstByCategoryName(name);
		return object;
	}

}
