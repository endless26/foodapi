package com.app.services.iservice;

import java.util.List;

import org.springframework.expression.ParseException;

import com.app.services.model.Region;

public interface IRegionService {
    List<Region> findAllByOrderByRegionIdAsc() throws ParseException;
	Region findFirstByRegionId(Long id) throws ParseException;
    List<Region> findByRegionStatusOrderByRegionIdAsc(int status) throws ParseException;
    Region findFirstByRegionName(String name) throws ParseException;
}
