package com.app.services;

public class APICode {
	public static final String list = "/";
	public static final String listActive = "/list";
	public static final String detail = "/{id}";
	public static final String create = "/create";
	public static final String update = "/update/{id}";
	public static final String updateView = "/update/view/{id}";
	public static final String topMenu = "/top";
	public static final String menuByCategory = "/ct";
	public static final String menuAllRegion = "/rg";
	
	public static final int STATUS_ACTIVE = 1;
	
	public static final String STR_RETURN_STATUS = "ec";
	public static final String OBJECT_ERROR = "oer";
	
	public static final int SUCCESS = 0;
	public static final int UNKNOWN_ERROR = -1;
	public static final int INVALID_JSON_FORMAT = -2;
	public static final int ROLLBACK_DATA = -4;
	public static final int DATABASE_ERROR = -3;

	// Category
	public static final int CATEGORY_UNDEFINED_ID = 100000;
	public static final int CATEGORY_MISSING_NAME = 100001;
	public static final int CATEGORY_MISSING_STATUS = 100002;
	public static final int CATEGORY_CATEGORY_EXIST = 100003;
	
	// Region
	public static final int REGION_UNDEFINED_ID = 110000;
	public static final int REGION_MISSING_NAME = 110001;
	public static final int REGION_MISSING_STATUS = 110002;
	public static final int REGION_REGION_EXIST = 110003;
	
	// Menu
	public static final int MENU_UNDEFINED_ID = 120000;
	public static final int MENU_MISSING_NAME = 120001;
	public static final int MENU_MISSING_STATUS = 120002;
	public static final int MENU_MISSING_CATEGORY = 120003;
	public static final int MENU_MISSING_REGION = 120004;
	public static final int MENU_MENU_EXIST = 120005;
	
	//employee model [testing]
	public static final int EMPLOYEE_UNDEFINED_ID = 999990;
	public static final int EMPLOYEE_MISSING_FIRSTNAME = 999991;
	public static final int EMPLOYEE_MISSING_LASTNAME = 999992;
	public static final int EMPLOYEE_MISSING_EMAIL = 999993;
}
