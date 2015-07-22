package com.dcfun.service.product;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcfun.service.base.DaoImpl;

@Service
@Transactional
public class ProductTypeServiceBean extends DaoImpl implements
		ProductTypeService {

	@Override
	public <T> void delete(Class<T> entityClass, Object[] entityids) {
		if (entityids != null && entityids.length > 0) {
			StringBuffer jpql = new StringBuffer();
			for (int i = 0; i < entityids.length; i++) {
				jpql.append("?").append(i + 2).append(",");
			}
			jpql.deleteCharAt(jpql.length() - 1);
			Query q = em.createQuery(
					"update ProductType p set p.visible=?1 where p.typeid in("
							+ jpql + ")").setParameter(1, false);
			for (int i = 0; i < entityids.length; i++) {
				q.setParameter(i + 2, entityids[i]);
			}
			q.executeUpdate();
		}
	}
}
