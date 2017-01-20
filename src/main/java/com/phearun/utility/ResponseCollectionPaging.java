package com.phearun.utility;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knongdai.tinh.entities.util.Pagination;

public class ResponseCollectionPaging<T> extends Response {

	@JsonProperty("DATA")
	private Collection<T> data;	

	@JsonProperty("PAGING")
	private Pagination pagination;
	
	public ResponseCollectionPaging() {}
	
	public ResponseCollectionPaging(Collection<T> data) {
		this.setData(data);
	}
	public Collection<T> getData() {
		return data;
	}
	public Pagination getPagination() {
		return pagination;
	}
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	public void setData(Collection<T> data) {
		this.data = data;
		if(data.isEmpty())
			super.setCode(HttpCode.RECORD_NOT_FOUND);
		else
			super.setCode(HttpCode.RECORD_FOUND);
	}

	@Override
	public String toString() {
		return "ResponseCollectionPaging [data=" + data + ", pagination=" + pagination + "]";
	}
}
