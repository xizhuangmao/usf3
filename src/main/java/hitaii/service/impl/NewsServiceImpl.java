package hitaii.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.NewsDaoI;
import hitaii.model.News;
import hitaii.model.Vessel;
import hitaii.pageModel.Pnews;
import hitaii.pageModel.Pvessel;
import hitaii.service.NewsServiceI;

@Service("newsService")
public class NewsServiceImpl implements NewsServiceI {
	
	private NewsDaoI newsDao;
	
	public NewsDaoI getNewsDao() {
		return newsDao;
	}
	@Autowired
	public void setNewsDao(NewsDaoI newsDao) {
		this.newsDao = newsDao;
	}

	@Override
	public List<Pnews> findEmployeeNews(Pnews pnews) {
		String hql = "from News where types = '1'";
		if(null != pnews.getTitle() && !pnews.getTitle().isEmpty()){
			hql += " and title like '%"+pnews.getTitle()+"%'";
		}
		if(null != pnews.getContent() && !pnews.getContent().isEmpty()){
			hql += " and content like '%"+pnews.getContent()+"%'";
		}
		if(null != pnews.getNewsdatefrom() && !pnews.getNewsdatefrom().isEmpty() && null != pnews.getNewsdateto() && !pnews.getNewsdateto().isEmpty()){
			hql += " and newsdate between '"+pnews.getNewsdatefrom()+"' and '"+pnews.getNewsdateto()+"'";
		}
		if(null != pnews.getSort() && !pnews.getSort().isEmpty() && null != pnews.getOrder() && !pnews.getOrder().isEmpty()){
			hql += " order by" + " " + pnews.getSort() + " " + pnews.getOrder();
		}
		hql += " order by newsdate desc";
		List<News> news = newsDao.find(hql, pnews.getPage(), pnews.getRows());
		List<Pnews> pnewslist = new ArrayList<Pnews>(); 
		if (null != news && news.size() > 0) {
			for (News n : news) {
				Pnews pnews2 = new Pnews();
				BeanUtils.copyProperties(n, pnews2);
				pnewslist.add(pnews2);
			}
		}
		return pnewslist;
	}
	
	@Override
	public List<Pnews> findHelpNews(Pnews pnews) {
		String hql = "from News where types = '3'";
		if(null != pnews.getTitle() && !pnews.getTitle().isEmpty()){
			hql += " and title like '%"+pnews.getTitle()+"%'";
		}
		if(null != pnews.getContent() && !pnews.getContent().isEmpty()){
			hql += " and content like '%"+pnews.getContent()+"%'";
		}
		if(null != pnews.getNewsdatefrom() && !pnews.getNewsdatefrom().isEmpty() && null != pnews.getNewsdateto() && !pnews.getNewsdateto().isEmpty()){
			hql += " and newsdate between '"+pnews.getNewsdatefrom()+"' and '"+pnews.getNewsdateto()+"'";
		}
		if(null != pnews.getSort() && !pnews.getSort().isEmpty() && null != pnews.getOrder() && !pnews.getOrder().isEmpty()){
			hql += " order by" + " " + pnews.getSort() + " " + pnews.getOrder();
		}
		hql += " order by newsdate desc";
		List<News> news = newsDao.find(hql, pnews.getPage(), pnews.getRows());
		List<Pnews> pnewslist = new ArrayList<Pnews>(); 
		if (null != news && news.size() > 0) {
			for (News n : news) {
				Pnews pnews2 = new Pnews();
				BeanUtils.copyProperties(n, pnews2);
				pnewslist.add(pnews2);
			}
		}
		return pnewslist;
	}

	@Override
	public Long countEmployeeNews() {
		String hql = "select count(*) from News where types = '1'";
		Long count = newsDao.count(hql);
		return count;
	}
	
	@Override
	public Long countCustomerNews() {
		String hql = "select count(*) from News where types = '2'";
		Long count = newsDao.count(hql);
		return count;
	}

	@Override
	public Long countHelpNews() {
		String hql = "select count(*) from News where types = '3'";
		Long count = newsDao.count(hql);
		return count;
	}
	
	@Override
	public void addEmployeeNews(Pnews pnews) {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date); 
		
