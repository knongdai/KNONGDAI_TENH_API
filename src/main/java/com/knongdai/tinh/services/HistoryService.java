package com.knongdai.tinh.services;

import java.util.ArrayList;

import com.knongdai.tinh.entities.History;

public interface HistoryService {
	
	public ArrayList<History> getAllHistroy(int userid);
	
	public int createHistory(History h);
	
	public int deleteHistoryById(int userid, int historyid);
}
