package com.phearun.utility;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.knongdai.tinh.entities.util.Pagination;

public class ResponseCollectionPaging<T> extends ResponseCollection<T> {

	@JsonProperty("PAGING")
	private Pagination pagination;

	public ResponseCollectionPaging() {}
	
	public ResponseCollectionPaging(Collection<T> data, Pagination pagination) {
		super(data);
		this.pagination = pagination;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String toString() {
		return "ResponseCollectionPaging [pagination=" + pagination + ", data=" + super.toString() + "]";
	}
}
