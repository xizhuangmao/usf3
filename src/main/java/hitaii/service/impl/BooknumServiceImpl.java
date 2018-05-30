package hitaii.service.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hitaii.dao.BooknumDaoI;
import hitaii.dao.CarrierDaoI;

import hitaii.dao.NvoccDaoI;
import hitaii.dao.UserDaoI;

import hitaii.dao.DockDaoI;

import hitaii.dao.CompanyDaoI;
import hitaii.dao.MemoDaoI;
import hitaii.dao.VesselDaoI;
import hitaii.dao.VoyageDaoI;
import hitaii.dao.WhesdtlDaoI;
import hitaii.model.Booknum;
import hitaii.model.Carrier;

import hitaii.model.Nvocc;

import hitaii.model.Dock;

import hitaii.model.Company;
import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.model.Vessel;

import hitaii.model.Voyage;

import hitaii.model.Whesdtl;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pbooknum;
import hitaii.pageModel.PcurrentStockReport;
import hitaii.pageModel.PdailyLoading;
import hitaii.pageModel.Pdock;
import hitaii.pageModel.Pvoyage;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.BooknumServiceI;
import hitaii.util.ExeclUtil;
import hitaii.util.WhesdtlUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("booknumService")
public class BooknumServiceImpl implements BooknumServiceI{
	
	private BooknumDaoI booknumDao;
	private CarrierDaoI carrierDao;
	private VesselDaoI vesselDao;
	private VoyageDaoI voyageDao;
	private UserDaoI userDao;
	private NvoccDaoI nvoccDao;
	private DockDaoI dockDao;
	private WhesdtlDaoI whesdtlDao;
	private MemoDaoI memoDao;
	private CompanyDaoI companyDao;
	
