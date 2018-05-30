package hitaii.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.record.formula.MemErrPtg;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.CompanyDaoI;
import hitaii.dao.MemoDaoI;
import hitaii.dao.UserDaoI;
import hitaii.dao.WhesDaoI;
import hitaii.dao.WhesdtlDaoI;
import hitaii.model.Company;
import hitaii.model.Memo;
import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.model.Whes;
import hitaii.model.Whesdtl;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pbooknum;
import hitaii.pageModel.Pmemo;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.MemoServiceI;

@Service(value="memoService")
public class MemoServiceImpl implements MemoServiceI {
	
	private MemoDaoI memoDao;
	private WhesdtlDaoI whesdtlDao;
	private UserDaoI userDao;
	private WhesDaoI whesDao;
	private CompanyDaoI companyDao;
	
	public CompanyDaoI getCompanyDao() {
		return companyDao;
	}
	public void setCompanyDao(CompanyDaoI companyDao) {
		this.companyDao = companyDao;
	}
	public WhesDaoI getWhesDao() {
		return whesDao;
	}
	@Autowired
	public void setWhesDao(WhesDaoI whesDao) {
		this.whesDao = whesDao;
	}
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	public MemoDaoI getMemoDao() {
		return memoDao;
	}
	@Autowired
	public void setMemoDao(MemoDaoI memoDao) {
		this.memoDao = memoDao;
	}
	public WhesdtlDaoI getWhesdtlDao() {
		return whesdtlDao;
	}
	@Autowired
	public void setWhesdtlDao(WhesdtlDaoI whesdtlDao) {
		this.whesdtlDao = whesdtlDao;
	}

	/** 
	 * 查找所有vehicleMemo
	 */
	@Override
	public DataGrid findAllVehicleMemo(Pmemo pmemo) {
		String hql = "select m.id as id, m.vin as vin, m.booknum as booknum, m.users as users, m.content as content, m.memodate as memodate, b.sealnum as sealnum, b.connum as connum from Memo as m left join Booknum as b on m.booknumId = b.id where m.types = '1'";
		String hqlCount ="select count(*) from Memo as m left join Booknum as b on m.booknumId = b.id where m.types = '1'";
		
		if(null != pmemo.getUoo() && !pmemo.getUoo().isEmpty()){
			hql = hql+(" and m.uoo like '%"+pmemo.getUoo()+"%'");
		}
		if(null != pmemo.getBookNum() && !pmemo.getBookNum().isEmpty()){
			hql = hql+(" and m.booknum like '%"+pmemo.getBookNum()+"%'");
		}
		if(null != pmemo.getVin() && !pmemo.getVin().isEmpty()){
			hql = hql+(" and m.vin like '%"+pmemo.getVin()+"%'");
		}
		if(null != pmemo.getSort() && !pmemo.getSort().isEmpty() && null != pmemo.getOrder() && !pmemo.getOrder().isEmpty()){
			hql = hql+(" order by" + " " + pmemo.getSort() + " " + pmemo.getOrder());
		}

		List<Pmemo> vehicleMemo = whesdtlDao.findMemowithbooknum(hql, pmemo.getPage(), pmemo.getRows());
		
		DataGrid d = new DataGrid();
		d.setRows(vehicleMemo);
		d.setTotal(whesdtlDao.findMemowithCount(hqlCount));
		return d;
	}
	
	/** 
	 * 查找所有UOOMemo
	 */
	@Override
	public DataGrid findAllUooMemo(Pmemo pmemo) {
		String hql = "from Memo where types = '2'";
		String hqlCount ="select count(*)"+ hql;
		
		if(null != pmemo.getUoo() && !pmemo.getUoo().isEmpty()){
			hql = hql+(" and uoo like '%"+pmemo.getUoo()+"%'");
		}
		if(null != pmemo.getBookNum() && !pmemo.getBookNum().isEmpty()){
			hql = hql+(" and booknum like '%"+pmemo.getBookNum()+"%'");
		}
		if(null != pmemo.getVin() && !pmemo.getVin().isEmpty()){
			hql = hql+(" and vin like '%"+pmemo.getVin()+"%'");
		}
		if(null != pmemo.getSort() && !pmemo.getSort().isEmpty() && null != pmemo.getOrder() && !pmemo.getOrder().isEmpty()){
			hql = hql+(" order by" + " " + pmemo.getSort() + " " + pmemo.getOrder());
		}
		List<Memo> uooMemo = memoDao.find(hql, pmemo.getPage(), pmemo.getRows());
		
		DataGrid d = new DataGrid();
		d.setRows(uooMemo);
		d.setTotal(memoDao.count(hqlCount));
		return d;
	}
	
