package com.dcfun.service.base;

import java.util.LinkedHashMap;

import com.dcfun.bean.QueryResult;

public interface DAO {

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	public void save(Object entity);

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	public void update(Object entity);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param entityid
	 * @return
	 */
	public <T> void delete(Class<T> entityClass, Object entityid);

	/**
	 * ɾ��ʵ������
	 * 
	 * @param entityClass
	 * @param entityids
	 * @return
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityids);

	/**
	 * ����ʵ��
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param entityid
	 * @return
	 */
	public <T> T find(Class<T> entityClass, Object entityid);

	/**
	 * ���÷��͡����似�� �Բ��ҵ������ݽ��з�ҳ����
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param firstindex
	 * @param maxresult
	 * @return
	 */
	public <T> QueryResult getScrollData(Class<T> entityClass);
	
	public <T> QueryResult getScrollData(Class<T> entityClass, int firstindex,
			int maxresult);

	public <T> QueryResult getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] whereParams);

	public <T> QueryResult getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, LinkedHashMap<String, String> orderby);
	
	public <T> QueryResult getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] whereParams,
			LinkedHashMap<String, String> orderby);
}
