package hitaii.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hitaii.dao.VoyageDaoI;
import hitaii.model.Voyage;
import hitaii.pageModel.Pvoyage;

@Repository("voyageDao")
public class VoyageDaoImpl extends BaseDaoImpl<Voyage> implements VoyageDaoI {
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession (){
		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Serializable save(Voyage m) {
		return this.getCurrentSession().save(m);
	}

	@Override
	public List<Voyage> find(String hql) {
		Query q =this.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	@Override
	public List<Voyage> find(String hql, Object o) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(o);
		return q.list();
	}
	
	@Override
	public Long Count(String hql) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		BigInteger count = (BigInteger) q.uniqueResult();
		Long cou = count.longValue();
		return cou;
	}
	
	@Override
	public Voyage get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<Voyage> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public Voyage get(String hql, Object[] params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (null != params && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i, params[i]);
			}
		}
		List<Voyage> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public Voyage get(String hql, Object v) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(v);
		List<Voyage> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(Voyage v) {
		this.getCurrentSession().delete(v);
	}

	@Override
	public void update(Voyage v) {
		this.getCurrentSession().update(v);
	}

	@Override
	public void saveOrUpdate(Voyage v) {
		this.getCurrentSession().saveOrUpdate(v);
	}
	
	@Override
	public List<Pvoyage> findVesselAndCarrierAndVoyage(String sql, int page,
			int rows) {
		Query q = this.getCurrentSession().createSQLQuery(sql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pvoyage.class));
		List<Pvoyage> voyages = new ArrayList<Pvoyage>();
		if(page==0){
			voyages = q.list();
		}else{
			voyages = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}
		return voyages;
	}
}
