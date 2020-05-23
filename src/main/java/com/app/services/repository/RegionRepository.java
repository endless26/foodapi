package com.app.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.services.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region,Long>{
	List<Region> findAllByOrderByRegionIdAsc();
	Region findFirstByRegionId(Long id);
	List<Region> findByRegionStatusOrderByRegionIdAsc(int status);
	Region findFirstByRegionName(String name);
}