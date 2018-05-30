package hitaii.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.MakeDaoI;
import hitaii.dao.ModelDaoI;
import hitaii.dao.WhesdtlDaoI;
import hitaii.model.Make;
import hitaii.model.Model;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pmake;
import hitaii.pageModel.Pmodel;
import hitaii.service.ModelServiceI;
import hitaii.util.WhesdtlUtil;

@Service("modelService")
public class ModelServiceImpl implements ModelServiceI {

	private ModelDaoI modelDao;
	public ModelDaoI getModelDao() {
		return modelDao;
	}
	@Autowired
	public void setModelDao(ModelDaoI modelDao) {
		this.modelDao = modelDao;
	}

	private WhesdtlDaoI whesdtlDao;
	private MakeDaoI makeDao;
	public MakeDaoI getMakeDao() {
		return makeDao;
	}
	@Autowired
	public void setMakeDao(MakeDaoI makeDao) {
		this.makeDao = makeDao;
	}
	public WhesdtlDaoI getWhesdtlDao() {
		return whesdtlDao;
	}
	@Autowired
	public void setWhesdtlDao(WhesdtlDaoI whesdtlDao) {
		this.whesdtlDao = whesdtlDao;
	}
	@Override
	public List<Model> findAllModelByMakeId(Pmodel pmodel) {
		String hql = "from Model where makeId = '"+pmodel.getMakeId()+"' order by model";
		return modelDao.find(hql);
	}

	@Override
	public DataGrid getModelDataGrid(Pmodel pmodel) {
		DataGrid d = new DataGrid();
		String hql = "select model.*, make.make from Model as model left join Make as make on model.makeId = make.id where 1=1 ";
		String count = "select count(*) from Model as model left join Make as make on model.makeId = make.id where 1=1 ";
		if(null != pmodel.getMakeId() && !pmodel.getMakeId().isEmpty()){
			hql = hql + " and model.makeId = '"+pmodel.getMakeId()+"'";
			count = count + " and model.makeId = '"+pmodel.getMakeId()+"'";
		}
		if(null != pmodel.getModel() && !pmodel.getModel().isEmpty()){
			hql = hql + " and model.model = '"+pmodel.getModel()+"'";
			count = count + " and model.model = '"+pmodel.getModel()+"'";
		}
		if(null != pmodel.getSort() && !pmodel.getSort().isEmpty() && null != pmodel.getOrder() && !pmodel.getOrder().isEmpty()){
			hql = hql+" order by "+ pmodel.getSort() + " " + pmodel.getOrder();
		}
		List<Pmodel> pmodels = modelDao.findwithMake(hql, pmodel.getPage(), pmodel.getRows());
		d.setRows(pmodels);
		d.setTotal(modelDao.Count(count));
		return d;
	}
	
	@Override
	public Json addModel(Pmodel pmodel) {
		Json j = new Json();
		try {
			pmodel.setId(WhesdtlUtil.getUUID());
			Model model = new Model();
			BeanUtils.copyProperties(pmodel, model);
			modelDao.save(model);
			j.setMsg("success");
			j.setSuccess(true);
		} catch (BeansException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@Override
	public Json findModelById(Pmodel pmodel){
		Json j = new Json();
		Model model = modelDao.get("from Model where id =:id", pmodel);
		if(null != model){
			BeanUtils.copyProperties(model, pmodel);
			j.setMsg("success");
			j.setSuccess(true);
			j.setObj(pmodel);
		}else{
			j.setMsg("model is not exist");
		}
		return j;
	}
	
	@Override
	public Json deleteModel(Pmodel pmodel) {
		Json j = new Json();
		Model model = modelDao.get("from Model where id =:id", pmodel);
		if(null != model){
			modelDao.delete(model);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("model is not exist");
		}
		return j;
	}
	
	@Override
	public List<Pmodel> findModelName() {
		String hql = "from Model order by model";
		List<Model> models = modelDao.find(hql);
		List<Pmodel> pmodels =new ArrayList<Pmodel>();
		if(null != models && models.size() > 0){
			for(Model m : models){
				Pmodel pmodel = new Pmodel();
				BeanUtils.copyProperties(m, pmodel);
				pmodels.add(pmodel);
			}
		}
		return pmodels;
	}
	
	@Override
	public Json editModel(Pmodel pmodel) {
		Json j = new Json();
		Model model = modelDao.get("from Model where id =:id", pmodel);
		if(null != model){
			hitaii.util.BeanUtils.copyProperties(pmodel, model);
			modelDao.update(model);
			j.setMsg("success");
			j.setSuccess(true);
		}else{
			j.setMsg("model is not exist");
		}
		return j;
	}
}