	/** 
	 * 添加或更新车辆memo
	 * @return 
	 */
	@Override
	public String saveVehicleMemo(Pwhesdtl pwhesdtl) {
		Pmemo pmemo = new Pmemo();
		if(null != pwhesdtl.getMemoId() && !pwhesdtl.getMemoId().isEmpty()){
			pmemo.setContent(pwhesdtl.getMemo());
			Memo memo = memoDao.get("from Memo where id=:memoId", pwhesdtl);
			if(null != memo){
				hitaii.util.BeanUtils.copyProperties(pmemo, memo);
				memoDao.update(memo);
				return "success";
			}else{
				return "The vehicle memo is not exist.";
			}
		}else{
			pmemo.setId(UUID.randomUUID().toString());
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
			pmemo.setUsers(sessionInfo.toString());
			String userHql = "from Users where logname = '"+sessionInfo.toString()+"'";
			Users userOper = userDao.get(userHql);
			pmemo.setUsersId(userOper.getId());
			pmemo.setTypes("1");
			pmemo.setContent(pwhesdtl.getMemo());
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date); 
			pmemo.setMemodate(time);
			pmemo.setVin(pwhesdtl.getVin());
			pmemo.setWhesId(pwhesdtl.getWhesId());
			Memo memo = new Memo();
			BeanUtils.copyProperties(pmemo, memo);
			memoDao.save(memo);
			return "success";
		}
	}
	
	@Override
	public String deleteVehicleInfoById(Pmemo pmemo) {
		Memo memo = memoDao.get("from Memo where id=:id",pmemo);
		if(null != memo){
			memoDao.delete(memo);
			return "success";
		}else{
			return "The vehicle information is not exist.";
		}
	}
	
	@Override
	public List<Pmemo> findVehicleMemoByVin(Pmemo pmemo) {
		String memoHql = "from Memo where vin = '"+pmemo.getVin()+"' order by memodate desc";
		List<Memo> memos = memoDao.find(memoHql);
		List<Pmemo> pMemo = new ArrayList<Pmemo>();
		if (null != memos && memos.size() > 0) {
			for (Memo m : memos) {
				Pmemo pmemo1 = new Pmemo();
				BeanUtils.copyProperties(m, pmemo1);
				pMemo.add(pmemo1);
			}
		}
		return pMemo;
	}
	
	@Override
	public DataGrid findUooMemoByBooknumId(Pmemo pmemo) {
		DataGrid d = new DataGrid();
		String hql = "from Memo where booknumId= :booknumId";
		if(null != pmemo.getSort() && !pmemo.getSort().isEmpty() && null != pmemo.getOrder() && !pmemo.getOrder().isEmpty()){
			hql = hql+(" order by" + " " + pmemo.getSort() + " " + pmemo.getOrder());
		}
		List<Memo> memos = memoDao.find(hql, pmemo, pmemo.getPage(), pmemo.getRows());
		List<Pmemo> pMemo = new ArrayList<Pmemo>();
		if (null != memos && memos.size() > 0) {
			for (Memo m : memos) {
				Pmemo pmemo1 = new Pmemo();
				BeanUtils.copyProperties(m, pmemo1);
				pMemo.add(pmemo1);
			}
		}
		d.setRows(pMemo);
		d.setTotal(memoDao.count(("select count(*) from Memo where booknumId= :booknumId"), pmemo));
		return d;
	}
	
	@Override
	public String addBooknumMemo(Pbooknum booknum) {
		Pmemo pmemo = new Pmemo();
		if(null != booknum.getMemoId() && !booknum.getMemoId().isEmpty()){
			pmemo.setContent(booknum.getMemo());
			Memo memo = memoDao.get("from Memo where id=:memoId", booknum);
			if(null != memo){
				hitaii.util.BeanUtils.copyProperties(pmemo, memo);
				memoDao.update(memo);
				return "success";
			}else{
				return "The Booknum memo is not exist.";
			}
		}else{
			pmemo.setId(UUID.randomUUID().toString());
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
			pmemo.setUsers(sessionInfo.toString());
			String userHql = "from Users where logname = '"+sessionInfo.toString()+"'";
			Users userOper = userDao.get(userHql);
			pmemo.setUsersId(userOper.getId());
			pmemo.setTypes("2");
			pmemo.setContent(booknum.getMemo());
			Date date = new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date); 
			pmemo.setMemodate(time);
			pmemo.setBooknumId(booknum.getId());
			pmemo.setBooknum(booknum.getBooknum());
			pmemo.setUoo(booknum.getUoo());
			Memo memo = new Memo();
			BeanUtils.copyProperties(pmemo, memo);
			memoDao.save(memo);
			return "success";
		}
	}
	
	@Override
	public List<Memo> getMemoByVin(Whesdtl whesdtl) {
		List<Memo> memos = memoDao.find("from Memo where vin =:vin", whesdtl);
		return memos;
	}
	
	@Override
	public void updateMemo(List<Memo> memos, Pwhesdtl pwhesdtl) {
		for(Memo m : memos){
			Pmemo pmemo = new Pmemo();
			BeanUtils.copyProperties(m, pmemo);
			pmemo.setVin(pwhesdtl.getVin());
			pmemo.setWhesId(pwhesdtl.getWhesId());
			hitaii.util.BeanUtils.copyProperties(pmemo, m);
			memoDao.update(m);
		}
	}
}
