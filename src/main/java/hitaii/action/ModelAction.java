package hitaii.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hitaii.model.Model;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pmake;
import hitaii.pageModel.Pmodel;
import hitaii.service.MakeServiceI;
import hitaii.service.ModelServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *  by mh 2016 1 22
 *	车辆  型号Action
 */
@Namespace("/")
@Action(value = "modelAction")
public class ModelAction extends BaseAction implements ModelDriven<Pmodel>{
	private Pmodel pmodel = new Pmodel();
	@Override
	public Pmodel getModel() {
		return pmodel;
	}
	
	private ModelServiceI modelService;
	public ModelServiceI getModelService() {
		return modelService;
	}
	@Autowired
	public void setModelService(ModelServiceI modelService) {
		this.modelService = modelService;
	}
	
	/**
	 * 返回Model页面的DataGrid数据
	 */
	public void getModelDataGrid(){
		super.writeJson(modelService.getModelDataGrid(pmodel));	
	}
	
	/**
	 * 页面的mmodel下拉列表
	 */
	public void getModelName(){
		super.writeJson(modelService.findModelName());
	}
	
	/**
	 * 保存新建Model对象
	 */
	public void addModel(){
		Json j = modelService.addModel(pmodel);
		super.writeJson(j);
	}

	/**
	 * 根据id 返回一个Model对象 
	 */
	public void findModelById(){
		Json j = modelService.findModelById(pmodel);
		super.writeJson(j);
	}
	/**
	 * 根据id 删除一个Model对象 
	 */
	public void deleteModel(){
		Json j = modelService.deleteModel(pmodel);
		super.writeJson(j);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160811
	 * 查询Make对应的Model
	 * @throws IOException
	 */
	public void findModelByMakeId() throws IOException{
		List<Model> model = modelService.findAllModelByMakeId(pmodel);
		super.writeJson(model);
		
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160811
	 * 查询Make对应的Model
	 * @throws IOException
	 */
	public void editModel(){
		Json j = modelService.editModel(pmodel);
		super.writeJson(j);
	}
}
