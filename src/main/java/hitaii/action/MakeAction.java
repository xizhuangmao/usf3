package hitaii.action;

import hitaii.pageModel.Json;
import hitaii.pageModel.Pmake;
import hitaii.service.MakeServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 *  by mh 2016 1 21
 *	车辆制造商Action
 */
@Namespace("/")
@Action(value = "makeAction")
public class MakeAction extends BaseAction implements ModelDriven<Pmake>{
	private Pmake pmake = new Pmake();
	@Override
	public Pmake getModel() {
		return pmake;
	}
	private MakeServiceI makeService;
	public MakeServiceI getMakeService() {
		return makeService;
	}
	@Autowired
	public void setMakeService(MakeServiceI makeService) {
		this.makeService = makeService;
	}
	
	/**
	 * 返回Make页面的DataGrid数据
	 */
	public void getMakeDataGrid(){
		super.writeJson(makeService.getAllMakeDataGrid(pmake));	
	}
	/**
	 * 保存新建Make对象
	 */
	public void addMake(){
		Json j = makeService.addMake(pmake);
		super.writeJson(j);
	}
	
	/**
	 * 页面的make下拉列表
	 */
	public void getMakeName(){
		super.writeJson(makeService.findMakeName());
	}
	/**
	 * 根据id 返回一个Make对象 
	 */
	public void findMakeById(){
		Json j = makeService.findMakeById(pmake);
		super.writeJson(j);
	}
	
	/**
	 * Make页面 删除一个对象
	 */
	public void deleteMake(){
		Json j = new Json();
		boolean b = makeService.deleteMake(pmake);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect Make success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect Make failed...");
		}
		super.writeJson(j);
	}
	
	/**
	 * 修改make
	 */
	public void editMake(){
		Json j = makeService.editMake(pmake);
		super.writeJson(j);
	}
}
