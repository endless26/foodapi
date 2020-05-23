package com.app.services.model;

//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	public static final String category_ep = "/ct";
	public static final String category_json_value = "ct";
	public static final String category_json_id = "id";
	public static final String category_json_name = "ctnm";
	public static final String category_json_status = "ctst";

    private long categoryId;
    private String categoryName;
    private int categoryStatus;
    
//    @OneToMany(mappedBy = "category")
//    Set<Menu> menu;
 
    public Category() {
    	this.categoryStatus = 1;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="category_sequence")
    @Column(name = "category_id", nullable = false)
	public long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }
 
    @Column(name = "category_name", nullable = false)
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
 
    @Column(name = "category_status", nullable = false)
    public int getCategoryStatus() {
        return categoryStatus;
    }
    public void setCategoryStatus(int categoryStatus) {
        this.categoryStatus = categoryStatus;
    }
}