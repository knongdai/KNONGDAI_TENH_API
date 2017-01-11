package com.knongdai.tinh.services;

import java.util.ArrayList;

import com.knongdai.tinh.entities.Farvorite;
import com.knongdai.tinh.entities.util.Pagination;

public interface FarvoriteService {
	
	public ArrayList<Farvorite> getFarvoriteByUserId(int userid, Pagination pagin);
	
	public int addFarvorite(Farvorite f);
	
	public int deleteFarvoriteById(int userid, int farvoriteid);
}
