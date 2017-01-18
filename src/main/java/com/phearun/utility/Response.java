package com.phearun.utility;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
	
	@JsonProperty("CODE")
	private String code;
	
	@JsonProperty("MESSAGE")
	private String message;
	
	public Response() {}
	
	public Response(boolean status) {
		if(status)
			this.code = HttpCode.SUCCEED;
		else
			this.code = HttpCode.FAILED;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		switch(code){
			case HttpCode.FAILED:
				message = "Operation Failed!";
				break;
			case HttpCode.SUCCEED:
				message = "Operation Succeed!";
				break;
			case HttpCode.RECORD_FOUND:
				message = "Record Found!";
				break;
			case HttpCode.RECORD_NOT_FOUND:
				message = "Record Not Found!";
				break;
			case HttpCode.INTERNAL_SERVER_ERROR:
				message = "Internal Server Error!";
				break;
		}
		return message;
	}
	
	@Override
	public String toString() {
		return "Response [code=" + code + ", message=" + message + "]";
	}
	
}
