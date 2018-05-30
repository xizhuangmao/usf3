package hitaii.action;

import hitaii.pageModel.Json;
import hitaii.pageModel.Presource;
import hitaii.service.ResourceServiceI;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "resourceAction")
public class ResourceAction extends BaseAction implements ModelDriven<Presource>{
	
	private Presource resource = new Presource();
	@Override
	public Presource getModel() {
		// TODO Auto-generated method stub
		return resource;
	}
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ResourceAction.class);
	
	private ResourceServiceI resourceService ;

	public ResourceServiceI getResourceService() {
		return resourceService;
	}
	@Autowired
	public void setResourceService(ResourceServiceI resourceService) {
		this.resourceService = resourceService;
	}


	
	public void add(){

		Json j = new Json();
		try{
			Presource u =resourceService.save(resource);
			j.setSuccess(true);
			j.setMsg("add resource success");
			j.setObj(u);
		}catch(Exception e){
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	
	public void delete(){
		
		Json j = new Json();
		
		resourceService.remove(resource);
		j.setSuccess(true);
		j.setMsg("delete resource success！");
		
		super.writeJson(j);
	}
	
	public void edit(){
		
		Json j = new Json();
		if(null!=resource.getId() && StringUtils.equals(resource.getId(), resource.getPid())){
			j.setSuccess(false);
			j.setMsg("parent can't be their own");
		}else{
			resourceService.edit(resource);
			j.setSuccess(true);
			j.setMsg("edit resource success！");
		}
		
		
		super.writeJson(j);
	}
	
	public void getById(){
		
		Json j = new Json();
		
		Presource r = resourceService.get(resource);
		j.setSuccess(true);
		j.setMsg("get resource success！");
		j.setObj(r);
		
		super.writeJson(j);
	}
	
	/*
	 * 获取资源treegrid
	 */
	public void treegrid(){
	
		
		super.writeJson(resourceService.treeGrid(resource));
	}
	
	/*
	 * 提供资源树
	 */
	public void tree(){
	
		
		super.writeJson(resourceService.tree(resource));
	}
	


}
