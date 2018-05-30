package hitaii.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.UserDaoI;
import hitaii.dao.WhesDaoI;
import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.model.Whes;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pwhes;
import hitaii.service.WhesServiceI;
import hitaii.util.WhesdtlUtil;

@Service("whesService")
public class WhesServiceImpl implements WhesServiceI {
	
	private WhesDaoI whesDao;
	private UserDaoI userDao;
	
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	public WhesDaoI getWhesDao() {
		return whesDao;
	}
	@Autowired
	public void setWhesDao(WhesDaoI whesDao) {
		this.whesDao = whesDao;
	}
	@Override
	public List<Pwhes> findAllWhes() {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String userHql = "from Users where logname = '"+sessionInfo.toString()+"'";
		Users user = userDao.get(userHql);
		Iterator<Whes> iterator=user.getWheses().iterator();
		String sql = "(";
		if(iterator.hasNext()){
			while(iterator.hasNext()){
				sql += "'" + iterator.next().getId() + "',";
			}
			sql = sql.substring(0,sql.length()-1);
			sql += ")";
		}else{
			sql += "'')";
		}
		String hql = "from Whes where id in "+sql+"";
		List<Whes> whes = whesDao.find(hql);
		List<Pwhes> pwhes = new ArrayList<Pwhes>();
		if (null != whes && whes.size() > 0) {
			for (Whes w : whes) {
				Pwhes pwhes2 = new Pwhes();
				BeanUtils.copyProperties(w, pwhes2);
				pwhes.add(pwhes2);
			}
		}
		return pwhes;
	}
	
	@Override
	public DataGrid getAllMakeDataGrid(Pwhes pwhes) {
		DataGrid d = new DataGrid();
		List<Pwhes> pwhess = new ArrayList<Pwhes>();
		String hql = "from Whes where 1=1 ";
		List<Whes> whess= new ArrayList<Whes>();
			if(null != pwhes.getSort() && !pwhes.getSort().isEmpty() && null != pwhes.getOrder() && !pwhes.getOrder().isEmpty()){
				hql=hql+" order by "+ pwhes.getSort() + " " + pwhes.getOrder();
			}
			whess = whesDao.find(hql,pwhes.getPage(),pwhes.getRows());
			
		changeModelTd(whess,pwhess);
		d.setRows(pwhess);
		d.setTotal(whesDao.count("select count(*)"+hql,pwhes));
		return d;
	}
	private void changeModelTd(List<Whes> whess, List<Pwhes> pwhess) {
			if(whess.size()>0){
				for(Whes whes : whess){
					Pwhes pwhes = new Pwhes();
					BeanUtils.copyProperties(whes, pwhes);
					pwhess.add(pwhes);
				}
			}
	}
	@Override
	public boolean saveOneWhes(Pwhes pwhes) {
		if(null==pwhes){
			return false;
		}
		if(null==pwhes.getFullname() || "".equals(pwhes.getFullname())){
			return false;
		}
		if(null==pwhes.getId() || "".equals(pwhes.getId())){
			pwhes.setId(WhesdtlUtil.getUUID());
		}
		Whes whes = new Whes();
		BeanUtils.copyProperties(pwhes, whes);
		whesDao.saveOrUpdate(whes);
		return true;
	}
	@Override
	public Pwhes findWarehouseEditObject(Pwhes pwhes) {
		if(null == pwhes || null==pwhes.getId() ||"".equals(pwhes.getId())){
			return null;
		}
		String hql = "from Whes where 1 = 1 and id = :id";
		Whes whes = whesDao.get(hql, pwhes);
		BeanUtils.copyProperties(whes,pwhes);
		return pwhes;
	}
	@Override
	public boolean delectOneWarehouse(Pwhes pwhes) {
		if(null == pwhes ||"".equals(pwhes.getId()) || null == pwhes.getId()){
			return false;
		}
		Whes whes=new Whes();
		BeanUtils.copyProperties(pwhes, whes);
		whesDao.delete(whes);
		return true;
	}
	
}
