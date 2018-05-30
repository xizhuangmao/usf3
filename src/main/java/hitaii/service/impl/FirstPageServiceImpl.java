package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.FirstPageDaoI;
import hitaii.model.Firstpage;
import hitaii.pageModel.PfirstPage;
import hitaii.service.FirstPageServiceI;

@Service("firstPageService")
public class FirstPageServiceImpl implements FirstPageServiceI {

	private FirstPageDaoI firstPageDao;
	
	public FirstPageDaoI getFirstPageDao() {
		return firstPageDao;
	}
	@Autowired
	public void setFirstPageDao(FirstPageDaoI firstPageDao) {
		this.firstPageDao = firstPageDao;
	}

	@Override
	public void updateOrSaveFirstPage(PfirstPage pfirstPage) {
		Firstpage firstpage = new Firstpage();
		BeanUtils.copyProperties(pfirstPage, firstpage);
		firstPageDao.update(firstpage);
	}
	
	@Override
	public List<Firstpage> findFirstPage() {
		String hql = "from Firstpage";
		List<Firstpage> firstpage = firstPageDao.find(hql);
		return firstpage;
	}
	
	@Override
	public Firstpage findEditFirstPage() {
		String hql = "from Firstpage";
		Firstpage firstpage = firstPageDao.get(hql);
		return firstpage;
	}
	
	@Override
	public void saveOrUpdateFirstPageCall(PfirstPage pfirstPage) {
		String hql = "from Firstpage where id = '"+pfirstPage.getId()+"'";
		Firstpage firstpage = firstPageDao.get(hql);
		pfirstPage.setWorkinghours(firstpage.getWorkinghours());
		pfirstPage.setPicintroduce(firstpage.getPicintroduce());
		pfirstPage.setIntroduce(firstpage.getIntroduce());
		pfirstPage.setFirstpic(firstpage.getFirstpic());
		pfirstPage.setPic1(firstpage.getPic1());
		pfirstPage.setPic2(firstpage.getPic2());
		pfirstPage.setPic3(firstpage.getPic3());
		pfirstPage.setPic4(firstpage.getPic4());
		pfirstPage.setPic5(firstpage.getPic5());
		BeanUtils.copyProperties(pfirstPage, firstpage);
		firstPageDao.saveOrUpdate(firstpage);
	}
	
	@Override
	public void saveOrUpdateFirstPageWork(PfirstPage pfirstPage) {
		String hql = "from Firstpage where id = '"+pfirstPage.getId()+"'";
		Firstpage firstpage = firstPageDao.get(hql);
		pfirstPage.setCallus(firstpage.getCallus());
		pfirstPage.setPicintroduce(firstpage.getPicintroduce());
		pfirstPage.setIntroduce(firstpage.getIntroduce());
		pfirstPage.setFirstpic(firstpage.getFirstpic());
		pfirstPage.setPic1(firstpage.getPic1());
		pfirstPage.setPic2(firstpage.getPic2());
		pfirstPage.setPic3(firstpage.getPic3());
		pfirstPage.setPic4(firstpage.getPic4());
		pfirstPage.setPic5(firstpage.getPic5());
		BeanUtils.copyProperties(pfirstPage, firstpage);
		firstPageDao.saveOrUpdate(firstpage);
	}
	
	@Override
	public void saveOrUpdateFirstPagePicIntro(PfirstPage pfirstPage) {
		String hql = "from Firstpage where id = '"+pfirstPage.getId()+"'";
		Firstpage firstpage = firstPageDao.get(hql);
		pfirstPage.setCallus(firstpage.getCallus());
		pfirstPage.setWorkinghours(firstpage.getWorkinghours());
		pfirstPage.setIntroduce(firstpage.getIntroduce());
		pfirstPage.setFirstpic(firstpage.getFirstpic());
		pfirstPage.setPic1(firstpage.getPic1());
		pfirstPage.setPic2(firstpage.getPic2());
		pfirstPage.setPic3(firstpage.getPic3());
		pfirstPage.setPic4(firstpage.getPic4());
		pfirstPage.setPic5(firstpage.getPic5());
		BeanUtils.copyProperties(pfirstPage, firstpage);
		firstPageDao.saveOrUpdate(firstpage);
	}
	
