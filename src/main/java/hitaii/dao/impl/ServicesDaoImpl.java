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

import hitaii.dao.ServicesDaoI;
import hitaii.model.Services;
import hitaii.pageModel.Porders;
import hitaii.pageModel.Pservices;
import hitaii.pageModel.Pvessel;

@Repository("servicesDao")
public class ServicesDaoImpl extends BaseDaoImpl<Services> implements ServicesDaoI {
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

	public List<Pservices> findwithWhesNvocc(String hql, int page, int rows) {

		Query q =this.getCurrentSession().createSQLQuery(hql);
		
		q.setResultTransformer(Transformers.aliasToBean(Pservices.class));
		List<Pservices> pservicesList = new ArrayList<Pservices>();
		if(page==0){
			pservicesList = q.list();
		}else{
			pservicesList = q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		}

		return pservicesList;
	}
	
	@Override
	public Long findCount(String hql) {
		Query q = this.getCurrentSession().createSQLQuery(hql);
		BigInteger count = (BigInteger) q.uniqueResult();
		Long cou = count.longValue();
		return cou;
	}
	
	@Override
	public List<Services> findServices(String hql) {
		Query q =this.getCurrentSession().createQuery(hql);
		return q.list();
	}
	
	@Override
	public Serializable saveServices(Services o) {
		return this.getCurrentSession().save(o);
	}
	
	@Override
	public Services getService(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<Services> l = q.list();
		if (null != l && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}
	
	@Override
	public void deleteService(Services o) {
		this.getCurrentSession().delete(o);
	}

	@Override
	public void updateService(Services o) {
		this.getCurrentSession().update(o);
	}

	@Override
	public List<Services> findServices(String hql, Object o) {
		Query q = this.getCurrentSession().createQuery(hql);
		q.setProperties(o);
		return q.list();
	}
	
	@Override
	public List<Services> findServices(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);	
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}
	
	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}
}
