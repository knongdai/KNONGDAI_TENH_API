package com.phearun.utility;

public class ResponseObject<T> extends Response {
	
	private T data;

	public ResponseObject() {}
	
	public ResponseObject(T data) {
		this.setData(data);
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
		if(data == null)
			super.setCode(HttpCode.RECORD_NOT_FOUND);
		else
			super.setCode(HttpCode.RECORD_FOUND);
	}
	
	@Override
	public String toString() {
		return "ResponseObject [data=" + data + "]";
	}

}
