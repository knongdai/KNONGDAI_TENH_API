package com.knongdai.tinh.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.Farvorite;
import com.knongdai.tinh.entities.util.Pagination;
import com.knongdai.tinh.repositories.FarvoriteRepository;
import com.knongdai.tinh.services.FarvoriteService;


@Service
public class FarvoriteServiceImpl implements FarvoriteService{

	@Autowired
	private FarvoriteRepository fr;
	
	@Override
	public ArrayList<Farvorite> getFarvoriteByUserId(int userid, Pagination pagin) {
		try{
			pagin.setTotalCount(fr.countFarvoriteByUserId(userid));
			return fr.getFarvoriteByUserId(userid, pagin);
		}catch(Exception e){	
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addFarvorite(Farvorite f) {
		return fr.addFarvorite(f);
	}

	@Override
	public int deleteFarvoriteById(int userid, int farvoriteid) {
		return fr.deleteFarvoriteById(userid, farvoriteid);
	}

}
