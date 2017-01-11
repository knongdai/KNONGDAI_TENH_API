package com.knongdai.tinh.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.SourceCategory;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.SourceCategoryRepository;
import com.knongdai.tinh.services.SourceCategoryService;

@Service
public class SourceCategoryServiceImpl implements SourceCategoryService{

	@Autowired
	private SourceCategoryRepository scr;
	@Override
	public ArrayList<SourceCategory> getAllSourceCategory(Pagination pagination){
		try{
			pagination.setTotalCount(scr.countAllSourceCategory());
			return scr.getAllSourceCategory(pagination);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int createSourceCategory(SourceCategory sc) {
		return scr.createSourceCategory(sc);
	}

	@Override
	public int updateSourceCategory(SourceCategory sc) {
		return scr.updateSourceCategory(sc);
	}

	@Override
	public int deleteSourceCategoryById(int id) {
		return scr.deleteSourceCategoryById(id);
	}

	@Override
	public ArrayList<SourceCategory> getAllUrlToScrap() {
		return scr.getAllUrlToScrap();
	}

	@Override
	public ArrayList<SourceCategory> getById(int id) {
		return scr.getById(id);
	}

}
