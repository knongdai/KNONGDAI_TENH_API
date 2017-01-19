package com.phearun.utility;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSet2<T> extends Response {

	@JsonProperty("DATA")
	private Set<T> data;	

	public ResponseSet2() {}
	
	public ResponseSet2(Set<T> data) {
		this.setData(data);
	}
	
	public Set<T> getData() {
		return data;
	}
	public void setData(Set<T> data) {
		this.data = data;
		if(data.isEmpty())
			super.setCode(HttpCode.RECORD_NOT_FOUND);
		else
			super.setCode(HttpCode.RECORD_FOUND);
	}

	@Override
	public String toString() {
		return "ResponseList2 [data=" + data + "]";
	}
}
