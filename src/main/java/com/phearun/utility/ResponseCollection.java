package com.phearun.utility;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCollection<T> extends Response {

	@JsonProperty("DATA")
	private Collection<T> data;	

	public ResponseCollection() {}
	
	public ResponseCollection(Collection<T> data) {
		this.setData(data);
	}
	
	public Collection<T> getData() {
		return data;
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
		return "ResponseCollection [data=" + data + "]";
	}
}
