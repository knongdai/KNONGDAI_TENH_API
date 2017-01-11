package com.knongdai.tinh.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.SubTwoCategory;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.SubTwoCategoryRepository;
import com.knongdai.tinh.services.SubTwoCategoryService;

@Service
public class SubTwoCategoryServiceImpl implements SubTwoCategoryService {

	@Autowired
	private SubTwoCategoryRepository ms;

	@Override
	public ArrayList<SubTwoCategory> getAllSubTwoCategory(Pagination pagin){
		try{
			pagin.setTotalCount(ms.countAllSubTwoCategory());
			return ms.getAllSubTwoCategory(pagin);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int createCategory(SubTwoCategory s) {
		return ms.createCategory(s);
	}

	@Override
	public int updateCategory(SubTwoCategory s) {
		return ms.updateCategory(s);
	}

	@Override
	public int deleteCategoryById(int id) {
		return ms.deleteCategoryById(id);
		
	}

	@Override
	public ArrayList<SubTwoCategory> getSubTwoCategoryByMainId(int mainid) {
		return ms.getSubTwoCategoryByMainId(mainid);
	}
	
	
}
