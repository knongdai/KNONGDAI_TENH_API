package com.knongdai.tinh.services;

import java.util.ArrayList;

import com.knongdai.tinh.entities.SubTwoCategory;
import com.knongdai.tinh.entities.util.Pagination;

public interface SubTwoCategoryService {
	
	public ArrayList<SubTwoCategory> getAllSubTwoCategory(Pagination pagin);
	
	public int createCategory(SubTwoCategory s);
	
	public int updateCategory(SubTwoCategory s);
	
	public int deleteCategoryById(int id);
	
	public ArrayList<SubTwoCategory> getSubTwoCategoryByMainId(int mainid);
}