	@Override
	public void saveOrUpdateIntroduce(PfirstPage pfirstPage) {
		String hql = "from Firstpage where id = '"+pfirstPage.getId()+"'";
		Firstpage firstpage = firstPageDao.get(hql);
		pfirstPage.setCallus(firstpage.getCallus());
		pfirstPage.setWorkinghours(firstpage.getWorkinghours());
		pfirstPage.setFirstpic(firstpage.getFirstpic());
		pfirstPage.setPicintroduce(firstpage.getPicintroduce());
		pfirstPage.setPic1(firstpage.getPic1());
		pfirstPage.setPic2(firstpage.getPic2());
		pfirstPage.setPic3(firstpage.getPic3());
		pfirstPage.setPic4(firstpage.getPic4());
		pfirstPage.setPic5(firstpage.getPic5());
		BeanUtils.copyProperties(pfirstPage, firstpage);
		firstPageDao.saveOrUpdate(firstpage);
	}
	
	@Override
	public PfirstPage findFirstPageById(String id) {
		String hql = "from Firstpage where id = '"+id+"'";
		Firstpage firstpage = firstPageDao.get(hql);
		PfirstPage pfirstPage = new PfirstPage();
		BeanUtils.copyProperties(firstpage, pfirstPage);
		return pfirstPage;
	}

	@Override
	public void changeFirstPic(String id, String firstpic) {
		String hql = "from Firstpage where id = '"+id+"'";
		Firstpage firstpage = firstPageDao.get(hql);
		PfirstPage pfirstPage = new PfirstPage();
		BeanUtils.copyProperties(firstpage, pfirstPage);
		pfirstPage.setFirstpic(firstpic);
		BeanUtils.copyProperties(pfirstPage, firstpage);
		firstPageDao.saveOrUpdate(firstpage);
	}
	@Override
	public void changePics(String id, String fieldName, String path) {
		String hql = "from Firstpage where id = '"+id+"'";
		Firstpage firstpage = firstPageDao.get(hql);
		PfirstPage pfirstPage = new PfirstPage();
		BeanUtils.copyProperties(firstpage, pfirstPage);
		if(fieldName.equals("pic1")){
			pfirstPage.setPic1(path);
		}else if(fieldName.equals("pic2")){
			pfirstPage.setPic2(path);
		}else if(fieldName.equals("pic3")){
			pfirstPage.setPic3(path);
		}else if(fieldName.equals("pic4")){
			pfirstPage.setPic4(path);
		}else if(fieldName.equals("pic5")){
			pfirstPage.setPic5(path);
		}
		BeanUtils.copyProperties(pfirstPage, firstpage);
		firstPageDao.saveOrUpdate(firstpage);
	}
	@Override
	public Map<String, List> findPics() {
		String hql = "from Firstpage";
		Firstpage firstpage = firstPageDao.get(hql);
		List<String> picList = new ArrayList<String>();
		picList.add(firstpage.getPic1());
		picList.add(firstpage.getPic2());
		picList.add(firstpage.getPic3());
		picList.add(firstpage.getPic4());
		picList.add(firstpage.getPic5());
		Map<String, List> picMap = new HashMap<String, List>();
		picMap.put("pics", picList);
		return picMap;
	}
	
	@Override
	public void deletePics(String id, String pic) {
		String hql = "from Firstpage where id = '"+id+"'";
		Firstpage firstpage = firstPageDao.get(hql);
		PfirstPage pfirstPage = new PfirstPage();
		pfirstPage.setId(firstpage.getId());
		pfirstPage.setCallus(firstpage.getCallus());
		pfirstPage.setWorkinghours(firstpage.getWorkinghours());
		pfirstPage.setIntroduce(firstpage.getIntroduce());
		pfirstPage.setFirstpic(firstpage.getFirstpic());
		pfirstPage.setPicintroduce(firstpage.getPicintroduce());
		pfirstPage.setPic1(firstpage.getPic1());
		pfirstPage.setPic2(firstpage.getPic2());
		pfirstPage.setPic3(firstpage.getPic3());
		pfirstPage.setPic4(firstpage.getPic4());
		pfirstPage.setPic5(firstpage.getPic5());
		if(pic.equals("pic1")){
			pfirstPage.setPic1("");
		}else if(pic.equals("pic2")){
			pfirstPage.setPic2("");
		}else if(pic.equals("pic3")){
			pfirstPage.setPic3("");
		}else if(pic.equals("pic4")){
			pfirstPage.setPic4("");
		}else if(pic.equals("pic5")){
			pfirstPage.setPic5("");
		}
		BeanUtils.copyProperties(pfirstPage, firstpage);
		firstPageDao.saveOrUpdate(firstpage);
	}

}
