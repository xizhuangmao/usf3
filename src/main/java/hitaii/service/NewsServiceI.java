package hitaii.service;

import hitaii.model.News;
import hitaii.pageModel.Pnews;

import java.util.List;

public interface NewsServiceI {
	
	//查找所有员工发布新闻
	public List<Pnews> findEmployeeNews(Pnews pnews);
	//员工添加新闻
	public void addEmployeeNews(Pnews pnews);
	//删除新闻
	public void deleteEmployeeNews(String id);
	//根据id查询新闻
	public News findEmployeeNewsById(Pnews pnews);
	//修改员工新闻
	public void editEmployeeNewsById(Pnews pnews);
	//查找所有客户发布新闻
	public List<Pnews> findCustomerNews(Pnews pnews);
	//客户添加新闻
	public void addCustomerNews(Pnews pnews);
	//删除新闻
	public void deleteCustomerNews(String id);
	//修改客户新闻
	public void editCustomerNewsById(Pnews pnews);
	
	public Long countEmployeeNews();
	
	public Long countCustomerNews();
	//查找所有帮助新闻
	public List<Pnews> findHelpNews(Pnews pnews);
	
	public Long countHelpNews();
	//添加帮助内容
	public void addHelpNews(Pnews pnews);
	//删除帮助内容
	public void deleteHelpNews(String id);
	//根据id查询帮助内容
	public News findHelpNewsById(Pnews pnews);
	//修改帮助内容
	public void editHelpNewsById(Pnews pnews);

}
