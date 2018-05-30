package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hitaii.constant.Constant;
import hitaii.dao.NvoccDaoI;
import hitaii.model.Carrier;
import hitaii.model.Nvocc;
import hitaii.model.Services;
import hitaii.model.Users;
import hitaii.model.Whes;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pnvocc;
import hitaii.pageModel.Pservices;
import hitaii.service.NvoccServiceI;
import hitaii.util.WhesdtlUtil;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("nvoccService")
public class NvoccServiceImpl implements NvoccServiceI{
	private NvoccDaoI nvoccDao;
	public NvoccDaoI getNvoccDao() {
		return nvoccDao;
	}
	@Autowired
	public void setNvoccDao(NvoccDaoI nvoccDao) {
		this.nvoccDao = nvoccDao;
	}
	
	@Override
	public DataGrid getAllNvoccUsers(Pnvocc pnvocc) {
		DataGrid d = new DataGrid();
		List<Pnvocc> pnvoccs = new ArrayList<Pnvocc>();
		String hql = "from Nvocc where 1 = 1 ";
		if(null != pnvocc.getSort() && !pnvocc.getSort().isEmpty() && null != pnvocc.getOrder() && !pnvocc.getOrder().isEmpty()){
			hql=hql+" order by "+ pnvocc.getSort() + " " + pnvocc.getOrder();
		}
		List<Nvocc> nvoccs=nvoccDao.find(hql,  pnvocc.getPage(), pnvocc.getRows());
		if(nvoccs.size()>0){
			changeModelTd(nvoccs,pnvoccs);
			d.setRows(pnvoccs);
			d.setTotal(nvoccDao.count("select count(*)"+hql,pnvocc));
		}
		return d;
	}
	private void changeModelTd(List<Nvocc> nvoccs, List<Pnvocc> pnvoccs) {
		if(nvoccs.size()>0){
			for(Nvocc nvocc : nvoccs){
				Pnvocc pnvocc = new Pnvocc();
				BeanUtils.copyProperties(nvocc, pnvocc);
				pnvoccs.add(pnvocc);
			}
		}
	}
	@Override
	public boolean saveOrUpdateOneNvocc(Pnvocc pnvocc) {
		if(null == pnvocc){
			return false;
		}
		if("".equals(pnvocc.getFullname()) || null == pnvocc.getFullname()){
			return false;
		}
		Nvocc nvocc = new Nvocc();
		if("".equals(pnvocc.getId()) || null == pnvocc.getId()){
			pnvocc.setId(WhesdtlUtil.getUUID());
		}
		BeanUtils.copyProperties(pnvocc, nvocc);
		nvoccDao.saveOrUpdate(nvocc);
		return true;
	}
	@Override
	public Pnvocc findNvoccEditObject(Pnvocc pnvocc) {
		if(null == pnvocc){
			return null;
		}
		String hql = " from Nvocc where 1 = 1 ";
		if("".equals(pnvocc.getId()) || null == pnvocc.getId()){
			return null;
		}else{
			hql = hql +" and id = :id";
		}
		Nvocc nvocc = nvoccDao.get(hql, pnvocc);
		if(null==nvocc){
			return null;
		}
		BeanUtils.copyProperties(nvocc,pnvocc);
		return pnvocc;
	}
	@Override
	public boolean delectOneNvocc(Pnvocc pnvocc) {
		if("".equals(pnvocc.getId()) || null == pnvocc.getId()){
			return false;
		}
		Nvocc nvocc=new Nvocc();
		BeanUtils.copyProperties(pnvocc, nvocc);
		nvoccDao.delete(nvocc);
		return true;
	}
	
	@Override
	public List<Pnvocc> findNvoccName(Users user) {
		List<Pnvocc> list = new ArrayList<Pnvocc> ();
		List<Nvocc> nvoccs = new ArrayList<Nvocc> ();
		String hql = " ";
		if(null==user){
			return null;
		}
		String types = user.getTypes();
		if(null==types || "".equals(types) || types.equals(Constant.USER_TYPE_EMPLOYEE)){
			hql = "from Nvocc where 1 = 1";
			nvoccs =nvoccDao.find(hql);
		}else{
			Set<Nvocc> ns =user.getNvoccs();
			if(null != ns){
					for(Nvocc nvocc : ns){
						nvoccs.add(nvocc);
					}
			}
		}
		if(nvoccs.size()>0){
			for(int i =0;i<nvoccs.size();i++){
				Pnvocc p = new Pnvocc();
				BeanUtils.copyProperties(nvoccs.get(i),p);
				list.add(p);
			}
		}
		return list;
	}
	
	@Override
	public List<Map<String, String>> findAllNvocc() {
		String hql = "from Nvocc order by fullname";
		List<Nvocc> nvocc = nvoccDao.find(hql);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		if (null != nvocc && nvocc.size() > 0) {
			Map<String, String> mapAll = new HashMap<String,String>();
			mapAll.put("nvocc", "Please Select");
			mapAll.put("id", "Please Select");
			list.add(mapAll);
			for(int i =0;i<nvocc.size();i++){
				Nvocc nvocc1 = nvocc.get(i);
				Map<String, String> map = new HashMap<String,String>();
				map.put("nvocc",  nvocc1.getFullname());
				map.put("id", nvocc1.getId());
				list.add(map);
			}
		}
		return list;
	}
	
}
