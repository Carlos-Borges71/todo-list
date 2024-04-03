package br.com.carlosborges.controller.exception;

import java.io.Serializable;

public class StandartError implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long timestamp;
	private Integer status;
	private String error;
	private String exception;
	private String path;
	
	public StandartError() {		
	}

	public StandartError(Long timestamp, Integer status, String error, String exception, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.exception = exception;
		this.path = path;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

}
