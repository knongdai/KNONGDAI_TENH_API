package com.phearun.utility;

import java.util.List;

public class ResponseMap<T> extends Response {
	private List<T> data;	
	private Paging pagination;

	public ResponseMap() {}
	
	public ResponseMap(List<T> data, Paging pagination) {
		this.setData(data);
		this.setPagination(pagination);
	}
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
		if(data.isEmpty())
			super.setCode(HttpCode.RECORD_NOT_FOUND);
		else
			super.setCode(HttpCode.RECORD_FOUND);
	}
	public Paging getPagination() {
		return pagination;
	}
	public void setPagination(Paging pagination) {
		this.pagination = pagination;
	}
	
	@Override
	public String toString() {
		return "ResponseList [data=" + data + ", pagination=" + pagination + "]";
	}

}
