package com.knongdai.tinh.services;

import java.util.ArrayList;

import com.knongdai.tinh.entities.SubOneCategory;
import com.knongdai.tinh.entities.util.Pagination;

public interface SubOneCategoryService {
	
	public int createSubCatetory(SubOneCategory sCategory);
	public ArrayList<SubOneCategory> getAllSubCategory(Pagination pagin);
	//public ArrayList<SubOneCategory> getById(int id);
	public int updateSubCategory(SubOneCategory sCategory);
	public int deleteSubOneCategoryById(int id);
	
}
