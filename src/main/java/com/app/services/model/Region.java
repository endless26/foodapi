package com.app.services.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "region")
public class Region {
	public static final String region_ep = "/rg";
	public static final String region_json_value = "rg";
	public static final String region_json_id = "id";
	public static final String region_json_name = "rgnm";
	public static final String region_json_status = "rgst";

    private long regionId;
    private String regionName;
    private int regionStatus;
    
    @OneToMany(mappedBy = "region")
    Set<Menu> menu;
 
    public Region() {
    	this.regionStatus = 1;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="region_sequence")
    @Column(name = "region_id", nullable = false)
	public long getRegionId() {
        return regionId;
    }
    public void setRegionId(long regionId) {
        this.regionId = regionId;
    }
 
    @Column(name = "region_name", nullable = false)
    public String getRegionName() {
        return regionName;
    }
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
 
    @Column(name = "region_status", nullable = false)
    public int getRegionStatus() {
        return regionStatus;
    }
    public void setRegionStatus(int regionStatus) {
        this.regionStatus = regionStatus;
    }
}