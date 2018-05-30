package hitaii.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;

import hitaii.model.News;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pnews;
import hitaii.service.NewsServiceI;

import com.opensymphony.xwork2.ModelDriven;
/**
 * @author qc
 * 
 * 时间：20160129
 * 
 * 新闻Action
 */
@Namespace("/")
@Action(value = "newsAction")
public class NewsAction extends BaseAction implements ModelDriven<Pnews>{
	private Pnews pnews = new Pnews();
	@Override
	public Pnews getModel() {
		return pnews;
	}
	
	private NewsServiceI newsService;
	
	public NewsServiceI getNewsService() {
		return newsService;
	}
	@Autowired
	public void setNewsService(NewsServiceI newsService) {
		this.newsService = newsService;
	}
	
	/** @author qc
	 * 
	 * time 20160129
	 * 查找所有员工发布新闻
	 */
	public void findEmployeeNews(){
		List<Pnews> news = newsService.findEmployeeNews(pnews);
		for(int i=0;i<news.size();i++){
			news.get(i).setContent(news.get(i).getContent().replaceAll("</p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<br/>", ""));
			news.get(i).setContent(news.get(i).getContent().trim());
		}
		Long total = newsService.countEmployeeNews();
		Map<String, Object> employeeMap = new HashMap<String, Object>();
		if(pnews.getRows() != 0){
			long totalPa;
			if(total == 0){
				totalPa = 1;
			}else{
				if(total % pnews.getRows() == 0){
					totalPa = total/pnews.getRows();
				}else{
					totalPa = total/pnews.getRows() + 1;
				}
			}
			employeeMap.put("totalPa", totalPa);
		}
		
		employeeMap.put("news", news);
		employeeMap.put("total", total);
		super.writeJson(employeeMap);
	}
	
	/** @author qc
	 * 
	 * time 20160304
	 * 查找所有员工发布新闻
	 */
	public void findEmployeesNews(){
		List<Pnews> news = newsService.findEmployeeNews(pnews);
		for(int i=0;i<news.size();i++){
			news.get(i).setContent(news.get(i).getContent().replaceAll("</p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<br/>", ""));
			news.get(i).setContent(news.get(i).getContent().trim());
		}
		Long total = newsService.countEmployeeNews();
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("rows", news);
		datas.put("total", total);
		super.writeJson(datas);
	}
	
	/** @author qc
	 * 
	 * time 20160311
	 * 查找所有帮助新闻
	 */
	public void findHelpsNews(){
		List<Pnews> news = newsService.findHelpNews(pnews);
		for(int i=0;i<news.size();i++){
			news.get(i).setContent(news.get(i).getContent().replaceAll("</p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<br/>", ""));
			news.get(i).setContent(news.get(i).getContent().trim());
		}
		Long total = newsService.countHelpNews();
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("rows", news);
		datas.put("total", total);
		super.writeJson(datas);
	}
	
	/** @author qc
	 * 
	 * time 20160311
	 * 查找所有帮助新闻
	 */
	public void findHelpNews(){
		List<Pnews> news = newsService.findHelpNews(pnews);
		for(int i=0;i<news.size();i++){
			news.get(i).setContent(news.get(i).getContent().replaceAll("</p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<br/>", ""));
			news.get(i).setContent(news.get(i).getContent().trim());
		}
		Long total = newsService.countHelpNews();
		Map<String, Object> helpMap = new HashMap<String, Object>();
		if(pnews.getRows() != 0){
			long totalPa;
			if(total == 0){
				totalPa = 1;
			}else{
				if(total % pnews.getRows() == 0){
					totalPa = total/pnews.getRows();
				}else{
					totalPa = total/pnews.getRows() + 1;
				}
			}
			helpMap.put("totalPa", totalPa);
		}
		
		helpMap.put("news", news);
		helpMap.put("total", total);
		super.writeJson(helpMap);
	}
	
