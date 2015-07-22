package com.dcfun.service.base;

import java.util.LinkedHashMap;

import com.dcfun.bean.QueryResult;

public interface DAO {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public void save(Object entity);

	/**
	 * 增加实体
	 * 
	 * @param entity
	 */
	public void update(Object entity);

	/**
	 * 删除实体
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param entityid
	 * @return
	 */
	public <T> void delete(Class<T> entityClass, Object entityid);

	/**
	 * 删除实体数组
	 * 
	 * @param entityClass
	 * @param entityids
	 * @return
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityids);

	/**
	 * 查找实体
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param entityid
	 * @return
	 */
	public <T> T find(Class<T> entityClass, Object entityid);

	/**
	 * 利用泛型、反射技术 对查找到的数据进行分页处理
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
