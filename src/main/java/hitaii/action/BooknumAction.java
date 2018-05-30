package hitaii.action;

import java.text.ParseException;
import java.util.List;

import hitaii.model.Booknum;
import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pbooknum;
import hitaii.pageModel.Pdock;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.BooknumServiceI;
import hitaii.service.MemoServiceI;
import hitaii.service.WhesdtlServiceI;
import hitaii.util.BeanUtils;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 订舱号Action
 */
@Namespace("/")
@Action(value = "booknumAction")
public class BooknumAction extends BaseAction implements ModelDriven<Pbooknum>{
	
	private Pbooknum booknum=new Pbooknum();
	@Override
	public Pbooknum getModel() {
		return booknum;
	}
	private BooknumServiceI booknumService;
	private MemoServiceI memoService;
	
	public MemoServiceI getMemoService() {
		return memoService;
	}
	@Autowired
	public void setMemoService(MemoServiceI memoService) {
		this.memoService = memoService;
	}
	public BooknumServiceI getBooknumService() {
		return booknumService;
	}
	@Autowired
	public void setBooknumService(BooknumServiceI booknumService) {
		this.booknumService = booknumService;
	}
	private WhesdtlServiceI whesdtlService;
	public WhesdtlServiceI getWhesdtlService() {
		return whesdtlService;
	}
	@Autowired
	public void setWhesdtlService(WhesdtlServiceI whesdtlService) {
		this.whesdtlService = whesdtlService;
	}
	
	//加载booknum 下拉列表数据 
	public void getBooknumByVoyageId() throws ParseException{
		super.writeJson(booknumService.findBooknumSelect(booknum));
	}
	
	//根据booknum  查找对应的对象
	public void findEditBooknum(){
		Json j = new Json();
		booknum = booknumService.findEditBooknum(booknum);
		if(null == booknum){
			j.setSuccess(false);
			j.setMsg("loading...failed");
		}else{
			j.setSuccess(true);
			j.setMsg("success");
			j.setObj(booknum);
		}
		super.writeJson(j);
	}
	
	//Trucking页面 修改了内容  
	public void editTruckEditSubmit(){
		Json j = new Json();
		boolean b =booknumService.editTruckEditSubmit(booknum);
		if(b){
			j.setSuccess(true);
			j.setMsg("update...success ");
		}else{
			j.setSuccess(false);
			j.setMsg("update...failed ");
		}
		super.writeJson(j);
	}
	/**
	 * NewOrders 页面 保存
	 */
	public void UpdateWhesdtlBooknum(){
		Json j = new Json();
		boolean b =booknumService.UpdateWhesdtlBooknum(booknum);
		if(b){
			j.setSuccess(true);
			j.setMsg("Save...success ");
		}else{
			j.setSuccess(false);
			j.setMsg("Save...failed ");
		}
		super.writeJson(j);
		
	}
	
	/**
	 * NewOrders 页面 保存
	 */
	public void deleteBooknum(){
		Json j = new Json();
		boolean b =booknumService.deleteBooknum(booknum);
		if(b){
			j.setSuccess(true);
			j.setMsg("delete...success ");
		}else{
			j.setSuccess(false);
			j.setMsg("delete...failed ");
		}
		super.writeJson(j);
		
	}
	
	/**
	 * 创建booknum对象
	 */
	public void addBooknum(){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		Json j = new Json();
		booknum.setBooknum(booknum.getBooknum().replaceAll(" ", ""));
		for(String booknumName : booknum.getBooknum().split(",")){
			Pbooknum editBooknum = new Pbooknum();
			BeanUtils.copyProperties(booknum, editBooknum);
			editBooknum.setBooknum(booknumName);
			Booknum booknums = booknumService.getBooknum(editBooknum);
			if(null != booknums){
				j.setMsg("Duplication of booknum! Please edit your booknum" + " " + booknumName);
			}else{
				j =booknumService.addBooknum(editBooknum,user);
			}
		}
		super.writeJson(j);
	}
	
	/**
	 * DockReceipt  Uoo查询
	 */
	public void SearchUooDockReceipt(){
		Json j = new Json();
		Pdock pdock =booknumService.SearchUooDockReceipt(booknum);
		j.setSuccess(true);
		j.setObj(pdock);
		super.writeJson(j);
	}
	
	//根据booknum查询车辆
	public void findBooknumById(){
		List<Pwhesdtl> pwhesdtls = booknumService.findBooknumById(booknum);
		super.writeJson(pwhesdtls);
	}
	
	//根据id查询booknum
	public void findBooknumUooById(){
		super.writeJson(booknumService.findBooknumUooById(booknum));
	}

	//添加booknum memo
	public void addBooknumMemo(){
		String msg = memoService.addBooknumMemo(booknum);
		Json j = new Json();
    	j.setMsg(msg);
    	super.writeJson(j);
	}
	
	//更新booknum
	public void updateBooknum(){
		String msg = booknumService.updateBooknum(booknum);
		Json j = new Json();
		j.setMsg(msg);
		super.writeJson(j);
	}
}
