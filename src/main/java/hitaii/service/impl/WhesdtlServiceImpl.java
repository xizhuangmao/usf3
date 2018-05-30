package hitaii.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.BooknumDaoI;
import hitaii.dao.CompanyDaoI;
import hitaii.dao.CustomerDaoI;
import hitaii.dao.MakeDaoI;
import hitaii.dao.MemoDaoI;
import hitaii.dao.OrdersDaoI;
import hitaii.dao.PicDaoI;
import hitaii.dao.ServicesDaoI;
import hitaii.dao.UserDaoI;
import hitaii.dao.WhesDaoI;
import hitaii.dao.WhesdtlDaoI;
import hitaii.dao.WhesdtlServicesDaoI;
import hitaii.model.Carrier;
import hitaii.model.Company;
import hitaii.model.Make;
import hitaii.model.Memo;
import hitaii.model.Pic;
import hitaii.model.Booknum;
import hitaii.model.Services;
import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.model.Whes;
import hitaii.model.WhesdtlServices;

import hitaii.model.Whesdtl;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pmemo;
import hitaii.pageModel.Ppic;
import hitaii.pageModel.Pservices;
import hitaii.pageModel.Puser;
import hitaii.pageModel.Pwhesdtl;

import hitaii.service.WhesdtlServiceI;
import hitaii.util.WhesdtlUtil;

@Service("whesdtlService")
public class WhesdtlServiceImpl implements WhesdtlServiceI {
	
	private WhesdtlDaoI whesdtlDao;

	private OrdersDaoI ordersDao;
	private UserDaoI userDao;
	private MakeDaoI makeDao;
	private CustomerDaoI customerDao;
	private PicDaoI picDao;
	private WhesDaoI whesDao;
	private MemoDaoI memoDao;
	private ServicesDaoI servicesDao;
	private WhesdtlServicesDaoI whesdtlServicesDao;
	private CompanyDaoI companyDao;
	
	public static File dirFrom;   
	public static File dirTo;  
	
	public CompanyDaoI getCompanyDao() {
		return companyDao;
	}
	@Autowired
	public void setCompanyDao(CompanyDaoI companyDao) {
		this.companyDao = companyDao;
	}
	public WhesdtlServicesDaoI getWhesdtlServicesDao() {
		return whesdtlServicesDao;
	}
	@Autowired
	public void setWhesdtlServicesDao(WhesdtlServicesDaoI whesdtlServicesDao) {
		this.whesdtlServicesDao = whesdtlServicesDao;
	}
	public ServicesDaoI getServicesDao() {
		return servicesDao;
	}
	@Autowired
	public void setServicesDao(ServicesDaoI servicesDao) {
		this.servicesDao = servicesDao;
	}
	public MemoDaoI getMemoDao() {
		return memoDao;
	}
	@Autowired
	public void setMemoDao(MemoDaoI memoDao) {
		this.memoDao = memoDao;
	}
	public WhesDaoI getWhesDao() {
		return whesDao;
	}
	@Autowired
	public void setWhesDao(WhesDaoI whesDao) {
		this.whesDao = whesDao;
	}
	public PicDaoI getPicDao() {
		return picDao;
	}
	@Autowired
	public void setPicDao(PicDaoI picDao) {
		this.picDao = picDao;
	}
	public CustomerDaoI getCustomerDao() {
		return customerDao;
	}
	@Autowired
	public void setCustomerDao(CustomerDaoI customerDao) {
		this.customerDao = customerDao;
	}
	public MakeDaoI getMakeDao() {
		return makeDao;
	}
	@Autowired
	public void setMakeDao(MakeDaoI makeDao) {
		this.makeDao = makeDao;
	}
	public OrdersDaoI getOrdersDao() {
		return ordersDao;
	}
	private BooknumDaoI booknumDao;
	
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
	
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public DataGrid getAlertWhesdtlDatagrid(Pwhesdtl pwhesdtl, String type) throws IOException {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		DataGrid d = new DataGrid();
		String sqlCount = "";
		String sql;
		String count;
		if(!sessionInfo.getUsers().getTypes().equals("1")){
			Set<Company> companies = sessionInfo.getUsers().getCompanies();
			String companyId = "";
			if(null != companies){
				companyId = " ('oooo'";
				for(Company company : companies){
					companyId += ",'"+company.getId()+"'";
				}
				companyId += ")";
			}
			if(null != type && type.equals("noOrders")){
				sql = "select w.id,w.pic,w.vin,w.make,w.model,w.year,w.color,w.engine,w.keynum,w.users,w.sticker,w.indate," +
						"b.booknum,w.titlestate,w.billstate,w.proofstate,w.note,b.connum,b.loaddate,o.ordersdate from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.flowstate = '2' and (w.booknumId is null or w.booknumId = '') and w.whesId in " + companyId;
				count ="select count(*) from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.flowstate = '2' and (w.booknumId is null or w.booknumId = '') and w.whesId in " + companyId;
			}else{
				sql = "select w.id,w.pic,w.vin,w.make,w.model,w.year,w.color,w.engine,w.keynum,w.users,w.sticker,w.indate," +
						"b.booknum,w.titlestate,w.billstate,w.proofstate,w.note,b.connum,b.loaddate,o.ordersdate from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.flowstate = '2' and w.whesId in " + companyId;
				count ="select count(*) from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.flowstate = '2' and w.whesId in " + companyId;
			}
			
		}else{
			sql = "select w.id,w.pic,w.vin,w.make,w.model,w.year,w.color,w.engine,w.keynum,w.users,w.sticker,w.indate," +
					"b.booknum,w.titlestate,w.billstate,w.proofstate,w.note,b.connum,b.loaddate,o.ordersdate from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.flowstate = '2'";
			count ="select count(*) from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.flowstate = '2'";
		}
		if(null != pwhesdtl.getUsers() && !pwhesdtl.getUsers().isEmpty() && !pwhesdtl.getUsers().equals("ALL")){
			sqlCount = sqlCount+(" and w.users = '"+pwhesdtl.getUsers()+"'");
		}
		if(null != pwhesdtl.getVin() && !pwhesdtl.getVin().isEmpty()){
			sqlCount = sqlCount+(" and w.vin like '%"+pwhesdtl.getVin()+"%'");
		}
		if(null != pwhesdtl.getYear() && !pwhesdtl.getYear().isEmpty()){
			sqlCount = sqlCount+(" and w.year = '"+pwhesdtl.getYear()+"'");
		}
		if(null != pwhesdtl.getWhes() && !pwhesdtl.getWhes().isEmpty() && !pwhesdtl.getWhes().equals("ALL")){
			sqlCount = sqlCount+(" and w.whes = '"+pwhesdtl.getWhes()+"'");
		}
		String hqlMake = "from Make where id = '"+pwhesdtl.getMake()+"'";
		Make make = makeDao.get(hqlMake);
		if(null != pwhesdtl.getMake() && !pwhesdtl.getMake().isEmpty()){
			sqlCount = sqlCount+(" and w.make = '"+make.getMake()+"'");
		}
		if(null != pwhesdtl.getModel() && !pwhesdtl.getModel().isEmpty()){
			sqlCount = sqlCount+(" and w.model = '"+pwhesdtl.getModel()+"'");
		}
		if(null != pwhesdtl.getColor() && !pwhesdtl.getColor().isEmpty()){
			sqlCount = sqlCount+(" and w.color like '%"+pwhesdtl.getColor()+"%'");
		}
		if(null != pwhesdtl.getBooknum() && !pwhesdtl.getBooknum().isEmpty()){
			sqlCount = sqlCount+(" and b.booknum like '%"+pwhesdtl.getBooknum()+"%'");
		}
		if(null != pwhesdtl.getConsize() && !pwhesdtl.getConsize().isEmpty()){
			sqlCount = sqlCount+(" and b.consize like '%"+pwhesdtl.getConsize()+"%'");
		}
		if(null != pwhesdtl.getInWareFrom() && !pwhesdtl.getInWareFrom().isEmpty() 
				|| null != pwhesdtl.getInWareTO() && !pwhesdtl.getInWareTO().isEmpty()){
			if(null == pwhesdtl.getInWareFrom()){
				pwhesdtl.setInWareFrom("");
			}
			sqlCount = sqlCount+(" and w.indate between '"+pwhesdtl.getInWareFrom()+"' and '"+pwhesdtl.getInWareTO()+"'");
		}
		if(null != pwhesdtl.getLoadingFrom() && !pwhesdtl.getLoadingFrom().isEmpty()
				|| null != pwhesdtl.getLoadingTo() && !pwhesdtl.getLoadingTo().isEmpty()){
			if(null == pwhesdtl.getLoadingFrom()){
				pwhesdtl.setLoadingFrom("");
			}
			sqlCount = sqlCount+(" and b.loaddate between '"+pwhesdtl.getLoadingFrom()+"' and '"+pwhesdtl.getLoadingTo()+"'");
		}
		if(null != pwhesdtl.getLoaddate() && !pwhesdtl.getLoaddate().isEmpty()){
			if(pwhesdtl.getLoaddate().equals("1")){
				sqlCount = sqlCount+(" and b.loaddate is not null and b.loaddate != ''");
			}else if(pwhesdtl.getLoaddate().equals("2")){
				sqlCount = sqlCount+(" and b.loaddate is null or b.loaddate = ''");
			}
		}
		if(null != pwhesdtl.getOrderstate() && !pwhesdtl.getOrderstate().isEmpty()){
			if(pwhesdtl.getOrderstate().equals("1")){
				sqlCount = sqlCount+(" and o.ordersdate is not null and o.ordersdate != ''");
			}else if(pwhesdtl.getOrderstate().equals("2")){
				sqlCount = sqlCount+(" and o.ordersdate is null or o.ordersdate = ''");
			}
		}
		if(null != pwhesdtl.getSort() && !pwhesdtl.getSort().isEmpty() && null != pwhesdtl.getOrder() && !pwhesdtl.getOrder().isEmpty()){
			if(pwhesdtl.getSort().equals("users")){
				sqlCount = sqlCount+(" order by" + " " + "w."+pwhesdtl.getSort() + " " + pwhesdtl.getOrder());
			}else{
				sqlCount = sqlCount+(" order by" + " " + pwhesdtl.getSort() + " " + pwhesdtl.getOrder());
			}
		}
		sql = sql + sqlCount;
		count = count + sqlCount; 
		List<Pwhesdtl> whesdtls = whesdtlDao.findwithbooknum(sql.toString(), pwhesdtl.getPage(), pwhesdtl.getRows());
		d.setRows(whesdtls);
		d.setTotal(whesdtlDao.findCount(count));
		return d;
	}
	
