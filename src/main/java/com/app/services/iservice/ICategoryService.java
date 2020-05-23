package com.app.services.iservice;

import java.util.List;

import org.springframework.expression.ParseException;

import com.app.services.model.Category;

public interface ICategoryService {
    List<Category> findAllByOrderByCategoryIdAsc() throws ParseException;
	Category findFirstByCategoryId(Long id) throws ParseException;
    List<Category> findByCategoryStatusOrderByCategoryIdAsc(int status) throws ParseException;
    Category findFirstByCategoryName(String name) throws ParseException;
}