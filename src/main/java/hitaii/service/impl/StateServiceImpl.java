package hitaii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.CountryDaoI;
import hitaii.dao.StateDaoI;
import hitaii.model.Country;
import hitaii.model.State;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pstate;
import hitaii.service.StateServiceI;
import hitaii.util.WhesdtlUtil;

@Service("stateService")
public class StateServiceImpl implements StateServiceI {
	private StateDaoI sateDao;
	private CountryDaoI countryDao;
	public StateDaoI getSateDao() {
		return sateDao;
	}
	@Autowired
	public void setSateDao(StateDaoI sateDao) {
		this.sateDao = sateDao;
	}
	public CountryDaoI getCountryDao() {
		return countryDao;
	}
	@Autowired
	public void setCountryDao(CountryDaoI countryDao) {
		this.countryDao = countryDao;
	}
	
	@Override
	public List<Pstate> findStateName(Pstate state) {
		String hql = "from State order by state";
		List<State> states = sateDao.find(hql);
		List<Pstate> pstates =new ArrayList<Pstate>();
		if(null != states && states.size() > 0){
			for(State s : states){
				Pstate pstate = new Pstate();
				BeanUtils.copyProperties(s, pstate, new String[]{"country"});
				pstates.add(pstate);
			}
		}
		return pstates;
	}
	@Override
	public DataGrid getStateDataGrid(Pstate pstate) {
		DataGrid d = new DataGrid();
		String hql = "select state.*, country.country from State as state left join Country as country on state.countryId = country.id where 1=1 ";
		String count = "select count(*) from State as state left join Country as country on state.countryId = country.id where 1=1 ";
		if(null != pstate.getCountryId() && !pstate.getCountryId().isEmpty()){
			hql = hql + " and state.countryId = '"+pstate.getCountryId()+"'";
			count = count + " and state.countryId = '"+pstate.getCountryId()+"'";
		}
		if(null != pstate.getState() && !pstate.getState().isEmpty()){
			hql = hql + " and state.state = '"+pstate.getState()+"'";
			count = count + " and state.state = '"+pstate.getState()+"'";
		}
		if(null != pstate.getSort() && !pstate.getSort().isEmpty() && null != pstate.getOrder() && !pstate.getOrder().isEmpty()){
			hql = hql+" order by "+ pstate.getSort() + " " + pstate.getOrder();
		}
		List<Pstate> pstates = sateDao.findwithCountry(hql, pstate.getPage(), pstate.getRows());
		d.setRows(pstates);
		d.setTotal(sateDao.Count(count));
		return d;
	}
	private void changeModelTd(List<State> states, List<Pstate> pStates) {
		for(int i=0;i<states.size();i++){
			Hibernate.initialize(states.get(i).getCountry());
			Pstate p = new Pstate();
			BeanUtils.copyProperties(states.get(i), p);
			if(null != states.get(i).getCountry()){
				p.setCountryId(states.get(i).getCountry().getId());
			}
			pStates.add(p);
		}
		
	}

	public Json addState(Pstate pstate){
		Json j = new Json();
		try {
			pstate.setId(WhesdtlUtil.getUUID());
			State state = new State();
			Country country = countryDao.get("from Country where id =:countryId", pstate);
			if(null != country){
				state.setCountry(country);
			}
			hitaii.util.BeanUtils.copyProperties(pstate, state);
			sateDao.save(state);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public Json findStateById(Pstate pstate) {
		Json j = new Json();
		State state = sateDao.get("from State where id =:id", pstate);
		if(null != state){
			BeanUtils.copyProperties(state, pstate, new String[]{"country"});
			pstate.setCountryId(state.getCountry().getId());
			j.setMsg("success");
			j.setSuccess(true);
			j.setObj(pstate);
		}else{
			j.setMsg("state is not exist");
		}
		return j;
	}
	
	public Json editState(Pstate pstate){
		Json j = new Json();
		State state = sateDao.get("from State where id =:id", pstate);
		if(null != state){
			Country country = countryDao.get("from Country where id =:countryId", pstate);
			if(null != country){
				state.setCountry(country);
			}
			hitaii.util.BeanUtils.copyProperties(pstate, state);
			sateDao.update(state);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("state is not exist");
		}
		return j;
	}
	
	@Override
	public boolean deleteState(Pstate pstate) {
		if("".equals(pstate.getId()) || null == pstate.getId()){
			return false;
		}
		State state=new State();
		BeanUtils.copyProperties(pstate, state);
		sateDao.delete(state);
		return true;
	}
	
	@Override
	public List<State> findStateByCountryId(Pstate pstate) {
		List<State> states = sateDao.find("from State where countryId =:countryId order by state", pstate);
		return states;
	}
	
}
