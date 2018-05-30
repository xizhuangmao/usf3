package hitaii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.PortDaoI;
import hitaii.model.Country;
import hitaii.model.Make;
import hitaii.model.Port;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pport;
import hitaii.service.PortServiceI;
import hitaii.util.WhesdtlUtil;

@Service("portService")
public class PortServiceImpl implements PortServiceI {

	private PortDaoI portDao;

	public PortDaoI getPortDao() {
		return portDao;
	}
	@Autowired
	public void setPortDao(PortDaoI portDao) {
		this.portDao = portDao;
	}

	@Override
	public DataGrid getPortDataGrid(Pport pport) {
		DataGrid d = new DataGrid();
		List<Pport> pports = new ArrayList<Pport>();
		String hql = "from Port where 1=1 ";
		if(null != pport.getPort() && !pport.getPort().isEmpty()){
			hql = hql + "and port = :port";
		}
		if(null != pport.getCountry() && !pport.getCountry().isEmpty()){
			hql = hql + "and country = :country";
		}
		if(null != pport.getState() && !pport.getState().isEmpty()){
			hql = hql + "and state = :state";
		}
		if(null != pport.getCity() && !pport.getCity().isEmpty()){
			hql = hql + "and city = :city";
		}
		List<Port> ports= new ArrayList<Port>();
		if(null != pport.getSort() && !pport.getSort().isEmpty() && null != pport.getOrder() && !pport.getOrder().isEmpty()){
			hql=hql+" order by "+ pport.getSort() + " " + pport.getOrder();
		}else{
			hql=hql+" order by port";
		}
		ports = portDao.find(hql,pport,pport.getPage(),pport.getRows());
			
		changeModelTd(ports,pports);
		d.setRows(pports);
		d.setTotal(portDao.count("select count(*)"+hql,pport));
		return d;
	}
	
	private void changeModelTd(List<Port> ports, List<Pport> pports) {
		if(ports.size()>0){
			for(Port port : ports){
				Pport pport = new Pport();
				BeanUtils.copyProperties(port, pport);
				pports.add(pport);
			}
		}
	}
	@Override
	public Json addPort(Pport pport) {
		Json j = new Json();
		try {
			pport.setId(WhesdtlUtil.getUUID());
			Port port = new Port();
			BeanUtils.copyProperties(pport, port);
			portDao.save(port);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public Json findPortById(Pport pport) {
		Json j = new Json();
		Port port = portDao.get("from Port where id = :id", pport);
		if(null != port){
			BeanUtils.copyProperties(port, pport);
			j.setObj(pport);
			j.setSuccess(true);
		}else{
			j.setMsg("port is not exist");
		}
		return j;
	}
	
	@Override
	public boolean deletePort(Pport pport) {
		if("".equals(pport.getId()) || null == pport.getId()){
			return false;
		}
		Port port=new Port();
		BeanUtils.copyProperties(pport, port);
		portDao.delete(port);
		return true;
	}
	
	@Override
	public List<Pport> getPortName() {
		List<Port> ports = portDao.find("from Port order by port");
		List<Pport> pports = new ArrayList<Pport>();
		for(Port p : ports){
			Pport pport = new Pport();
			BeanUtils.copyProperties(p, pport);
			pports.add(pport);
		}
		return pports;
	}
	
	@Override
	public Json editPort(Pport pport) {
		Json j = new Json();
		Port port = portDao.get("from Port where id=:id", pport);
		if(null != port){
			hitaii.util.BeanUtils.copyProperties(pport, port);
			portDao.update(port);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("port is not exist");
		}
		return j;
	}
	
}
