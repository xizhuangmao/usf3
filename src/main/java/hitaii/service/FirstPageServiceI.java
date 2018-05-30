package hitaii.service;

import java.util.List;
import java.util.Map;

import hitaii.model.Firstpage;
import hitaii.pageModel.PfirstPage;

public interface FirstPageServiceI {

	public void updateOrSaveFirstPage(PfirstPage pfirstPage);
	
	//查找公司信息
	public List<Firstpage> findFirstPage();
	
	//查找修改公司信息
	public Firstpage findEditFirstPage();
	
	//修改Introduce
	public void saveOrUpdateIntroduce(PfirstPage pfirstPage);
	
	//根据id查询firstpage
	public PfirstPage findFirstPageById(String id);
	
	//修改firstpic
	public void changeFirstPic(String id, String firstpic);
	
	//修改Pics
	public void changePics(String id, String fieldName, String path);

	//查询滚动图片
	public Map<String, List> findPics();

	//修改公司联系方式
	public void saveOrUpdateFirstPageCall(PfirstPage pfirstPage);
	
	//修改公司作息时间
	public void saveOrUpdateFirstPageWork(PfirstPage pfirstPage);

	//修改图片简介
	public void saveOrUpdateFirstPagePicIntro(PfirstPage pfirstPage);

	//删除滚动图片
	public void deletePics(String id, String pic);

}
