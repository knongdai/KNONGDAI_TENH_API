package com.phearun.utility;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knongdai.tinh.entities.util.Pagination;

public class ResponseObject<T> extends Response {
	
	@JsonProperty("DATA")
	private List<T> data;	
	
	@JsonProperty("PAGING")
	private Pagination pagination;

	public ResponseObject() {}
	
	public ResponseObject(List<T> data, Pagination pagination) {
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
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	@Override
	public String toString() {
		return "ResponseList [data=" + data + ", pagination=" + pagination + "]";
	}

}
