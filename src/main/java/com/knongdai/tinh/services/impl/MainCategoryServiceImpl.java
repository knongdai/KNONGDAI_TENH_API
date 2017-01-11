package com.knongdai.tinh.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.MainCategory;
import com.knongdai.tinh.repositories.MainCategoryRepository;
import com.knongdai.tinh.services.MainCategoryService;

@Service
public class MainCategoryServiceImpl implements MainCategoryService{

	@Autowired
	private MainCategoryRepository mcService;
	
	@Override
	public int addMainCatetory(MainCategory mCategory) {
		return mcService.addMainCatetory(mCategory);
	}

	@Override
	public ArrayList<MainCategory> getAllMainCategory() {
		return mcService.getAllMainCategory();
	}
	
	@Override
	public ArrayList<MainCategory> getMainCategoryById(int id) {
		return mcService.getMainCategoryById(id);
	}

	@Override
	public int updateMainCategory(MainCategory mCategory) {
		return mcService.updateMainCategory(mCategory);
	}

	@Override
	public int deleteMainCategoryById(int id) {
		return mcService.deleteMainCategoryById(id);
	}
	
	@Override
	public List<MainCategory> getSubCategoryAsLists() {
		return mcService.getSubCategoryAsLists();
	}

}
