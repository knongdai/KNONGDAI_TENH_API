package com.knongdai.tinh.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.Source;
import com.knongdai.tinh.repositories.SourceRepository;
import com.knongdai.tinh.services.SourceService;

@Service
public class SourceServiceImpl implements SourceService{

	@Autowired
	private SourceRepository sr;
	@Override
	public ArrayList<Source> getAllSources() {
		return sr.getAllSources();
	}

	@Override
	public int createSource(Source s) {
		return sr.createSource(s);
	}

	@Override
	public int updateSource(Source s) {
		return sr.updateSource(s);
	}

	@Override
	public int deleteSourceById(int id) {
		return sr.deleteSourceById(id);
	}

	@Override
	public List<Source> getAll() {
		return sr.getAll();
	}
	
	@Override
	public List<Source> getSourceJoinOneToMany() {
		return sr.getSourceJoinOneToMany();
	}
	
	/*@Override
	public List<Source> getTotalProductsInSource() {
		return sr.getTotalProductsInSource();
	}*/

}
