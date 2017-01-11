package com.knongdai.tinh.services;

import java.util.ArrayList;
import java.util.List;

import com.knongdai.tinh.entities.MainCategory;

public interface MainCategoryService {
	
	public int addMainCatetory(MainCategory mCategory);

	public ArrayList<MainCategory> getAllMainCategory();

	public ArrayList<MainCategory> getMainCategoryById(int id);

	public int updateMainCategory(MainCategory mCategory);
	
	public int deleteMainCategoryById(int id);
	
	public List<MainCategory> getSubCategoryAsLists();
}
