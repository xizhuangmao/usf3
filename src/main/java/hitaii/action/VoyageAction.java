package hitaii.action;

import java.text.ParseException;
import java.util.List;

import hitaii.model.Booknum;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pbooknum;
import hitaii.pageModel.Pvoyage;
import hitaii.service.BooknumServiceI;
import hitaii.service.VoyageServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 航次Action
 */

@Namespace("/")
@Action(value = "voyageAction")
public class VoyageAction extends BaseAction implements ModelDriven<Pvoyage>{
	
	private Pvoyage voyage = new Pvoyage();

	private VoyageServiceI voyageService;
	private BooknumServiceI booknumService;
	
	public BooknumServiceI getBooknumService() {
		return booknumService;
	}
	@Autowired
	public void setBooknumService(BooknumServiceI booknumService) {
		this.booknumService = booknumService;
	}
	public VoyageServiceI getVoyageService() {
		return voyageService;
	}
	@Autowired
	public void setVoyageService(VoyageServiceI voyageService) {
		this.voyageService = voyageService;
	}

	@Override
	public Pvoyage getModel() {
		return voyage;
	}
	
	/**
	 * 加载Voyage列表  和下拉列表
	 * @throws ParseException 
	 * @throws BeansException 
	 */
	public void getVoyageByVesselId() throws BeansException, ParseException{
		super.writeJson(voyageService.findVoyageName(voyage));
	}
	/**
	 * 加载Voyage页面 DataGrid数据
	 */
	public void getVoyageDataGrid(){
		DataGrid d = voyageService.getVoyageDataGrid(voyage);
		super.writeJson(d);
	}
	/**
	 * voyage页面add添加或者edit修改 一个对象
	 */
	public void addVoyage(){
		Json j = voyageService.addVoyage(voyage);
		super.writeJson(j);
	}
	/**
	 * 打开Voyage页面 加载对象
	 */
	public void findVoyageById(){
		Json j = new Json();
		Pvoyage pvoyage = voyageService.findVoyageById(voyage);
		if(null != pvoyage){
			j.setObj(pvoyage);
			j.setMsg("success");
			j.setSuccess(true);
		}
		super.writeJson(j);
	}
	/**
	 * Voyage页面 删除功能
	 */
	public void deleteVoyage(){
		Json j = new Json();
		List<Booknum> booknums = booknumService.findBooknumByVoyageId(voyage);
		if(null != booknums && booknums.size()>0){
			j.setMsg("This voyage can not be deleted, please refresh");
		}else{
			boolean b = voyageService.deleteVoyage(voyage);
			if(b){
				j.setSuccess(true);
				j.setMsg("delect Voyage success...");
			}else{
				j.setSuccess(false);
				j.setMsg("delect Voyage failed...");
			}
		}
		super.writeJson(j);
	}
	/**
	 * 根据id查询voyage对象
	 */
	public void getVoyageById(){
		Json j = new Json();
		voyage = voyageService.getVoyageById(voyage);
		if(null==voyage){
			j.setSuccess(false);
			j.setMsg("find Voyage failed...");
		}else{
			j.setSuccess(true);
			j.setObj(voyage);
			j.setMsg("find Voyage success...");
		}
		super.writeJson(j);
	}
	/**
	 * 修改voyage对象
	 */
	public void editVoyage(){
		Json j = voyageService.editVoyage(voyage);
		super.writeJson(j);
	}
	
}
