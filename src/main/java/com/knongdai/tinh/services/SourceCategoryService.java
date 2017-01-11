package com.knongdai.tinh.services;

import java.util.ArrayList;

import com.knongdai.tinh.entities.SourceCategory;
import com.knongdai.tinh.entities.util.Pagination;

public interface SourceCategoryService {
	
	public ArrayList<SourceCategory> getAllSourceCategory(Pagination pagin);
	
	public ArrayList<SourceCategory> getById(int id);
	public int createSourceCategory(SourceCategory sc);
	
	public int updateSourceCategory(SourceCategory sc);
	
	public int deleteSourceCategoryById(int id);
	
	public ArrayList<SourceCategory> getAllUrlToScrap();
}