	/** @author qc
	 * 
	 * time 20160202
	 * 查找所有客户发布新闻
	 */
	public void findCustomersNews(){
		List<Pnews> news = newsService.findCustomerNews(pnews);
		for(int i=0;i<news.size();i++){
			news.get(i).setContent(news.get(i).getContent().replaceAll("</p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<br/>", ""));
			news.get(i).setContent(news.get(i).getContent().trim());
		}
		Long total = newsService.countCustomerNews();
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("rows", news);
		datas.put("total", total);
		super.writeJson(datas);
	}
	
	/** @author qc
	 * 
	 * time 20160202
	 * 查找所有客户发布新闻
	 */
	public void findCustomerNews(){
		List<Pnews> news = newsService.findCustomerNews(pnews);
		for(int i=0;i<news.size();i++){
			news.get(i).setContent(news.get(i).getContent().replaceAll("</p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<p>", ""));
			news.get(i).setContent(news.get(i).getContent().replaceAll("<br/>", ""));
			news.get(i).setContent(news.get(i).getContent().trim());
		}
		Long total = newsService.countCustomerNews();
		Map<String, Object> customerMap = new HashMap<String, Object>();
		if(pnews.getRows() != 0){
			long totalPa;
			if(total == 0){
				totalPa = 1;
			}else{
				if(total % pnews.getRows() == 0){
					totalPa = total/pnews.getRows();
				}else{
					totalPa = total/pnews.getRows() + 1;
				}
			}
			customerMap.put("totalPa", totalPa);
		}
		
		customerMap.put("news", news);
		customerMap.put("total", total);
		super.writeJson(customerMap);
	}
	
	
	/** @author qc
	 * 
	 * time 20160129
	 * 员工添加新闻
	 */
	public void addEmployeeNews(){
		pnews.setContent(pnews.getContent().trim());
		newsService.addEmployeeNews(pnews);
		Json j = new Json();
		j.setMsg("release success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	/** @author qc
	 * 
	 * time 20160311
	 * 添加帮助内容
	 */
	public void addHelpNews(){
		newsService.addHelpNews(pnews);
		Json j = new Json();
		j.setMsg("release success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	/** @author qc
	 * 
	 * time 20160202
	 * 客户添加新闻
	 */
	public void addCustomerNews(){
		newsService.addCustomerNews(pnews);
		Json j = new Json();
		j.setMsg("release success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	/** @author qc
	 * 
	 * time 20160129
	 * 员工删除新闻
	 */
	public void deleteEmployeeNews(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		newsService.deleteEmployeeNews(id);
		Json j = new Json();
		j.setMsg("success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	/** @author qc
	 * 
	 * time 20160129
	 * 删除帮助内容
	 */
	public void deleteHelpNews(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		newsService.deleteHelpNews(id);
		Json j = new Json();
		j.setMsg("success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	/** @author qc
	 * 
	 * time 20160202
	 * 客户删除新闻
	 */
	public void deleteCustomerNews(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String id = request.getParameter("id");
		newsService.deleteCustomerNews(id);
		Json j = new Json();
		j.setMsg("success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	/** @author qc
	 * 
	 * time 20160201
	 * 根据id查询员工新闻
	 */
	public void findEmployeeNewsById(){
		News news = newsService.findEmployeeNewsById(pnews);
		super.writeJson(news);
	}
	
	/** @author qc
	 * 
	 * time 20160201
	 * 根据id查询帮助内容
	 */
	public void findHelpNewsById(){
		News news = newsService.findHelpNewsById(pnews);
		super.writeJson(news);
	}
	
	/** @author qc
	 * 
	 * time 20160201
	 * 修改员工新闻
	 */
	public void editEmployeeNewsById(){
		newsService.editEmployeeNewsById(pnews);
		Json j = new Json();
		j.setMsg("edit success");
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	/** @author qc
	 * 
	 * time 20160201
	 * 修改帮助内容
	 */
	public void editHelpNewsById(){
		newsService.editHelpNewsById(pnews);
		Json j = new Json();
		j.setMsg("edit success");
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	/** @author qc
	 * 
	 * time 20160202
	 * 修改客户新闻
	 */
	public void editCustomerNewsById(){
		newsService.editCustomerNewsById(pnews);
		Json j = new Json();
		j.setMsg("edit success");
		j.setSuccess(true);
		super.writeJson(j);
	}
}
