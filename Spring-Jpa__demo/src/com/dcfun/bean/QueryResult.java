package com.dcfun.bean;

import java.util.List;

public class QueryResult<T> {

	private Long totalRecord;
	private List<T> resultList;
	
	public Long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Long long1) {
		this.totalRecord = long1;
	}
	public List<T> getResultList() {
		return resultList;
	}
	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}
}
