package com.knongdai.tinh.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knongdai.tinh.entities.History;
import com.knongdai.tinh.repositories.HistoryRepository;
import com.knongdai.tinh.services.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{
	@Autowired
	private HistoryRepository hs;

	@Override
	public ArrayList<History> getAllHistroy(int userid) {
		return hs.getAllHistroy(userid);
	}

	@Override
	public int createHistory(History h) {
		return hs.addHistory(h);
	}

	@Override
	public int deleteHistoryById(int userid, int historyid) {
		return hs.deleteHistoryById(userid, historyid);
	}
}