	@Override
	public DataGrid getAlertWhesdtlNoOrdersCount(Pwhesdtl pwhesdtl) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		DataGrid d = new DataGrid();
		String count = null;
		if(!sessionInfo.getUsers().getTypes().equals("1")){
			Set<Company> companies = sessionInfo.getUsers().getCompanies();
			String companyId = "";
			if(null != companies){
				companyId = " ('oooo'";
				for(Company company : companies){
					companyId += ",'"+company.getId()+"'";
				}
				companyId += ")";
			}

			count ="select count(*) from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.flowstate = '2' and (w.booknumId is null or w.booknumId = '') and w.whesId in " + companyId;
		}
		d.setTotal(whesdtlDao.findCount(count));
		return d;
	}
	
	@Override
	public Long getCountPreAlertWhesdtl(){
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String count;
		if(sessionInfo.getUsers().getTypes().equals("1")){
			count ="select count(*) from Whesdtl as w where w.flowstate = '1'";
		}else{
			Set<Company> companies = sessionInfo.getUsers().getCompanies();
			String companyId = "";
			if(null != companies){
				companyId = " ('oooo'";
				for(Company company : companies){
					companyId += ",'"+company.getId()+"'";
				}
				companyId += ")";
			}
			count ="select count(*) from Whesdtl as w where w.flowstate = '1' and w.whesId in " + companyId;
		}
		return whesdtlDao.findCount(count);
	}
	
	@Override
	public DataGrid getPreAlertWhesdtl(Pwhesdtl pwhesdtl) throws IOException {
		DataGrid d = new DataGrid();
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String sql;
		String count;
		if(sessionInfo.getUsers().getTypes().equals("1")){
			sql = "from Whesdtl as w where w.flowstate = '1'";
			count ="select count(*) from Whesdtl as w where w.flowstate = '1'";
		}else{
			Set<Company> companies = sessionInfo.getUsers().getCompanies();
			String companyId = "";
			if(null != companies){
				companyId = " ('oooo'";
				for(Company company : companies){
					companyId += ",'"+company.getId()+"'";
				}
				companyId += ")";
			}
			sql = "from Whesdtl as w where w.flowstate = '1' and w.whesId in " + companyId;
			count ="select count(*) from Whesdtl as w where w.flowstate = '1' and w.whesId in " + companyId;
		}
		String sqlCount = "";
		if(null != pwhesdtl.getWhes() && !pwhesdtl.getWhes().isEmpty() && !pwhesdtl.getWhes().equals("ALL")){
			sqlCount = sqlCount+(" and w.whes = '"+pwhesdtl.getWhes()+"'");
		}
		if(null != pwhesdtl.getSort() && !pwhesdtl.getSort().isEmpty() && null != pwhesdtl.getOrder() && !pwhesdtl.getOrder().isEmpty()){
			sqlCount = sqlCount+(" order by" + " " + pwhesdtl.getSort() + " " + pwhesdtl.getOrder());
		}
		sql = sql + sqlCount;
		count = count + sqlCount; 
		List<Whesdtl> whesdtls = whesdtlDao.findPreAlert(sql.toString(), pwhesdtl.getPage(), pwhesdtl.getRows());
		List<Pwhesdtl> pwhesdtls = new ArrayList<Pwhesdtl>();
		if (null != whesdtls && whesdtls.size() > 0) {
			for (Whesdtl w : whesdtls) {
				Pwhesdtl pwhesdtl2 = new Pwhesdtl();
				BeanUtils.copyProperties(w, pwhesdtl2);
				pwhesdtls.add(pwhesdtl2);
			}
		}
		d.setRows(pwhesdtls);
		d.setTotal(whesdtlDao.findCount(count));
		return d;
	}
	
	public DataGrid getAllOrders(Pwhesdtl pwhesdtl) {
		DataGrid d = new DataGrid();
		String sql = " from Whesdtl as w ,Booknum as b  where w.booknumId = b.id";
		String count ="select count(*)"+ sql;
		if(null != pwhesdtl.getSort() && !pwhesdtl.getSort().isEmpty() && null != pwhesdtl.getOrder() && !pwhesdtl.getOrder().isEmpty()){
			sql=sql+(" order by" + " " + pwhesdtl.getSort() + " " + pwhesdtl.getOrder());
		}
		List<Pwhesdtl> whesdtls = whesdtlDao.findwithbooknum(sql.toString(), pwhesdtl.getPage(), pwhesdtl.getRows());
		d.setRows(whesdtls);
		d.setTotal(whesdtlDao.findwithCount(count));
		return d;
	}
	
	@Override
	public DataGrid findWhesdtlWithBooknum(Pwhesdtl pwhesdtl) {
		DataGrid d = new DataGrid();
		String sql = " from Whesdtl where booknum is null";
		String count ="select count(*)"+ sql;
		if(null != pwhesdtl.getSort() && !pwhesdtl.getSort().isEmpty() && null != pwhesdtl.getOrder() && !pwhesdtl.getOrder().isEmpty()){
			sql=sql+(" order by" + " " + pwhesdtl.getSort() + " " + pwhesdtl.getOrder());
		}
		List<Whesdtl> whesdtls = whesdtlDao.find(sql.toString(), pwhesdtl.getPage(), pwhesdtl.getRows());
		d.setRows(whesdtls);
		d.setTotal(whesdtlDao.findwithCount(count));
		return d;
	}
	
	@Override
	public DataGrid findWhesdtlNotUOODataGrid(Pwhesdtl whesdtl, Users user) {
		DataGrid d = new DataGrid();
		Set<Company> companies = user.getCompanies();
		Set<Carrier> carriers = user.getCarriers();
		Set<Whes> whes = user.getWheses();
		String companyId = "";
		if(null != companies){
			companyId = " ('oo'";
			for(Company company : companies){
				companyId += ",'"+company.getId()+"'";
			}
			companyId += ")";
		}
		String sql = " from Whesdtl as w, Orders as o  where w.ordersId is not null and (w.booknumId is null or w.booknumId = '') and w.ordersId=o.id ";
		
		if(null != user && !user.getTypes().equals("1")){
			sql = sql+(" and w.whesId in "+companyId+" and w.flowstate = '2'"); 
		}
		if(null != whesdtl.getVin() && !whesdtl.getVin().isEmpty()){
			sql = sql + " and w.vin= '"+whesdtl.getVin()+"'";
		}
		if(null != whesdtl.getSort() && !whesdtl.getSort().isEmpty() && null != whesdtl.getOrder() && !whesdtl.getOrder().isEmpty()){
			if(whesdtl.getSort().equals("vin")){
				sql=sql+" order by " + " w." + whesdtl.getSort() + " " + whesdtl.getOrder();
			}else if(whesdtl.getSort().equals("ordersdate")){
				sql=sql+" order by " + " o." + whesdtl.getSort() + " " + whesdtl.getOrder();
			}
		}
		
		List<Pwhesdtl> whesdtls=whesdtlDao.findWhesdtlNotUOO(sql, whesdtl.getPage(), whesdtl.getRows());

		d.setRows(whesdtls);
		
		String count ="select count(*)"+ sql;
		d.setTotal(whesdtlDao.findwithCount(count));
		return d;
	}

	@Override
	public DataGrid findValidOrdersDataGrid(Pwhesdtl whesdtl, Users user) {
		DataGrid d = new DataGrid();
		String count = null;
		String sql = null;
		//第一次查询获取BooknumId集合
		if(user.getTypes().equals("1")){
			String sqlorder = " from Booknum where uoo is not null ";
			sqlorder = addCondition(whesdtl, sqlorder);
			if(null != whesdtl.getSort() && !whesdtl.getSort().isEmpty() && null != whesdtl.getOrder() && !whesdtl.getOrder().isEmpty()){
				sqlorder=sqlorder+(" order by" + " " + whesdtl.getSort() + " " + whesdtl.getOrder());
			}
			count ="select count(*) "+ sqlorder;
			List<Booknum> booknums = booknumDao.find(sqlorder, whesdtl.getPage(), whesdtl.getRows());
			//组装ordersId字符串
					String sqlordersID ="";
					if(null!=booknums){
						sqlordersID+=" and b.id  in ('000000'";
						for(Booknum b :booknums){
							sqlordersID+=",'"+b.getId()+"'";
						}
						sqlordersID+=") ";
					}
			//第二次查询，获取页面需要的车辆数据
			sql = " select b.id as id, b.booknum as booknum, b.consize as consize,b.carrier as carrier,b.vessel as vessel,b.voyage as voyage," +
						 "b.connum as connum,b.sealnum as sealnum,b.uoo as uoo,b.truckdate as truckdate,b.truck as truck,b.loaddate as loaddate,w.users as users,w.vin as vin, " +
							"o.pod as pod,v.cutoffdate as cutoffdate,v.terminal as terminal" +
							" from Whesdtl w left join Orders o on w.ordersId = o.id left join Booknum b on w.booknumId = b.id left join Voyage v on b.voyageId=v.id where (b.id is not null and b.id != '') " +sqlordersID;		
			d.setTotal(booknumDao.count(count));
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
			String hql = " select b.id as id, b.booknum as booknum, b.consize as consize,b.carrier as carrier,b.vessel as vessel,b.voyage as voyage," +
					 "b.connum as connum,b.sealnum as sealnum,b.uoo as uoo,b.truckdate as truckdate,b.truck as truck,b.loaddate as loaddate,w.users as users,w.vin as vin, " +
						"o.pod as pod,v.cutoffdate as cutoffdate,v.terminal as terminal" +
						" from Whesdtl w left join Orders o on w.ordersId = o.id left join Booknum b on w.booknumId = b.id left join Voyage v on b.voyageId=v.id where (b.id is not null and b.id != '') and b.uoo is not null and whesId in "+whesId+" group by b.id";		
			count = "select count(DISTINCT b.id) from Whesdtl w left join Orders o on w.ordersId = o.id left join Booknum b on w.booknumId = b.id left join Voyage v on b.voyageId=v.id where (b.id is not null and b.id != '') and b.uoo is not null and whesId in "+whesId+"";
		
			List<Pwhesdtl> pwhesdtls =new ArrayList<Pwhesdtl>();
			pwhesdtls = whesdtlDao.findwithBooknumVoyageOrders(hql, whesdtl.getPage(), whesdtl.getRows());
			
			String sqlbooknumID ="";
			if(null!=pwhesdtls){
				sqlbooknumID+=" and b.id  in ('00000'";
				for(Pwhesdtl wPwhesdtl :pwhesdtls){
					sqlbooknumID+=",'"+wPwhesdtl.getId()+"'";
				}
				sqlbooknumID+=") ";
			}
			
			sql = " select b.id as id, b.booknum as booknum, b.consize as consize,b.carrier as carrier,b.vessel as vessel,b.voyage as voyage," +
					 "b.connum as connum,b.sealnum as sealnum,b.uoo as uoo,b.truckdate as truckdate,b.truck as truck,b.loaddate as loaddate,w.users as users,w.vin as vin, " +
						"o.pod as pod,v.cutoffdate as cutoffdate,v.terminal as terminal" +
						" from Whesdtl w left join Orders o on w.ordersId = o.id left join Booknum b on w.booknumId = b.id left join Voyage v on b.voyageId=v.id where (b.id is not null and b.id != '') and whesId in "+whesId+"" + sqlbooknumID;
			d.setTotal(whesdtlDao.findCount(count));
		}
		
		if(null !=whesdtl.getBooknum()&& !whesdtl.getBooknum().isEmpty()){
			sql = sql+" and b.booknum = '"+whesdtl.getBooknum()+"'";
		}
		if(null !=whesdtl.getCutoffFromDate()&& !whesdtl.getCutoffFromDate().isEmpty()){
			sql = sql+" and cutoffdate >= '"+whesdtl.getCutoffFromDate()+"'";
		}
		if(null !=whesdtl.getCutoffToDate()&& !whesdtl.getCutoffToDate().isEmpty()){
			sql = sql+" and cutoffdate <= '"+whesdtl.getCutoffToDate()+"'";
		}
		
		sql = sql + " order by w.booknumId";
		//查询结果
		List<Pwhesdtl> pwhesdtls =new ArrayList<Pwhesdtl>();
		pwhesdtls = whesdtlDao.findwithBooknumVoyageOrders(sql, 0, 0);

		d.setRows(pwhesdtls);
		
		return d;		
	}
	
	@Override
	public Long getBooknumVehicleCount() {
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		String count = null;
		Set<Company> whes =user.getCompanies();
		String whesId ="";
		if(null != whes){
			whesId = " ('oo'";
				for(Company wh : whes){
					whesId+=",'"+wh.getId()+"'";
				}
			whesId += ")";
		}
		count = "select count(DISTINCT b.id) from Whesdtl w left join Orders o on w.ordersId = o.id left join Booknum b on w.booknumId = b.id left join Voyage v on b.voyageId=v.id where (b.id is not null and b.id != '') and whesId in "+whesId+"";

		return whesdtlDao.findCount(count);
	}
	
	public void saveVehicleInf(Pwhesdtl pwhesdtl) {
		Whesdtl whesdtl = new Whesdtl();
		pwhesdtl.setId(UUID.randomUUID().toString());
		Make make = makeDao.get("from Make where id =:makeId", pwhesdtl);
		if(null != make){
			pwhesdtl.setMake(make.getMake());
		}else{
			pwhesdtl.setMake("DEFAULT");
			pwhesdtl.setModel("DEFAULT");
		}
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String userHql = "from Users where logname = '"+sessionInfo.getUsers().getLogname()+"'";
		Users user = userDao.get(userHql);
		pwhesdtl.setUsersId(user.getId());
		pwhesdtl.setUsers(user.getFullname());
		Company company = companyDao.get("from Company where id =:whesId", pwhesdtl);
		pwhesdtl.setWhes(company.getFullname());
		pwhesdtl.setFlowstate("1");
		BeanUtils.copyProperties(pwhesdtl, whesdtl);
		whesdtlDao.savewhesdtl(whesdtl);
	}
	
	@Override
	public Pwhesdtl saveOrUpdateVehicleInf(Pwhesdtl pwhesdtl) {
		Whesdtl whesdtl = new Whesdtl();
		pwhesdtl.setFlowstate("2");
		pwhesdtl.setId(UUID.randomUUID().toString());

		Users user = userDao.get("from Users where id =:usersId", pwhesdtl);
		pwhesdtl.setUsers(user.getFullname());
		Make make = makeDao.get("from Make where id =:makeId", pwhesdtl);
		if(null != make){
			pwhesdtl.setMake(make.getMake());
		}else{
			pwhesdtl.setMake("DEFAULT");
			pwhesdtl.setModel("DEFAULT");
		}
		Company company = companyDao.get("from Company where id =:whesId", pwhesdtl);
		pwhesdtl.setWhes(company.getFullname());
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		pwhesdtl.setUsersOper(sessionInfo.toString());
		String userOperHql = "from Users where logname = '"+pwhesdtl.getUsersOper()+"'";
		Users userOper = userDao.get(userOperHql);
		pwhesdtl.setUsersOperId(userOper.getId());
		BeanUtils.copyProperties(pwhesdtl, whesdtl);
		whesdtlDao.saveOrUpdatewhesdtl(whesdtl);
		return pwhesdtl;
	}

	private String addCondition(Pwhesdtl whesdtl, String sql) {
		if(null !=whesdtl.getBooknum() && !whesdtl.getBooknum().isEmpty() && (whesdtl.getBooknum()).indexOf("Please Select") != 0){
			sql = sql+" and booknum = '"+whesdtl.getBooknum()+"'";
		}
		if(null !=whesdtl.getCarrier()&& !whesdtl.getCarrier().isEmpty() && (whesdtl.getCarrier()).indexOf("Please Select") != 0){
			sql = sql+" and carrier = '"+whesdtl.getCarrier()+"'";
		}
		if(null !=whesdtl.getVessel()&& !whesdtl.getVessel().isEmpty() && (whesdtl.getVessel()).indexOf("Please Select") != 0){
			sql = sql+" and vessel = '"+whesdtl.getVessel()+"'";		
		}
		if(null !=whesdtl.getVoyage()&& !whesdtl.getVoyage().isEmpty() && (whesdtl.getVoyage()).indexOf("Please Select") != 0){
			sql = sql+" and voyage = '"+whesdtl.getVoyage()+"'";
		}
		if(null !=whesdtl.getTruck()&& !whesdtl.getTruck().isEmpty() && (whesdtl.getTruck()).indexOf("Please Select") != 0){
			sql = sql+" and truck = '"+whesdtl.getTruck()+"'";
		}
		if(null !=whesdtl.getTruckingFromDate()&& !whesdtl.getTruckingFromDate().isEmpty()){
			sql = sql+" and truckdate >= '"+whesdtl.getTruckingFromDate()+"'";
		}
		if(null !=whesdtl.getTruckingToDate()&& !whesdtl.getTruckingToDate().isEmpty()){
			sql = sql+" and truckdate <= '"+whesdtl.getTruckingToDate()+"'";
		}
		return sql;
	}
	
	@Override
	public DataGrid findWareHouseVehicleInfo(String vin) {
		DataGrid d = new DataGrid();
		String sql = "select w.id,w.vin,w.pic,w.make,w.model,w.year,w.color,w.engine,w.sticker,w.fuel,b.loaddate,b.entrydate,b.vessel,b.voyage,b.uoo " +
				"from Whesdtl as w left join Booknum as b on w.booknumId = b.id where w.vin = '"+vin+"'";
		List<Pwhesdtl> whesdtls = whesdtlDao.findWareHousewithbooknum(sql.toString());
		d.setRows(whesdtls);
		return d;
	}
	
	@Override
	public List<Ppic> findPicsByVin(String vin) throws IOException {
		String hql = "from Pic where vin = '"+vin+"'";
		List<Pic> pic = picDao.find(hql);
		List<Ppic> pics = new ArrayList<Ppic>();
	   	for(int i=0;i<pic.size();i++){
	   		Ppic pic1 = new Ppic();
	   		BeanUtils.copyProperties(pic.get(i), pic1);
	   		pics.add(pic1);
	   	}
		return pics;
	}
	
	@Override
	public List<Ppic> findPicsByVinAndType(String vin, String type) {
		String hql;
		if(type.equals("0")){
			hql = "from Pic where vin = '"+vin+"'";
		}else{
			hql = "from Pic where vin = '"+vin+"' and pictype = '"+type+"'";
		}
		List<Pic> pic = picDao.find(hql);
		List<Ppic> pics = new ArrayList<Ppic>();
	   	for(int i=0;i<pic.size();i++){
	   		Ppic pic1 = new Ppic();
	   		BeanUtils.copyProperties(pic.get(i), pic1);
	   		pics.add(pic1);
	   	}
		return pics;
	}
	
	@Override
	public Pic findPicPathByName(String picName) {
		String hql = "from Pic where name = '"+picName+"'";
		return picDao.get(hql);
	}
	@Override
	public Pwhesdtl removeWareHousePic(Pwhesdtl pwhesdtl) {
		String hql = "from Pic where path = '"+pwhesdtl.getPicPath()+"' and id = '"+pwhesdtl.getId()+"'";
		Pic pic = picDao.get(hql);
		File folder = new File(pic.getPath().replace("/" + pic.getName(), ""));
		if(folder.exists()){
			File[] files = folder.listFiles();
			for(File file:files){
				if(file.getName().equals(pic.getName())){
					file.delete();
				}
			}
		}
		
		String hqlWhesdtl = "from Whesdtl where vin = '"+pic.getVin()+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(hqlWhesdtl);
		BeanUtils.copyProperties(whesdtl, pwhesdtl);
		if(null != pwhesdtl.getPic() && !pwhesdtl.getPic().isEmpty()){
			File file =new File(pwhesdtl.getPic());
			if (!file.exists()) {
				pwhesdtl.setPic("");
			}
		}
		BeanUtils.copyProperties(pwhesdtl, whesdtl);
		whesdtlDao.updatePic(whesdtl);
		picDao.delete(pic);
		return pwhesdtl;
	}
	
	@Override
	public Pwhesdtl changeWareHousePic(String picPath, String picVin) {
		String hql = "from Whesdtl where vin = '"+picVin+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(hql);
		Pwhesdtl pwhesdtl = new Pwhesdtl();
		BeanUtils.copyProperties(whesdtl, pwhesdtl);
		pwhesdtl.setPic(picPath);
		BeanUtils.copyProperties(pwhesdtl, whesdtl);
		whesdtlDao.updatePic(whesdtl);
		return pwhesdtl;
	}
	
	@Override
	public Ppic findWareHousePic(String id) {
		String hql = "from Pic where id = '"+id+"'";
		Pic pic = picDao.get(hql);
		Ppic ppic = new Ppic();
		BeanUtils.copyProperties(pic, ppic);
		return ppic;
	}
	
	@Override
	public Pwhesdtl findwhesdtlByVin(String vin) {
		String hql = "from Whesdtl where vin = '"+vin+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(hql);
		Pwhesdtl wPwhesdtl = new Pwhesdtl();
		BeanUtils.copyProperties(whesdtl, wPwhesdtl);
		return wPwhesdtl;
	}
	
	@Override
	public void addPic(String vin, String picName, String picPath, String type) {
		Ppic ppic = new Ppic();
		ppic.setId(UUID.randomUUID().toString());
		ppic.setName(picName);
		ppic.setVin(vin);
		ppic.setPath(picPath);
		ppic.setPictype(type);
		Pic pic = new Pic();
		BeanUtils.copyProperties(ppic, pic);
		picDao.save(pic);
	}

	@Override
	public List<Whesdtl> groupPic() {
		String hql = "from Whesdtl group by make,model";
		return whesdtlDao.findVehicleInfo(hql);
	}
	
	@Override
	public List<Pwhesdtl> findPicWhesdtl(int i) {
		String sql = "select w.model,p.name,p.path,w.make,w.vin from pic as p left join whesdtl as w on p.vin=w.vin limit "+i+",500";
		return whesdtlDao.findwithPic(sql.toString());
	}
	
	@Override
	public Pwhesdtl findVehicleInfoByVin(String vin) {
		String sql = " from Whesdtl where vin = '"+vin+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(sql);
		Pwhesdtl pwhesdtl = new Pwhesdtl();
		BeanUtils.copyProperties(whesdtl, pwhesdtl);
		String makeHql = "from Make where make = '"+whesdtl.getMake()+"'";
		Make make = makeDao.get(makeHql);
		if(null != make){
			pwhesdtl.setMakeId(make.getId());
		}
		return pwhesdtl;
	}
	
	@Override
	public Pwhesdtl findVehicleInfoById(String id) {
		String sql = " from Whesdtl where id = '"+id+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(sql);
		Pwhesdtl pwhesdtl = new Pwhesdtl();
		BeanUtils.copyProperties(whesdtl, pwhesdtl);
		String makeHql = "from Make where make = '"+whesdtl.getMake()+"'";
		Make make = makeDao.get(makeHql);
		if(null != make){
			pwhesdtl.setMakeId(make.getId());
		}
		return pwhesdtl;
	}
	
	@Override
	public String updateWarehouseInfo(Pwhesdtl pwhesdtl) {
		String userOperHql = "from Whesdtl where id = '"+pwhesdtl.getId()+"'";
		Whesdtl userOperWhesdtl = whesdtlDao.getPic(userOperHql);
		String hql = "from Make where id = '"+pwhesdtl.getMakeId()+"'";
		Make make = makeDao.get(hql);
		if(null != make){
			pwhesdtl.setMake(make.getMake());
		}else{
			pwhesdtl.setMake("DEFAULT");
			pwhesdtl.setModel("DEFAULT");
		}
		pwhesdtl.setFlowstate("2");
		pwhesdtl.setOldUsers(pwhesdtl.getUsers());

		Users user = userDao.get("from Users where fullname =:oldUsers", pwhesdtl);
		Pmemo pmemo = new Pmemo();
		if(null != pwhesdtl.getMemoId() && !pwhesdtl.getMemoId().isEmpty()){
			String memoHql = "from Memo where id = '"+pwhesdtl.getMemoId()+"'";
			Memo memo = memoDao.get(memoHql);
			if(null != memo){
				BeanUtils.copyProperties(memo, pmemo);
				pmemo.setContent(pwhesdtl.getMemo());
				pmemo.setVin(pwhesdtl.getVin());
				pmemo.setWhesId(pwhesdtl.getWhesId());
				BeanUtils.copyProperties(pmemo, memo);
				memoDao.update(memo);
			}
		}else{
			if(null != pwhesdtl.getMemo() && !pwhesdtl.getMemo().isEmpty()){
				pmemo.setId(UUID.randomUUID().toString());
				SessionInfo sessionInfo = new SessionInfo();
				sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
				pmemo.setUsers(sessionInfo.toString());
				String userMemoHql = "from Users where logname = '"+sessionInfo.toString()+"'";
				Users userOper = userDao.get(userMemoHql);
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
			}
		}
		if(null != user){
			pwhesdtl.setUsersId(user.getId());
			Company company = companyDao.get("from Company where id =:whesId", pwhesdtl);
			pwhesdtl.setWhes(company.getFullname());
			pwhesdtl.setUsersOper(userOperWhesdtl.getUsersOper());
			pwhesdtl.setUsersOperId(userOperWhesdtl.getUsersOperId());
			hitaii.util.BeanUtils.copyProperties(pwhesdtl, userOperWhesdtl);
			whesdtlDao.updateWarehouseInfo(userOperWhesdtl);
			return "success";
		}else{
			return "User not exist.";
		}
	}
	
	@Override
	public String updatePreAlertInfo(Pwhesdtl pwhesdtl) {
		String hql = "from Whesdtl where id = '"+pwhesdtl.getId()+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(hql);
		String makeHql = "from Make where id = '"+pwhesdtl.getMakeId()+"'";
		String whesHql = "from Company where id = '"+pwhesdtl.getWhesId()+"'";
		Company company = companyDao.get(whesHql);
		pwhesdtl.setWhes(company.getFullname());
		Make make = makeDao.get(makeHql);
		if(null != make){
			pwhesdtl.setMake(make.getMake());
		}else{
			pwhesdtl.setMake("DEFAULT");
			pwhesdtl.setModel("DEFAULT");
		}
		if(!whesdtl.getFlowstate().equals("1")){
			return "The vehicle has been put in storage";
		}else{
			hitaii.util.BeanUtils.copyProperties(pwhesdtl, whesdtl);
			whesdtlDao.updateWarehouseInfo(whesdtl);
			return "success";
		}
	}
	
	@Override
	public void updatePath(String path,String picPath,String vin) {
		String hql = "from Pic where path ='"+picPath+"'";
		Pic pic = picDao.get(hql);
		if(null != pic){
			pic.setPath(path);
			picDao.update(pic);
		}

		String hql1= "from Whesdtl where pic = '"+picPath+"' and vin = '"+vin+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(hql1);
		if(null != whesdtl){
			whesdtl.setPic(path);
			whesdtlDao.updatePic(whesdtl);
		}
	}
	
	@Override
	public List<Whesdtl> findVehicleInfByFlowState() {
		String hql = "from Whesdtl where flowstate = '1'";
		return whesdtlDao.findVehicleInfo(hql);
	}
	@Override
	public DataGrid findVehicleInfByUsers(Pwhesdtl pwhesdtl) {
		DataGrid d = new DataGrid();
		String hql = "from Whesdtl as w,Make as m where w.users = '"+pwhesdtl.getUsers()+"' and w.flowstate = '1' and m.make=w.make";
		List<Pwhesdtl> whesdtls = whesdtlDao.findWareHousewithMake(hql.toString());
		d.setRows(whesdtls);
		return d;
	}
	@Override
	public DataGrid findCustomerByFullName(String name, Pwhesdtl pwhesdtl) {
		DataGrid d = new DataGrid();
		if(null == name || name.equals("")){
			name = "null";
		}
		if(name.indexOf("'") != -1){
			name=name.replace("'", "''");
		}
		String hql = "from Users where fullname like '"+name+"%' or logname like '"+name+"%'";
		String countHql = "select count(*) from Users where fullname like '"+name+"%' or logname like '"+name+"%'";
		if(null != pwhesdtl.getSort()){
			hql += (" order by" + " " + pwhesdtl.getSort() + " " + pwhesdtl.getOrder());
		}
		List<Users> users = userDao.find(hql, pwhesdtl.getPage(), pwhesdtl.getRows());
		Long count = userDao.count(countHql);
		d.setRows(users);
		d.setTotal(count);
		return d;
	}
	@Override
	public Whesdtl findAllVehicle(Pwhesdtl pwhesdtl) {
		String hql = "from Whesdtl where vin = '"+pwhesdtl.getVin()+"'";
		return whesdtlDao.getPic(hql);
	}
	@Override
	public Json resaleVehicle(Pwhesdtl pwhesdtl) {
		Json j = new Json();
		Users user = userDao.get("from Users where fullname =:users", pwhesdtl);
		if(null != user){
			String hql = "from Whesdtl where id = '"+pwhesdtl.getId()+"'";
			Whesdtl whesdtl = whesdtlDao.getPic(hql);
			pwhesdtl.setUsersId(user.getId());
			hitaii.util.BeanUtils.copyProperties(pwhesdtl, whesdtl);
			whesdtlDao.updatePic(whesdtl);
			j.setObj(pwhesdtl);
			j.setMsg("success");
			j.setSuccess(true);
			return j;
		}else{
			j.setMsg("The user does not exist");
			return j;
		}
	}
	
	@Override
	public DataGrid getWhesdtDatagrid(Pwhesdtl pwhesdtl) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		DataGrid d = new DataGrid();
		String sqlCount = "";
		String sql = null;
		String count = null;
		if(sessionInfo.getUsers().getTypes().equals("1")){
			sql = "select w.id,w.pic,w.vin,w.make,w.model,w.year,w.color,w.engine,w.keynum,w.users,w.sticker,w.indate,w.whes," +
					"b.booknum,w.titlestate,w.billstate,w.proofstate,w.note,b.connum,b.loaddate,w.flowstate,o.ordersdate from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where 1=1";
			count ="select count(*) from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where 1=1";
		}else if(sessionInfo.getUsers().getTypes().equals("2")){
			sql = "select w.id,w.pic,w.vin,w.make,w.model,w.year,w.color,w.engine,w.keynum,w.users,w.sticker,w.indate,w.whes," +
					"b.booknum,w.titlestate,w.billstate,w.proofstate,w.note,b.connum,b.loaddate,w.flowstate,o.ordersdate from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.users = '"+sessionInfo.getUsers().getFullname()+"'";
			count ="select count(*) from Whesdtl as w left join Booknum as b on w.booknumId = b.id left join Orders as o on w.ordersId = o.id where w.users = '"+sessionInfo.getUsers().getFullname()+"'";
		}

		if(null != pwhesdtl.getUsers() && !pwhesdtl.getUsers().isEmpty()){
			sqlCount = sqlCount+(" and w.users = '"+pwhesdtl.getUsers()+"'");
		}
		if(null != pwhesdtl.getVin() && !pwhesdtl.getVin().isEmpty()){
			sqlCount = sqlCount+(" and w.vin like '%"+pwhesdtl.getVin()+"%'");
		}
		if(null != pwhesdtl.getYear() && !pwhesdtl.getYear().isEmpty()){
			sqlCount = sqlCount+(" and w.year = '"+pwhesdtl.getYear()+"'");
		}
		if(null != pwhesdtl.getWhes() && !pwhesdtl.getWhes().isEmpty()){
			sqlCount = sqlCount+(" and w.whes = '"+pwhesdtl.getWhes()+"'");
		}
		String hqlMake = "from Make where id = '"+pwhesdtl.getMakeId()+"'";
		Make make = makeDao.get(hqlMake);
		if(null != pwhesdtl.getMakeId() && !pwhesdtl.getMakeId().isEmpty() && !pwhesdtl.getMakeId().equals("DEFAULT")){
			sqlCount = sqlCount+(" and w.make = '"+make.getMake()+"'");
		}
		if(null != pwhesdtl.getModel() && !pwhesdtl.getModel().isEmpty() && !pwhesdtl.getModel().equals("DEFAULT")){
			sqlCount = sqlCount+(" and w.model = '"+pwhesdtl.getModel()+"'");
		}
		if(null != pwhesdtl.getColor() && !pwhesdtl.getColor().isEmpty()){
			sqlCount = sqlCount+(" and w.color like '%"+pwhesdtl.getColor()+"%'");
		}
		if(null != pwhesdtl.getBooknum() && !pwhesdtl.getBooknum().isEmpty()){
			sqlCount = sqlCount+(" and b.booknum like '%"+pwhesdtl.getBooknum()+"%'");
		}
		if(null != pwhesdtl.getConsize() && !pwhesdtl.getConsize().isEmpty()){
			sqlCount = sqlCount+(" and b.consize like '%"+pwhesdtl.getConsize()+"%'");
		}
		if(null != pwhesdtl.getInWareFrom() && !pwhesdtl.getInWareFrom().isEmpty() 
				&& null != pwhesdtl.getInWareTO() && !pwhesdtl.getInWareTO().isEmpty()){
			sqlCount = sqlCount+(" and w.indate between '"+pwhesdtl.getInWareFrom()+"' and '"+pwhesdtl.getInWareTO()+"'");
		}
		if(null != pwhesdtl.getLoadingFrom() && !pwhesdtl.getLoadingFrom().isEmpty()
				&& null != pwhesdtl.getLoadingTo() && !pwhesdtl.getLoadingTo().isEmpty()){
			sqlCount = sqlCount+(" and b.loaddate between '"+pwhesdtl.getLoadingFrom()+"' and '"+pwhesdtl.getLoadingTo()+"'");
		}
		if(null != pwhesdtl.getFlowstate() && !pwhesdtl.getFlowstate().isEmpty()){
			sqlCount = sqlCount+(" and w.flowstate = '"+pwhesdtl.getFlowstate()+"'");
		}
		if(null != pwhesdtl.getWhesId() && !pwhesdtl.getWhesId().isEmpty() && !pwhesdtl.getWhesId().equals("ALL")){
			sqlCount = sqlCount+(" and w.whesId = '"+pwhesdtl.getWhesId()+"'");
		}
		if(null != pwhesdtl.getLoaddate() && !pwhesdtl.getLoaddate().isEmpty()){
			if(pwhesdtl.getLoaddate().equals("1")){
				sqlCount = sqlCount+(" and b.loaddate is not null and b.loaddate != ''");
			}else if(pwhesdtl.getLoaddate().equals("2")){
				sqlCount = sqlCount+(" and b.loaddate is null or b.loaddate = ''");
			}
		}
		if(null != pwhesdtl.getOrderstate() && !pwhesdtl.getOrderstate().isEmpty()){
			if(pwhesdtl.getOrderstate().equals("1")){
				sqlCount = sqlCount+(" and o.ordersdate is not null and o.ordersdate != ''");
			}else if(pwhesdtl.getOrderstate().equals("2")){
				sqlCount = sqlCount+(" and o.ordersdate is null or o.ordersdate = ''");
			}
		}
		if(null != pwhesdtl.getSort() && !pwhesdtl.getSort().isEmpty() && null != pwhesdtl.getOrder() && !pwhesdtl.getOrder().isEmpty()){
			sqlCount = sqlCount+(" order by" + " " + pwhesdtl.getSort() + " " + pwhesdtl.getOrder());
		}
		
		sql = sql + sqlCount;
		count = count + sqlCount; 
		List<Pwhesdtl> whesdtls = whesdtlDao.findwithbooknum(sql.toString(), pwhesdtl.getPage(), pwhesdtl.getRows());
		d.setRows(whesdtls);
		d.setTotal(whesdtlDao.findCount(count));
		return d;
	}
	
	@Override
	public void deletePreAlertVehicle(Pwhesdtl pwhesdtl) {
		String hql = "from Whesdtl where id = '"+pwhesdtl.getId()+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(hql);
		whesdtlDao.deleteWhesdtl(whesdtl);
	}
	
	@Override
	public String inWarehouseInfo(Pwhesdtl pwhesdtl) {
		String userOperHql = "from Whesdtl where id = '"+pwhesdtl.getId()+"'";
		Whesdtl userOperWhesdtl = whesdtlDao.getPic(userOperHql);
		String hql = "from Make where id = '"+pwhesdtl.getMakeId()+"'";
		Make make = makeDao.get(hql);
		if(null != make){
			pwhesdtl.setMake(make.getMake());
		}else{
			pwhesdtl.setMake("DEFAULT");
			pwhesdtl.setModel("DEFAULT");
		}
		pwhesdtl.setFlowstate("2");
		pwhesdtl.setOldUsers(pwhesdtl.getUsers());

		Users user = userDao.get("from Users where fullname =:oldUsers", pwhesdtl);
		if(null != user){
			pwhesdtl.setUsersId(user.getId());
			String companyHql = "from Company where id = '"+pwhesdtl.getWhesId()+"'";
			Company company = companyDao.get(companyHql);
			if(null != company){
				pwhesdtl.setWhesId(company.getId());
				pwhesdtl.setWhes(company.getFullname());
			}
			
			SessionInfo sessionInfo = new SessionInfo();
			sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
			pwhesdtl.setUsersOper(sessionInfo.toString());
			String userInOperHql = "from Users where logname = '"+pwhesdtl.getUsersOper()+"'";
			Users userOper = userDao.get(userInOperHql);
			pwhesdtl.setUsersOperId(userOper.getId());
			pwhesdtl.setIndate(WhesdtlUtil.getNowTime());
			BeanUtils.copyProperties(pwhesdtl, userOperWhesdtl);
			whesdtlDao.updateWarehouseInfo(userOperWhesdtl);
			Pmemo pmemo = new Pmemo();
			if(null != pwhesdtl.getMemo() && !pwhesdtl.getMemo().isEmpty()){
				pmemo.setId(UUID.randomUUID().toString());
				pmemo.setUsers(sessionInfo.toString());
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
			}
			return "success";
		}else{
			return "User not exist.";
		}
		
	}
	
	@Override
	public DataGrid findVehicleMemoByVin(Pwhesdtl pwhesdtl) {
		DataGrid d = new DataGrid();
		String memoHql = "from Memo where vin = '"+pwhesdtl.getVin()+"'";
		if(null != pwhesdtl.getSort() && !pwhesdtl.getSort().isEmpty() && null != pwhesdtl.getOrder() && !pwhesdtl.getOrder().isEmpty()){
			memoHql = memoHql+(" order by" + " " + pwhesdtl.getSort() + " " + pwhesdtl.getOrder());
		}
		List<Memo> memos = memoDao.find(memoHql, pwhesdtl.getPage(), pwhesdtl.getRows());
		List<Pmemo> pMemo = new ArrayList<Pmemo>();
		if (null != memos && memos.size() > 0) {
			for (Memo m : memos) {
				Pmemo pmemo = new Pmemo();
				BeanUtils.copyProperties(m, pmemo);
				pMemo.add(pmemo);
			}
		}
		d.setRows(pMemo);
		d.setTotal(memoDao.count(("select count(*) from Memo where vin=:vin"), pwhesdtl));
		return d;
	}
	
	@Override
	public Pwhesdtl findVehicleInfoById(Pwhesdtl pwhesdtl) {
		Whesdtl whesdtl = whesdtlDao.getVehicleInfo("from Whesdtl where id=:id", pwhesdtl);
		Make make = makeDao.get("from Make where make = '"+whesdtl.getMake()+"'");
		BeanUtils.copyProperties(whesdtl, pwhesdtl);
		if(null != make){
			pwhesdtl.setMakeId(make.getId());
		}
		return pwhesdtl;
	}
	
	@Override
	public void changePicType(String picId, String type) {
		String hql = "from Pic where id = '"+picId+"'";
		Pic pic = picDao.get(hql);
		Ppic ppic = new Ppic();
		ppic.setPictype(type);
		hitaii.util.BeanUtils.copyProperties(ppic, pic);
		picDao.update(pic);
	}
	
	@Override
	public Pmemo findMemoContentById(String memoId){
		String hql = "from Memo where id = '"+memoId+"'";
		Memo memo = memoDao.get(hql);
		Pmemo pmemo = new Pmemo();
		if(null != memo){
			BeanUtils.copyProperties(memo, pmemo);
		}
		return pmemo;
	}
	
	@Override
	public DataGrid findUooAndWhesdtlServices(Pwhesdtl whesdtl, Users user) {
		DataGrid d = new DataGrid();
		String hql = null;
		String count = null;
		if(user.getTypes().equals("1")){
			String sqlorder = " from Booknum where uoo is not null ";
			count ="select count(*) "+ sqlorder;
			if(null !=whesdtl.getUoo() && !whesdtl.getUoo().isEmpty()){
				sqlorder = sqlorder+" and uoo like '%"+whesdtl.getUoo()+"%'";
			}
			if(null != whesdtl.getSort() && !whesdtl.getSort().isEmpty() && null != whesdtl.getOrder() && !whesdtl.getOrder().isEmpty()){
				sqlorder=sqlorder+(" order by" + " " + whesdtl.getSort() + " " + whesdtl.getOrder());
			}
			List<Booknum> booknums = booknumDao.find(sqlorder, whesdtl.getPage(), whesdtl.getRows());
			String sqlordersID ="";
			if(null!=booknums){
				sqlordersID+=" and b.id in ('000000'";
				for(Booknum b :booknums){
					sqlordersID+=",'"+b.getId()+"'";
				}
				sqlordersID+=") ";
			}
			
			hql = "from Whesdtl as w, Booknum as b where w.booknumId=b.id" + sqlordersID;
			hql = hql + " order by w.booknumId";
			
			d.setTotal(booknumDao.count(count));
			
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
			hql = "from Whesdtl as w, Booknum as b where w.booknumId=b.id and b.uoo is not null and w.whesId in " + whesId + "";
			count = "select count(DISTINCT b.id) from Whesdtl w, Booknum b where w.booknumId = b.id and b.uoo is not null and w.whesId in "+whesId+"";
			if(null !=whesdtl.getUoo() && !whesdtl.getUoo().isEmpty()){
				hql = hql + " and b.uoo like '%"+whesdtl.getUoo()+"%'";
				count = count + " and b.uoo like '%"+whesdtl.getUoo()+"%'";
			}
			hql = hql + " group by w.id";
			List<Pwhesdtl> pwhesdtls =new ArrayList<Pwhesdtl>();
			pwhesdtls = whesdtlDao.findUooAndWhesdtlServices(hql, whesdtl.getPage(), whesdtl.getRows());
			
			String sqlbooknumID ="";
			if(null!=pwhesdtls){
				sqlbooknumID+=" and w.id  in ('00000'";
				for(Pwhesdtl wPwhesdtl :pwhesdtls){
					sqlbooknumID+=",'"+wPwhesdtl.getId()+"'";
				}
				sqlbooknumID+=") ";
			}
			
			hql = "from Whesdtl as w, Booknum as b where w.booknumId=b.id and b.uoo is not null and w.whesId in " + whesId + "" + " " + sqlbooknumID;
			hql = hql + " order by w.booknumId";
			d.setTotal(whesdtlDao.findCount(count));
		}
		
		List<Pwhesdtl> whesdtls = whesdtlDao.findUooAndWhesdtlServices(hql, 0, 0);
		List<Pservices> pservicesList = new ArrayList<Pservices>();
		for(int i=0;i<whesdtls.size();i++){
			Set<WhesdtlServices> whesdtlServices = whesdtls.get(i).getWhesdtlServiceses();
			if(null != whesdtlServices && whesdtlServices.size()>0){
				for(WhesdtlServices w : whesdtlServices){
					Pservices pservices = new Pservices();
					Services services = w.getServices();
					BeanUtils.copyProperties(services, pservices);
					Whesdtl whesdtl2 = w.getWhesdtl();
					pservices.setPaystate(w.getPaystate());
					pservices.setPay(w.getPay());
					pservices.setDiscount(w.getDiscount());
					pservices.setPrice(w.getPrice());
					pservices.setUsers(whesdtl2.getUsers());
					pservices.setVin(whesdtl2.getVin());
					pservices.setUoo(whesdtls.get(i).getUoo());
					Company company = companyDao.get("from Company where id =:companyId", services);
					if(null != company){
						pservices.setCompany(company.getFullname());
					}
					pservices.setServiceId(services.getId());
					pservices.setWhesdtlId(whesdtl2.getId());
					pservices.setId(w.getId());
					pservices.setBooknumId(whesdtls.get(i).getBooknumId());
					pservicesList.add(pservices);
				}
			}else{
				Pservices pservices = new Pservices();
				pservices.setUsers(whesdtls.get(i).getUsers());
				pservices.setVin(whesdtls.get(i).getVin());
				pservices.setUoo(whesdtls.get(i).getUoo());
				pservices.setBooknumId(whesdtls.get(i).getBooknumId());
				pservices.setWhesdtlId(whesdtls.get(i).getId());
				pservicesList.add(pservices);
			}
		}
		
		d.setRows(pservicesList);
		return d;
	}
	
	@Override
	public List<Pwhesdtl> findWhesdtlServices(Users user) {
		DataGrid d = new DataGrid();
		String hql = null;
		String count = null;
		if(user.getTypes().equals("1")){
			String sqlorder = " from Booknum where uoo is not null ";
			count ="select count(*) "+ sqlorder;
			List<Booknum> booknums = booknumDao.find(sqlorder, 0, 0);
			String sqlordersID ="";
			if(null!=booknums){
				sqlordersID+=" and b.id in ('000000'";
				for(Booknum b :booknums){
					sqlordersID+=",'"+b.getId()+"'";
				}
				sqlordersID+=") ";
			}
			
			hql = "from Whesdtl as w, Booknum as b where w.booknumId=b.id" + sqlordersID;
			hql = hql + " order by w.booknumId";
			
			d.setTotal(booknumDao.count(count));
			
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
			hql = "from Whesdtl as w, Booknum as b where w.booknumId=b.id and b.uoo is not null and w.whesId in " + whesId + " group by w.id";
			count = "select count(DISTINCT b.id) from Whesdtl w, Booknum b where w.booknumId = b.id and b.uoo is not null and w.whesId in "+whesId+"";
			
			List<Pwhesdtl> pwhesdtls =new ArrayList<Pwhesdtl>();
			pwhesdtls = whesdtlDao.findUooAndWhesdtlServices(hql, 0, 0);
			
			String sqlbooknumID ="";
			if(null!=pwhesdtls){
				sqlbooknumID+=" and w.id  in ('00000'";
				for(Pwhesdtl wPwhesdtl :pwhesdtls){
					sqlbooknumID+=",'"+wPwhesdtl.getId()+"'";
				}
				sqlbooknumID+=") ";
			}
			
			hql = "from Whesdtl as w, Booknum as b where w.booknumId=b.id and b.uoo is not null and w.whesId in " + whesId + "" + " " + sqlbooknumID;
			hql = hql + " order by w.booknumId";
			d.setTotal(whesdtlDao.findCount(count));
		}
		
		List<Pwhesdtl> whesdtls = whesdtlDao.findUooAndWhesdtlServices(hql, 0, 0);
		return whesdtls;
	}   
	
	@Override
	public Long findVehicleInfo() {
		Long preAlert = null;
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo= (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		if(!sessionInfo.getUsers().getTypes().equals("1")){
			String preAlertHql = "select count(*) from Whesdtl where usersId = '"+sessionInfo.getUsers().getId()+"' and flowstate = '1'";
			preAlert = whesdtlDao.findwithCount(preAlertHql);
		}
		return preAlert;
	}
	
	@Override
	public Whesdtl getWhesdtlByVin(Pwhesdtl pwhesdtl) {
		return whesdtlDao.getWhesdtl("from Whesdtl where id != '"+pwhesdtl.getId()+"' and vin = '"+pwhesdtl.getVin()+"'");
	}
	
	@Override
	public Whesdtl getWhesdtl(Pwhesdtl pwhesdtl) {
		return whesdtlDao.getWhesdtl("from Whesdtl where id = '"+pwhesdtl.getId()+"'");
	}
	
	@Override
	public void changePicInfo(Whesdtl whesdtl, Pwhesdtl pwhesdtl) {
		if(null != pwhesdtl.getMakeId() && !pwhesdtl.getMakeId().isEmpty()){
    		Make make = makeDao.get("from Make where id = '"+pwhesdtl.getMakeId()+"'");
    		if(null != make){
    			pwhesdtl.setMake(make.getMake());
    		}else{
	    		pwhesdtl.setMake("DEFAULT");
	    	}
    	}else{
    		pwhesdtl.setMake("DEFAULT");
    	}
		if(null == pwhesdtl.getModel() || pwhesdtl.getModel().isEmpty()){
    		pwhesdtl.setModel("DEFAULT");
    	}
		if(null == whesdtl.getMake() || whesdtl.getMake().isEmpty()){
	    	whesdtl.setMake("DEFAULT");
    	}
		if(null == whesdtl.getModel() || whesdtl.getModel().isEmpty()){
    		whesdtl.setModel("DEFAULT");
    	}
		List<Pic> pics = picDao.find("from Pic where vin =:vin", whesdtl);
		if(null != pics && pics.size()>0){
			if(null != whesdtl.getVin() && null != pwhesdtl.getVin() && !whesdtl.getVin().equals(pwhesdtl.getVin())){
				for(Pic p : pics){
					Ppic ppic = new Ppic();
					BeanUtils.copyProperties(p, ppic);
					ppic.setVin(pwhesdtl.getVin());
					String oldWhesdtlPic = p.getPath();
					
					String path = p.getPath().substring(p.getPath().lastIndexOf("/")+1);
					String picPath = "C:/";
			    	picPath = picPath + "/" + "USFL" + "/";
			    	picPath = picPath + whesdtl.getMake() + "/";
			    	picPath = picPath + whesdtl.getModel() + "/";
			    	String oldPicPath = picPath + whesdtl.getVin();
			    	String newPicPath = picPath + pwhesdtl.getVin();
			    	
			    	//修改对应c盘下的图片vin号
					File srcDir = new File(oldPicPath);  
					srcDir.renameTo(new File(newPicPath));  
			    	
			    	picPath = newPicPath  + "/" + path;
			    	
			    	ppic.setPath(picPath);
					hitaii.util.BeanUtils.copyProperties(ppic, p);
					picDao.update(p);
					if(whesdtl.getPic().equals(oldWhesdtlPic)){
						Pwhesdtl pwhesdtl2 = new Pwhesdtl();
						pwhesdtl2.setPic(picPath);
						hitaii.util.BeanUtils.copyProperties(pwhesdtl2, whesdtl);
						whesdtlDao.updatePic(whesdtl);
					}
				}
			}
			if(!whesdtl.getMake().equals(pwhesdtl.getMake()) || !whesdtl.getModel().equals(pwhesdtl.getModel())){
				String picsPath = "C://USFL/";
				WhesdtlUtil.isExists(picsPath + pwhesdtl.getMake());
				
				dirFrom = new File(picsPath + whesdtl.getMake() + "/" + whesdtl.getModel() + "/" + pwhesdtl.getVin());
				dirTo = new File(picsPath + pwhesdtl.getMake() + "/" + pwhesdtl.getModel() + "/" + pwhesdtl.getVin());
				listFileInDir(new File(picsPath + whesdtl.getMake() + "/" + whesdtl.getModel() + "/" + pwhesdtl.getVin()));
				WhesdtlUtil.deleteFile(new File(picsPath + whesdtl.getMake() + "/" + whesdtl.getModel() + "/" + pwhesdtl.getVin()));
				for(Pic p : pics){
					String picPath = "C:/";
			    	picPath = picPath + "/" + "USFL" + "/";
					Ppic ppic = new Ppic();
					BeanUtils.copyProperties(p, ppic);
					String oldWhesdtlPic = p.getPath();
					
					String path = p.getPath().substring(p.getPath().lastIndexOf("/")+1);
					picPath = picPath + pwhesdtl.getMake() + "/";
					picPath = picPath + pwhesdtl.getModel() + "/";
					picPath = picPath + pwhesdtl.getVin();
					picPath = picPath  + "/" + path;
					ppic.setPath(picPath);
					hitaii.util.BeanUtils.copyProperties(ppic, p);
					picDao.update(p);
					if(whesdtl.getPic().equals(oldWhesdtlPic)){
						Pwhesdtl pwhesdtl2 = new Pwhesdtl();
						pwhesdtl2.setPic(picPath);
						hitaii.util.BeanUtils.copyProperties(pwhesdtl2, whesdtl);
						whesdtlDao.updatePic(whesdtl);
					}
				}
			}
		}
	}
	
	 public void listFileInDir(File file) {   
        File[] files = file.listFiles();   
        for (File f : files) {   
             String tempfrom = f.getAbsolutePath();   
             String tempto = tempfrom.replace(dirFrom.getAbsolutePath(),   
                     dirTo.getAbsolutePath()); // 后面的路径 替换前面的路径名   
            if (f.isDirectory()) {   
                 File tempFile = new File(tempto);   
                 tempFile.mkdirs();   
                 listFileInDir(f);   
             } else {   
                 System.out.println("源文件:" + f.getAbsolutePath());   
                //   
                int endindex = tempto.lastIndexOf("\\");// 找到"/"所在的位置   
                 String mkdirPath = tempto.substring(0, endindex);   
                 File tempFile = new File(mkdirPath);   
                 tempFile.mkdirs();// 创建立文件夹   
                 System.out.println("目标点:" + tempto);   
                 copy(tempfrom, tempto);   
             }   
        } 
     }   
    /**
      * 封装好的文件拷贝方法
      */  
    public void copy(String from, String to) {   
        try {   
             InputStream in = new FileInputStream(from);   
             OutputStream out = new FileOutputStream(to);   
   
            byte[] buff = new byte[1024];   
            int len = 0;   
            while ((len = in.read(buff)) != -1) {   
                 out.write(buff, 0, len);   
             }   
             in.close();   
             out.close();   
         } catch (FileNotFoundException e) {   
             e.printStackTrace();   
         } catch (IOException e) {   
             e.printStackTrace();   
         }   
     }
    
	@Override
	public List<Whesdtl> findVehicleByBooknumId(String bookingNumId) {
		return whesdtlDao.findVehicleInfo("from Whesdtl where booknumId = '"+bookingNumId+"'");
	}

}