	public CompanyDaoI getCompanyDao() {
		return companyDao;
	}
	@Autowired
	public void setCompanyDao(CompanyDaoI companyDao) {
		this.companyDao = companyDao;
	}
	public MemoDaoI getMemoDao() {
		return memoDao;
	}
	@Autowired
	public void setMemoDao(MemoDaoI memoDao) {
		this.memoDao = memoDao;
	}
	public NvoccDaoI getNvoccDao() {
		return nvoccDao;
	}
	@Autowired
	public void setNvoccDao(NvoccDaoI nvoccDao) {
		this.nvoccDao = nvoccDao;
	}
	public VoyageDaoI getVoyageDao() {
		return voyageDao;
	}
	@Autowired
	public void setVoyageDao(VoyageDaoI voyageDao) {
		this.voyageDao = voyageDao;
	}
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}

	public DockDaoI getDockDao() {
		return dockDao;
	}
	@Autowired
	public void setDockDao(DockDaoI dockDao) {
		this.dockDao = dockDao;
	}

	public CarrierDaoI getCarrierDao() {
		return carrierDao;
	}
	@Autowired
	public void setCarrierDao(CarrierDaoI carrierDao) {
		this.carrierDao = carrierDao;
	}
	public VesselDaoI getVesselDao() {
		return vesselDao;
	}
	@Autowired
	public void setVesselDao(VesselDaoI vesselDao) {
		this.vesselDao = vesselDao;
	}
	public BooknumDaoI getBooknumDao() {
		return booknumDao;
	}
	@Autowired
	public void setBooknumDao(BooknumDaoI booknumDao) {
		this.booknumDao = booknumDao;
	}
	
	public WhesdtlDaoI getWhesdtlDao() {
		return whesdtlDao;
	}
	@Autowired
	public void setWhesdtlDao(WhesdtlDaoI whesdtlDao) {
		this.whesdtlDao = whesdtlDao;
	}
	@Override
	public List<Booknum> findBooknumSelect(Pbooknum booknum) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String hql = null;
		if(sessionInfo.getUsers().getTypes().equals("1")){
			hql = "from Booknum where voyageId = '"+booknum.getVoyageId()+"' and (uoo = '' or uoo is null) order by booknum";
		}else if(sessionInfo.getUsers().getTypes().equals("2")){
			Set<Company> companies = sessionInfo.getUsers().getCompanies();
			String companyId = "";
			if(null != companies){
				companyId = " ('oooo'";
				for(Company company : companies){
					companyId += ",'"+company.getId()+"'";
				}
				companyId += ")";
			}
			hql = "from Booknum where voyageId = '"+booknum.getVoyageId()+"' and companyId in " + companyId + " and (uoo = '' or uoo is null) order by booknum";
		}
		List<Booknum> booknums =booknumDao.find(hql);
		return booknums;
	}
	
	@Override
	public DataGrid findAllBookingNum(Pbooknum pageBooknum) {
		DataGrid d = new DataGrid();
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String sql = null;
		String count = null;
		if(sessionInfo.getUsers().getTypes().equals("1")){
			//第一次查询获取ordersId集合
			String sqlbooknum = " from Booknum as b where 1=1";
			count ="select count(*) "+ sqlbooknum;
			
			if(null != pageBooknum.getCarrierId() && !pageBooknum.getCarrierId().isEmpty()){
				String hql = "from Company where id = '"+pageBooknum.getCarrierId()+"'";
				Company company = companyDao.get(hql);
				if(null != company){
					sqlbooknum = sqlbooknum+(" and b.carrier = '"+company.getFullname()+"'");
					count = count+(" and b.carrier = '"+company.getFullname()+"'");
				}
			}
			if(null != pageBooknum.getVesselId() && !pageBooknum.getVesselId().isEmpty()){
				String hql = "from Vessel where id = '"+pageBooknum.getVesselId()+"'";
				Vessel vessel = vesselDao.get(hql);
				if(null != vessel){
					sqlbooknum = sqlbooknum+(" and b.vessel = '"+vessel.getVessel()+"'");
					count = count+(" and b.vessel = '"+vessel.getVessel()+"'");
				}
			}
			if(null != pageBooknum.getVoyageId() && !pageBooknum.getVoyageId().isEmpty()){
				String hql = "from Voyage where id = '"+pageBooknum.getVoyageId()+"'";
				Voyage voyage = voyageDao.get(hql);
				sqlbooknum = sqlbooknum+(" and b.voyage = '"+voyage.getVoyage()+"'");
				count = count+(" and b.voyage = '"+voyage.getVoyage()+"'");
			}
			if(null != pageBooknum.getBooknum() && !pageBooknum.getBooknum().isEmpty()){
				sqlbooknum = sqlbooknum+(" and b.booknum like '%"+pageBooknum.getBooknum()+"%'");
				count = count+(" and b.booknum like '%"+pageBooknum.getBooknum()+"%'");
			}
			if(null != pageBooknum.getCompanyId() && !pageBooknum.getCompanyId().isEmpty()){
				sqlbooknum = sqlbooknum+(" and b.companyId = '"+pageBooknum.getCompanyId()+"'");
				count = count+(" and b.companyId = '"+pageBooknum.getCompanyId()+"'");
			}
			
			if(null != pageBooknum.getSort() && !pageBooknum.getSort().isEmpty() && null != pageBooknum.getOrder() && !pageBooknum.getOrder().isEmpty()){
				sqlbooknum = sqlbooknum + (" order by" + " " + pageBooknum.getSort() + " " + pageBooknum.getOrder());
			}
			List<Booknum> booknumList = booknumDao.find(sqlbooknum.toString(), pageBooknum.getPage(), pageBooknum.getRows());
			//组装ordersId字符串
			String sqlbooknumID ="";
			if(null!=booknumList){
				sqlbooknumID+=" and b.id  in ('0'";
				for(Booknum booknums :booknumList){
					sqlbooknumID+=",'"+booknums.getId()+"'";
				}
				sqlbooknumID+=") ";
			}
			
			//第二次查询，获取页面需要的车辆数据
			sql = " select w.booknumId as booknumId,b.id as id,b.booknum as booknum,b.company as company,b.carrier as carrier,b.vessel as vessel, " +
					" b.voyage as voyage,b.users as users,b.pod as pod,v.cutoffdate as cutoffdate,w.vin as vin,b.loaddate as loaddate" +
					" from Booknum b left join Whesdtl w on w.booknumId=b.id left join Voyage v on b.voyageId = v.id where 1=1 " +sqlbooknumID+"";
	
			sql = sql + " order by b.id desc";
			d.setTotal(booknumDao.count(count));
			
		}else if(sessionInfo.getUsers().getTypes().equals("2")){
			Set<Company> companies = sessionInfo.getUsers().getCompanies();
			String companyId = "";
			if(null != companies){
				companyId = " ('oooo'";
				for(Company company : companies){
					companyId += ",'"+company.getId()+"'";
				}
				companyId += ")";
			}
			
			String orderHql = " select w.booknumId as booknumId,b.id as id,b.booknum as booknum,b.company as company,b.carrier as carrier,b.vessel as vessel, " +
					" b.voyage as voyage,b.users as users,b.pod as pod,v.cutoffdate as cutoffdate,w.vin as vin,b.loaddate as loaddate" +
					" from Booknum b left join Whesdtl w on w.booknumId=b.id left join Voyage v on b.voyageId = v.id where b.companyId in " + companyId;
			count = "select count(DISTINCT b.id) from Booknum b left join Whesdtl w on w.booknumId=b.id left join Voyage v on b.voyageId = v.id where b.companyId in " + companyId;
			if(null != pageBooknum.getCarrierId() && !pageBooknum.getCarrierId().isEmpty()){
				String hql = "from Company where id = '"+pageBooknum.getCarrierId()+"'";
				Company company = companyDao.get(hql);
				if(null != company){
					orderHql = orderHql+(" and b.carrier = '"+company.getFullname()+"'");
					count = count+(" and b.carrier = '"+company.getFullname()+"'");
				}
			}
			if(null != pageBooknum.getVesselId() && !pageBooknum.getVesselId().isEmpty()){
				String hql = "from Vessel where id = '"+pageBooknum.getVesselId()+"'";
				Vessel vessel = vesselDao.get(hql);
				if(null != vessel){
					orderHql = orderHql+(" and b.vessel = '"+vessel.getVessel()+"'");
					count = count+(" and b.vessel = '"+vessel.getVessel()+"'");
				}
			}
			if(null != pageBooknum.getVoyageId() && !pageBooknum.getVoyageId().isEmpty()){
				String hql = "from Voyage where id = '"+pageBooknum.getVoyageId()+"'";
				Voyage voyage = voyageDao.get(hql);
				orderHql = orderHql+(" and b.voyage = '"+voyage.getVoyage()+"'");
				count = count+(" and b.voyage = '"+voyage.getVoyage()+"'");
			}
			if(null != pageBooknum.getBooknum() && !pageBooknum.getBooknum().isEmpty()){
				orderHql = orderHql+(" and b.booknum like '%"+pageBooknum.getBooknum()+"%'");
				count = count+(" and b.booknum like '%"+pageBooknum.getBooknum()+"%'");
			}
			if(null != pageBooknum.getCompanyId() && !pageBooknum.getCompanyId().isEmpty()){
				orderHql = orderHql+(" and b.companyId = '"+pageBooknum.getCompanyId()+"'");
				count = count+(" and b.companyId = '"+pageBooknum.getCompanyId()+"'");
			}
			
			if(null != pageBooknum.getSort() && !pageBooknum.getSort().isEmpty() && null != pageBooknum.getOrder() && !pageBooknum.getOrder().isEmpty()){
				sql = sql + (" order by" + " " + pageBooknum.getSort() + " " + pageBooknum.getOrder());
			}
			orderHql = orderHql + "group by b.id";
			List<Pbooknum> pbooknums =new ArrayList<Pbooknum>();
			pbooknums = whesdtlDao.findWithWhesdtl(orderHql, pageBooknum.getPage(), pageBooknum.getRows());
			String sqlbooknumID ="";
			if(null!=pbooknums){
				sqlbooknumID+=" and b.id in ('00000'";
				for(Pbooknum bPbooknum :pbooknums){
					sqlbooknumID+=",'"+bPbooknum.getId()+"'";
				}
				sqlbooknumID+=") ";
			}
			
			sql = " select w.booknumId as booknumId,b.id as id,b.booknum as booknum,b.company as company,b.carrier as carrier,b.vessel as vessel, " +
					" b.voyage as voyage,b.users as users,b.pod as pod,v.cutoffdate as cutoffdate,w.vin as vin,b.loaddate as loaddate" +
					" from Booknum b left join Whesdtl w on w.booknumId=b.id left join Voyage v on b.voyageId = v.id where b.companyId in " + companyId + " " + sqlbooknumID;
			
			sql = sql + " order by b.id desc";
			d.setTotal(whesdtlDao.findCount(count));
		}
		//查询结果
		List<Pbooknum> oList =new ArrayList<Pbooknum>();
		oList = whesdtlDao.findWithWhesdtl(sql.toString(), 0, 0);
		for(Pbooknum b : oList){
			List<Whesdtl> whesdtls = whesdtlDao.findVehicleInfo("from Whesdtl where booknumId = '"+b.getId()+"'");
			if(null != whesdtls && whesdtls.size() > 0){
				b.setType("1");
			}
		}
		d.setRows(oList);
		return d;
	}
	
	@Override
	public Json findEditBookingNum(Pbooknum pageBooknum) {
		Json j = new Json();
		Booknum booknum = booknumDao.get("from Booknum where id =:id", pageBooknum);
		if(null != booknum){
			Pbooknum pbooknum = new Pbooknum();
			BeanUtils.copyProperties(booknum, pbooknum);
			Company company = companyDao.get("from Company where fullname =:carrier", booknum);
			Vessel vessel = vesselDao.get("from Vessel where vessel = '"+booknum.getVessel()+"' and carrierId = '"+company.getId()+"'");
			Voyage voyage = voyageDao.get("from Voyage where id = '"+booknum.getVoyageId()+"'");
			if(null != company){
				pbooknum.setCarrierId(company.getId());
			}
			if(null != vessel){
				pbooknum.setVesselId(vessel.getId());
			}
			if(null != voyage){
				pbooknum.setCutoffdate(voyage.getCutoffdate());
			}
			j.setMsg("success");
			j.setSuccess(true);
			j.setObj(pbooknum);
		}else{
			j.setMsg("booknum is not exist");
		}
		return j;
	}
	
	@Override
	public boolean saveOrUpdateBooknum(Booknum booknum) {
		if(null != booknum){
			booknumDao.saveOrUpdate(booknum);
			return true;
		}
		return false;
	}
	@Override
	public Pbooknum findEditBooknum(Pbooknum booknum) {
		if(null != booknum.getBooknum()&& !booknum.getBooknum().isEmpty()){
			String hql = "from Booknum where booknum = :booknum";
			Booknum b = booknumDao.get(hql,booknum);
			if(null != b){
				BeanUtils.copyProperties(b, booknum);
				return booknum;
			}
		}
		return null;
	}
	
	@Override
	public boolean editTruckEditSubmit(Pbooknum booknum){
		if(null==booknum){
			return false;
		}
		Booknum b = new Booknum();
		BeanUtils.copyProperties(booknum , b);
		booknumDao.saveOrUpdate(b);
		return true;
	}
	@Override
	public boolean UpdateWhesdtlBooknum(Pbooknum pbooknum) {
		if(null == pbooknum){
			return false;
		}
		Company company = companyDao.get("from Company where id = '"+pbooknum.getCarrierId()+"'");
		if(null != company){
			pbooknum.setCarrier(company.getFullname());
		}
		Vessel vessel = vesselDao.get("from Vessel where id = '"+pbooknum.getVesselId()+"'");
		if(null != vessel){
			pbooknum.setVessel(vessel.getVessel());
		}
		Voyage voyage = voyageDao.get("from Voyage where id = '"+pbooknum.getVoyageId()+"'");
		if(null != voyage){
			pbooknum.setVoyage(voyage.getVoyage());
		}
		Company truck = companyDao.get("from Company where id = '"+pbooknum.getTruckId()+"'");
		if(null != truck){
			pbooknum.setTruck(truck.getFullname());
		}
		pbooknum.setEntrydate(WhesdtlUtil.getNowTime());
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String userHql = "from Users where logname = '"+sessionInfo.getUsers().getLogname()+"'";
		Users user = userDao.get(userHql);
		if(null != user){
			pbooknum.setUsersId(user.getId());
			pbooknum.setUsers(user.getFullname());
			pbooknum.setTruckusersId(user.getId());
			pbooknum.setTruckusers(user.getFullname());
		}
		pbooknum.setActive("1");
		Booknum booknum = booknumDao.get("from Booknum where id =:id", pbooknum);
		if(null != booknum){
			hitaii.util.BeanUtils.copyProperties(pbooknum , booknum);
			booknumDao.update(booknum);
			String sql = "update Whesdtl set booknumId = '"+pbooknum.getId()+"' where vin in "+pbooknum.getWhesdtlVinJSONs();
			whesdtlDao.updateSomeWhesdtlBooknumId(sql);
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<PdailyLoading> getDailyLoadingScheduleDate(Pwhesdtl pwhesdtl) {
		if(null==pwhesdtl.getLoadingFrom() || "".equals(pwhesdtl.getLoadingFrom())){
			String loadingFromDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
			pwhesdtl.setLoadingFrom(loadingFromDate);
		}
		if(null==pwhesdtl.getLoadingTo()|| "".equals(pwhesdtl.getLoadingTo())){
			String loadingToDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
			pwhesdtl.setLoadingTo(loadingToDate);
		}
		boolean isCompare = WhesdtlUtil.compare_date(pwhesdtl.getLoadingTo(),pwhesdtl.getLoadingFrom());
		if(isCompare){
			return null;
		}
		String sql =" select b.id as booknumId,b.booknum as booknum, b.consize as consize,b.connum as connum,b.sealnum as sealnum,b.uoo as uoo,b.loaddate as loaddate," +
				"w.id as id,w.usersId as usersId,w.users as users,w.vin as vin,w.make as make,w.model as model,w.year as year,w.color as color,w.indate as indate,w.fuel as fuel,w.fuelType as fuelType " +
					" from Whesdtl w,Booknum b  where w.booknumId = b.id and b.loaddate>= '"+pwhesdtl.getLoadingFrom()+"' and b.loaddate<= '"+pwhesdtl.getLoadingTo()+"' " ;
		if(null!=pwhesdtl.getUsersId() && !pwhesdtl.getUsersId().isEmpty()){
			sql = sql + " and w.usersId = '"+pwhesdtl.getUsersId()+"'";
		}
		sql = sql +" order by b.loaddate,b.booknum";
		List<PdailyLoading> dailyLoadings = whesdtlDao.getPdailyLoadingDate(sql);
		return dailyLoadings;
	}
	
	@Override
	public List<PcurrentStockReport> getCurrentStockReport(Pwhesdtl pwhesdtl) {
		String date="";
		if(pwhesdtl==null || null==pwhesdtl.getLoaddate() ||"".equals(pwhesdtl.getLoaddate())){
			date = WhesdtlUtil.getNowTime();
		}else{
			date = pwhesdtl.getLoaddate();
		}
		String sql = "from Whesdtl w, Booknum b where w.indate != '' and w.indate < '"+date+"' and w.booknumId=b.id and (b.loaddate = '' or b.loaddate is null or b.loaddate > '"+date+"') and w.users is not null";
		String head = "select w.id as id,w.usersId as usersId,w.users as users,w.indate as indate,w.year as year, w.make as make,w.model as model,w.color as color,w.vin as vin,w.engine as engine,w.keynum as keynum,w.sticker as sticker,w.note as note ";
		sql = head + sql;
		if(null==pwhesdtl.getUsersId() ||"".equals(pwhesdtl.getUsersId())){
		}else{
			sql = sql + " and w.usersId = '"+pwhesdtl.getUsersId()+"'";
		}
		sql = sql+" order by w.usersId asc, w.indate desc";
		List<PcurrentStockReport> currentStockReports=whesdtlDao.getCurrentStockReport(sql);
		return currentStockReports;
	}
	@Override
	public List<PcurrentStockReport> getReceivedCarsReport(Pwhesdtl pwhesdtl) {
		if(pwhesdtl==null ){
			return null;
		}
		String fromDate="";
		String toDate="";
		if(null==pwhesdtl.getLoadingFrom() || "".equals(pwhesdtl.getLoadingFrom())){
			fromDate = WhesdtlUtil.getNowTime();
		}else{
			fromDate = pwhesdtl.getLoadingFrom(); 
		}
		if(null==pwhesdtl.getLoadingTo() || "".equals(pwhesdtl.getLoadingTo())){
			toDate = WhesdtlUtil.getNowTime();
		}else{
			toDate = pwhesdtl.getLoadingTo(); 
		}
		String sql = "from Whesdtl w, Booknum b where w.booknumId=b.id and w.indate >= '"+fromDate+"' and w.indate <= '"+toDate+"'";
		//String sql = "from Whesdtl w, Booknum b where w.booknumId=b.id and w.indate >= '2015-12-01' and w.indate <= '2016-01-29'";
		String head = "select w.id as id,w.usersId as usersId,w.users as users,w.indate as indate,w.year as year, w.make as make,w.model as model,w.color as color,w.vin as vin,w.engine as engine,w.keynum as keynum,w.sticker as sticker,w.note as note,b.loaddate as loaddate,b.booknum as booknum,b.connum as connum ";
		sql = head + sql;
		if(null==pwhesdtl.getUsersId() ||"".equals(pwhesdtl.getUsersId())){
		}else{
			sql =sql+ " and w.usersId = '"+pwhesdtl.getUsersId()+"'";
		}
		sql = sql+" order by w.indate asc ";
		
		List<PcurrentStockReport> currentStockReports=whesdtlDao.getCurrentStockReport(sql);
		if(currentStockReports.size()>0){
			return currentStockReports;
		}
			return currentStockReports;
	}
	
	@Override
	public HSSFWorkbook getReceivedCarsReportHSSFWorkbook(Pwhesdtl pwhesdtl) {
		if(null==pwhesdtl.getLoadingFrom() || "".equals(pwhesdtl.getLoadingFrom())){
			pwhesdtl.setLoadingFrom(WhesdtlUtil.getNowTime());
		}
		if(null==pwhesdtl.getLoadingTo() || "".equals(pwhesdtl.getLoadingTo())){
			pwhesdtl.setLoadingTo(WhesdtlUtil.getNowTime());
		}
		List<PcurrentStockReport> currentStockReports = getReceivedCarsReport(pwhesdtl);
		HSSFWorkbook hssfworkbook = ExeclUtil.ReceivedCarsReportExcel(currentStockReports, pwhesdtl.getLoadingFrom(), pwhesdtl.getLoadingTo());
		return hssfworkbook;
	}
	
	@Override
	public void deleteBookingNumById(String bookingNumId) {
		String hql = "from Booknum where id = '"+bookingNumId+"'";
		Booknum booknum = booknumDao.get(hql);
		booknumDao.delete(booknum);
	}

	@Override
	public Json updateBookingNum(Pbooknum pageBooknum) {
		Json j = new Json();
		Booknum booknum = booknumDao.get("from Booknum where id =:id", pageBooknum);
		if(null != booknum){
			Company carrierCompany = companyDao.get("from Company where id =:carrierId", pageBooknum);
			if(null != carrierCompany){
				pageBooknum.setCarrier(carrierCompany.getFullname());
			}
			Company company = companyDao.get("from Company where id =:companyId", pageBooknum);
			if(null != company){
				pageBooknum.setCompany(company.getFullname());
			}
			Vessel vessel = vesselDao.get("from Vessel where id =:vesselId", pageBooknum);
			if(null != vessel){
				pageBooknum.setVessel(vessel.getVessel());
			}
			Voyage voyage = voyageDao.get("from Voyage where id =:voyageId", pageBooknum);
			if(null != voyage){
				pageBooknum.setVoyage(voyage.getVoyage());
			}

		}
		hitaii.util.BeanUtils.copyProperties(pageBooknum, booknum);
		try {
			booknumDao.update(booknum);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@Override
	public Pdock SearchUooDockReceipt(Pbooknum pbooknum) {
		if(null==pbooknum.getUoo() || "".equals(pbooknum.getUoo())){
			return null;
		}
		Pdock pdock = new Pdock();
		String dockHql = "from Dock where uoo =:uoo";
		Dock dock = dockDao.get(dockHql,pbooknum);
		if(dock != null){
			BeanUtils.copyProperties(dock , pdock);
		}
		String booknumHql = "from Booknum where uoo =:uoo";
		Booknum booknum = booknumDao.get(booknumHql,pbooknum);
		if(booknum != null){
			pdock.setVessel(booknum.getVessel()+" , "+booknum.getVoyage());
			pdock.setConnum(booknum.getConnum());
			pdock.setSealnum(booknum.getSealnum());
			pbooknum.setId(booknum.getId());
		}else{
			return null;
		}
		String whesdtlHql = "from Whesdtl where booknumId = :id";
		List<Whesdtl> whesdtls = whesdtlDao.findW(whesdtlHql, pbooknum);
		List<String> list = new ArrayList<String>();
		if(whesdtls.size()>0){
			for(Whesdtl whesdtl :whesdtls){
				String whesdtlList = whesdtl.getYear()+" "+whesdtl.getMake()+" "+whesdtl.getModel()+",VIN:"+whesdtl.getVin();
				list.add(whesdtlList);
			}
			pdock.setWhesdtlList(list);
			return pdock;
		}
		
		return null;
	}
	
	@Override
	public Json addBooknum(Pbooknum pbooknum, Users user) {
		Json j = new Json();
		pbooknum.setUsersId(user.getId());
		pbooknum.setUsers(user.getFullname());
		pbooknum.setId(WhesdtlUtil.getUUID());
		pbooknum.setActive("0");
		
		Company company = companyDao.get("from Company where id =:companyId", pbooknum);
		pbooknum.setCompany(company.getFullname());
		Booknum booknum = new Booknum();
		BeanUtils.copyProperties(pbooknum , booknum);
		try {
			booknumDao.save(booknum);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public List<Pwhesdtl> findBooknumById(Pbooknum booknum) {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		String hql = null;
		if(null != user){
			if(user.getTypes().equals("1")){
				hql = " from Whesdtl as w, Orders as o, Booknum as b where w.ordersId is not null and w.booknumId = '"+booknum.getId()+"' and b.uoo is not null and w.ordersId=o.id and w.booknumId=b.id";
			}else if(user.getTypes().equals("2")){
				Set<Company> whes =user.getCompanies();
				String whesId ="";
				if(null != whes){
					whesId = " ('oo'";
						for(Company wh : whes){
							whesId+=",'"+wh.getId()+"'";
						}
					whesId += ")";
				}
				hql = " from Whesdtl as w, Orders as o, Booknum as b where w.ordersId is not null and w.booknumId = '"+booknum.getId()+"' and b.uoo is not null and w.ordersId=o.id and w.booknumId=b.id and w.whesId in" + " " + whesId;
			}
		}
		
		List<Pwhesdtl> whesdtls = whesdtlDao.findWithOrders(hql);
		return whesdtls;
	}
	
	@Override
	public Booknum findBooknumUooById(Pbooknum booknum) {
		return booknumDao.get("from Booknum where id= :id", booknum);
	}
	
	@Override
	public Booknum getBooknum(Pbooknum booknum) {
		if(null != booknum.getId() && !booknum.getId().isEmpty()){
			return booknumDao.get("from Booknum where booknum=:booknum and id !=:id and voyageId =:voyageId", booknum);
		}else{
			return booknumDao.get("from Booknum where booknum=:booknum and voyageId =:voyageId", booknum);
		}
		
	}
	
	@Override
	public String updateBooknum(Pbooknum booknum) {
		Booknum booknum2 = booknumDao.get("from Booknum where id=:booknumId", booknum);
		Company company = companyDao.get("from Company where id =:truckId", booknum);
		if(null != company){
			booknum.setTruck(company.getFullname());
		}
		hitaii.util.BeanUtils.copyProperties(booknum, booknum2);
		booknumDao.update(booknum2);
		String hql = "from Whesdtl where vin in "+booknum.getWhesdtlVinJSONs()+"";
		List<Whesdtl> whesdtls = whesdtlDao.findVehicleInfo(hql);
		for(int i=0;i<whesdtls.size();i++){
			whesdtls.get(i).setBooknumId(booknum.getBooknumId());
		}
		return "success";
	}
	
	@Override
	public boolean deleteBooknum(Pbooknum booknum) {
		String booknumHql = "from Booknum where id = '"+booknum.getId()+"'";
		Booknum booknumUoo = booknumDao.get(booknumHql);
		
		String hql = "from Whesdtl where booknumId = '"+booknum.getId()+"'";
		List<Whesdtl> whesdtls = whesdtlDao.findVehicleInfo(hql);
		if(whesdtls.size()>0 && null != booknumUoo){
			for(int i=0;i<whesdtls.size();i++){
				whesdtls.get(i).setBooknumId(null);
			}
			booknumUoo.setUoo(null);
			booknumUoo.setLoaddate(null);
			booknumUoo.setConsize(null);
			booknumUoo.setConnum(null);
			booknumUoo.setSealnum(null);
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public List<Booknum> findBooknumByVoyageId(Pvoyage voyage) {
		return booknumDao.find("from Booknum where voyageId =:id", voyage);
	}
}
