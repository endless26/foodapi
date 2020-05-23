package com.app.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
	
	public static final String menu_ep = "/mn";
	public static final String menu_json_value = "mn";
	public static final String menu_json_list = "list";
	public static final String menu_json_id = "id";
	public static final String menu_json_idct = "idct";
	public static final String menu_json_idrg = "idrg";
	public static final String menu_json_name = "mnnm";
	public static final String menu_json_view = "mnv";
	public static final String menu_json_status = "mnst";

    private long menuId;
    private String menuName;
    private int menuView;
    private int menuStatus;
    private long categoryId;
    private long regionId;

//    @ManyToOne
//    private List<Category> category;
    
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "Category", 
//          joinColumns = { @JoinColumn(name = "menu_id") }, 
//          inverseJoinColumns = { @JoinColumn(name = "category_id") })
//    private List<Category> category;

    
//    @ManyToOne
//    @JoinColumn(name = "region_id")
//    private Set<Region> region;
 
    public Menu() {
    	this.menuStatus = 1;
    	this.menuView = 0;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="menu_sequence")
    @Column(name = "menu_id", nullable = false)
	public long getMenuId() {
        return menuId;
    }
    public void setMenuId(long menuId) {
        this.menuId = menuId;
    }

    @Column(name = "category_id", nullable = false)
    public long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Column(name = "region_id", nullable = false)
    public long getRegionId() {
        return regionId;
    }
    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }
 
    @Column(name = "menu_name", nullable = false)
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
 
    @Column(name = "menu_view", nullable = false)
    public int getMenuView() {
        return menuView;
    }
    public void setMenuView(int menuView) {
        this.menuView = menuView;
    }
    
    @Column(name = "menu_status", nullable = false)
    public int getMenuStatus() {
        return menuStatus;
    }
    public void setMenuStatus(int menuStatus) {
        this.menuStatus = menuStatus;
    }
}