		pnews.setId(UUID.randomUUID().toString());
		pnews.setNewsdate(time);
		pnews.setTypes("1");
		pnews.setUsersId(ServletActionContext.getRequest().getSession().getAttribute("sessionInfo").toString());
		News news = new News();
		BeanUtils.copyProperties(pnews, news);
		newsDao.save(news);
	}
	
	@Override
	public void addHelpNews(Pnews pnews) {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date); 
		
		pnews.setId(UUID.randomUUID().toString());
		pnews.setNewsdate(time);
		pnews.setTypes("3");
		pnews.setUsersId(ServletActionContext.getRequest().getSession().getAttribute("sessionInfo").toString());
		News news = new News();
		BeanUtils.copyProperties(pnews, news);
		newsDao.save(news);
	}

	@Override
	public void deleteEmployeeNews(String id) {
		String hql = "from News where id = '"+id+"'";
		News news = newsDao.get(hql);
		newsDao.delete(news);
	}
	@Override
	public News findEmployeeNewsById(Pnews pnews) {
		String hql = "from News where id = '"+pnews.getId()+"'";	
		return newsDao.get(hql);
	}
	@Override
	public void editEmployeeNewsById(Pnews pnews) {
		String hql = "from News where id = '"+pnews.getId()+"'";
		News news = newsDao.get(hql);
		pnews.setTypes(news.getTypes());
		pnews.setUsersId(news.getUsersId());
		pnews.setNewsdate(news.getNewsdate());
		BeanUtils.copyProperties(pnews, news);
		newsDao.update(news);
	}
	@Override
	public List<Pnews> findCustomerNews(Pnews pnews) {
		String hql = "from News where types = '2'";
		if(null != pnews.getTitle() && !pnews.getTitle().isEmpty()){
			hql += " and title like '%"+pnews.getTitle()+"%'";
		}
		if(null != pnews.getContent() && !pnews.getContent().isEmpty()){
			hql += " and content like '%"+pnews.getContent()+"%'";
		}
		if(null != pnews.getNewsdatefrom() && !pnews.getNewsdatefrom().isEmpty() && null != pnews.getNewsdateto() && !pnews.getNewsdateto().isEmpty()){
			hql += " and newsdate between '"+pnews.getNewsdatefrom()+"' and '"+pnews.getNewsdateto()+"'";
		}
		if(null != pnews.getSort() && !pnews.getSort().isEmpty() && null != pnews.getOrder() && !pnews.getOrder().isEmpty()){
			hql += " order by" + " " + pnews.getSort() + " " + pnews.getOrder();
		}
		hql += " order by newsdate desc";
		List<News> news = newsDao.find(hql, pnews.getPage(), pnews.getRows());
		List<Pnews> pnewslist = new ArrayList<Pnews>(); 
		if (null != news && news.size() > 0) {
			for (News n : news) {
				Pnews pnews2 = new Pnews();
				BeanUtils.copyProperties(n, pnews2);
				pnewslist.add(pnews2);
			}
		}
		return pnewslist;
	}
	@Override
	public void addCustomerNews(Pnews pnews) {
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date); 
		
		pnews.setId(UUID.randomUUID().toString());
		pnews.setNewsdate(time);
		pnews.setTypes("2");
		pnews.setUsersId(ServletActionContext.getRequest().getSession().getAttribute("sessionInfo").toString());
		News news = new News();
		BeanUtils.copyProperties(pnews, news);
		newsDao.save(news);
	}
	@Override
	public void deleteCustomerNews(String id) {
		String hql = "from News where id = '"+id+"'";
		News news = newsDao.get(hql);
		newsDao.delete(news);
	}
	@Override
	public void editCustomerNewsById(Pnews pnews) {
		String hql = "from News where id = '"+pnews.getId()+"'";
		News news = newsDao.get(hql);
		Pnews pNews = new Pnews();
		BeanUtils.copyProperties(news, pNews);
		pNews.setTitle(pnews.getTitle());
		pNews.setContent(pnews.getContent());
		BeanUtils.copyProperties(pNews, news);
		newsDao.update(news);
	}
	@Override
	public void deleteHelpNews(String id) {
		String hql = "from News where id = '"+id+"'";
		News news = newsDao.get(hql);
		newsDao.delete(news);
	}
	@Override
	public News findHelpNewsById(Pnews pnews) {
		String hql = "from News where id = '"+pnews.getId()+"'";	
		return newsDao.get(hql);
	}
	@Override
	public void editHelpNewsById(Pnews pnews) {
		String hql = "from News where id = '"+pnews.getId()+"'";
		News news = newsDao.get(hql);
		pnews.setTypes(news.getTypes());
		pnews.setUsersId(news.getUsersId());
		pnews.setNewsdate(news.getNewsdate());
		BeanUtils.copyProperties(pnews, news);
		newsDao.update(news);
	}

}
