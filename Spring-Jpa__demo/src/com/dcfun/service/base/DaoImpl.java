package com.dcfun.service.base;

import java.util.LinkedHashMap;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.bean.QueryResult;

@Transactional
public abstract class DaoImpl implements DAO {

	@PersistenceContext
	protected EntityManager em;

	public <T> void delete(Class<T> entityClass, Object entityid) {
		delete(entityClass, new Object[] { entityid });
	}

	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		for (Object id : entityids) {
			em.remove(em.getReference(entityClass, id));
		}
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> T find(Class<T> entityClass, Object entityid) {
		return em.find(entityClass, entityid);
	}

	public void save(Object entity) {
		em.persist(entity);
	}

	public void update(Object entity) {
		em.merge(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult getScrollData(Class<T> entityClass) {
		return getScrollData(entityClass, -1, -1, null, null, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, LinkedHashMap<String, String> orderby) {
		return getScrollData(entityClass, firstindex, maxresult, null, null, orderby);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult getScrollData(Class<T> entityClass, int firstindex,
			int maxresult, String wherejpql, Object[] whereParams) {
		return getScrollData(entityClass, firstindex, maxresult, wherejpql, whereParams, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult getScrollData(Class<T> entityClass, int firstindex,
			int maxresult) {
		return getScrollData(entityClass, firstindex, maxresult, null, null, null);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public <T> QueryResult getScrollData(Class<T> entityClass, int firstindex,
			int maxResult, String wherejpql, Object[] whereParams,
			LinkedHashMap<String, String> orderby) {
		String entityname = getEntityName(entityClass);
		QueryResult<T> qr = new QueryResult<T>();
		Query q = em.createQuery("select o from " + entityname + " o "
				+ (wherejpql == null ? "" : wherejpql) + buildOrderby(orderby));
		if (firstindex != -1 && maxResult != -1)
			q.setFirstResult(firstindex).setMaxResults(maxResult);
		setwhereParams(q, whereParams);
		qr.setResultList(q.getResultList());

		q = em.createQuery("select count(o) from " + entityname + " o"
				+ (wherejpql == null ? "" : wherejpql));
		setwhereParams(q, whereParams);
		qr.setTotalRecord((Long) q.getSingleResult());
		return qr;
	}

	protected void setwhereParams(Query q, Object[] whereParams) {
		if (whereParams != null && whereParams.length > 0) {
			for (int i = 0; i < whereParams.length; i++) {
				q.setParameter(i + 1, whereParams[i]);
			}
		}
	}

	protected String buildOrderby(LinkedHashMap<String, String> orderby) {
		StringBuffer orderbyql = new StringBuffer();
		if (orderby != null && orderby.size() > 0) {
			orderbyql.append("order by");
			for (String key : orderby.keySet()) {
				orderbyql.append(" o.").append(key).append(" ").append(
						orderby.get(key)).append(",");
			}
			orderbyql.deleteCharAt(orderbyql.length() - 1);
		}
		return orderbyql.toString();
	}

	protected <T> String getEntityName(Class<T> entityClass) {
		String entityname = entityClass.getSimpleName();
		Entity entity = entityClass.getAnnotation(Entity.class);
		if (entity.name() != null && !"".equals(entity.name())) {
			entityname = entity.name();
		}
		return entityname;
	}
}
