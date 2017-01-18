package com.phearun.utility;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseList2<T> extends Response {

	@JsonProperty("DATA")
	private List<T> data;	

	public ResponseList2() {}
	
	public ResponseList2(List<T> data) {
		this.setData(data);
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

	@Override
	public String toString() {
		return "ResponseList2 [data=" + data + "]";
	}
}
