package hitaii.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hitaii.model.Firstpage;
import hitaii.pageModel.Json;
import hitaii.pageModel.PfirstPage;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.FirstPageServiceI;
import hitaii.util.WhesdtlUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * @author qc
 * 
 * 时间：20160120
 * 
 * 首页Action
 */
@Namespace("/")
@Action(value = "firstPageAction")
public class FirstPageAction extends BaseAction implements ModelDriven<PfirstPage>{
	private PfirstPage pfirstPage = new PfirstPage();
	@Override
	public PfirstPage getModel() {
		return pfirstPage;
	}
	private FirstPageServiceI firstPageService;
	
	public FirstPageServiceI getFirstPageService() {
		return firstPageService;
	}
	@Autowired
	public void setFirstPageService(FirstPageServiceI firstPageService) {
		this.firstPageService = firstPageService;
	}

	public void updateOrSaveFirstPage(){
		firstPageService.updateOrSaveFirstPage(pfirstPage);
	}
	
	public void findFirstPage(){
		List<Firstpage> firstPage = firstPageService.findFirstPage();
		for(int i=0;i<firstPage.size();i++){
			firstPage.get(i).setIntroduce(firstPage.get(i).getIntroduce().replaceAll("</p>", ""));
			firstPage.get(i).setIntroduce(firstPage.get(i).getIntroduce().replaceAll("<p>", ""));
			firstPage.get(i).setIntroduce(firstPage.get(i).getIntroduce().replaceAll("<br/>", ""));
			firstPage.get(i).setIntroduce(firstPage.get(i).getIntroduce().replaceAll("&nbsp;", ""));
			firstPage.get(i).setIntroduce(firstPage.get(i).getIntroduce().trim());
		}
		List<Map> content= new ArrayList<Map>();
		Map<String, String> cont1 = new HashMap<String, String>();
		Map<String, String> cont2 = new HashMap<String, String>();
		for(int i=0;i<firstPage.size();i++){
			String picintroduce = null;
			String callus = null;		
			String workinghours = null;
			String introduce = null;
			if(null != firstPage.get(i).getIntroduce() && !firstPage.get(i).getIntroduce().isEmpty()){
				introduce = firstPage.get(i).getIntroduce();
			}
			String firstpic = "<p>" + firstPage.get(i).getFirstpic() + "</p>";
			
			String pic1 = "<p>" + firstPage.get(i).getPic1() + "</p>";
			String pic2 = "<p>" + firstPage.get(i).getPic2() + "</p>";
			String pic3 = "<p>" + firstPage.get(i).getPic3() + "</p>";
			String pic4 = "<p>" + firstPage.get(i).getPic4() + "</p>";
			String pic5 = "<p>" + firstPage.get(i).getPic5() + "</p>";	
			if(null != firstPage.get(i).getCallus() && !firstPage.get(i).getCallus().isEmpty()){
				callus = firstPage.get(i).getCallus();
				callus = callus.replaceAll("<p>", "");
				callus = callus.replaceAll("</p>", "");
				callus = callus.replaceAll("<br/>", "");
				callus = callus.replaceAll("&nbsp;", "");
				callus = callus.trim();
			}
			if(null != firstPage.get(i).getWorkinghours() && !firstPage.get(i).getWorkinghours().isEmpty()){
				workinghours = firstPage.get(i).getWorkinghours();
				workinghours = workinghours.replaceAll("<p>", "");
				workinghours = workinghours.replaceAll("</p>", "");
				workinghours = workinghours.replaceAll("<br/>", "");
				workinghours = workinghours.replaceAll("&nbsp;", "");
				workinghours = workinghours.trim();
			}
			if(null != firstPage.get(i).getPicintroduce() && !firstPage.get(i).getPicintroduce().isEmpty()){
				picintroduce = firstPage.get(i).getPicintroduce();
				picintroduce = picintroduce.replaceAll("<p>", "");
				picintroduce = picintroduce.replaceAll("</p>", "");
				picintroduce = picintroduce.replaceAll("<br/>", "");
				picintroduce = picintroduce.replaceAll("&nbsp;", "");
				picintroduce = picintroduce.trim();
			}
			String pic = firstpic + "<p>" + picintroduce + "</p>" + pic1 + pic2 + pic3 + pic4 + pic5;
			pic = pic.replaceAll("<p>null</p>", "");
			cont1.put("content","<p>" + callus + "</p>" + "<p>" + workinghours + "</p>" + pic);
			cont1.put("firstId", "callwork");
			cont1.put("id", firstPage.get(i).getId());
			content.add(cont1);

//			cont2.put("content", workinghours);
//			cont2.put("firstId", "workinghours");
//			cont2.put("id", firstPage.get(i).getId());
//			content.add(cont2);
			
			cont2.put("content", introduce);
			cont2.put("firstId", "introduce");
			cont2.put("id", firstPage.get(i).getId());
			content.add(cont2);
			
			
//			cont4.put("content", pic);
//			cont3.put("firstId", "pic");
//			cont3.put("id", firstPage.get(i).getId());
//			content.add(cont4);
		}
		super.writeJson(content);
	}
	
	public void findEditFirstPage(){
		Firstpage firstPage = firstPageService.findEditFirstPage();
		super.writeJson(firstPage);
	}
	
	public void saveOrUpdateFirstPageCall(){
		Json j = new Json();
		firstPageService.saveOrUpdateFirstPageCall(pfirstPage);
		j.setMsg("edit success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	public void saveOrUpdateFirstPageWork(){
		Json j = new Json();
		firstPageService.saveOrUpdateFirstPageWork(pfirstPage);
		j.setMsg("edit success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	public void saveOrUpdateFirstPagePicIntro(){
		Json j = new Json();
		firstPageService.saveOrUpdateFirstPagePicIntro(pfirstPage);
		j.setMsg("edit success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	public void saveOrUpdateIntroduce(){
		Json j = new Json();
		firstPageService.saveOrUpdateIntroduce(pfirstPage);
		j.setMsg("edit success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	 /**  
     * @author qc
     * 修改滚动图片
     * 
     * 时间:20160317
     * @throws IOException 
     */  
	public void upPics() throws IOException {  
		HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		String id = request.getParameter("id");
		PfirstPage firstPage = firstPageService.findFirstPageById(id);
		
		String picsPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadList");
		File picsfile =new File(picsPath);
		if (!picsfile.exists()) {
			 
		}else{
			deleteFile(picsfile);
		}
		
    	List<FileItem> list = null;
    	String picPath = "C:/";
    	WhesdtlUtil.isExists(picPath);
    	picPath = picPath + "/" + "USFL" + "/";
    	WhesdtlUtil.isExists(picPath);
    	picPath = picPath + "firstPagePics";
    	WhesdtlUtil.isExists(picPath);
    	
    	String src = "";
    	/* FileItemFactory是FileItem的工厂，不过它只是个interface，
		 * 有两个实现DiskFileItemFactory和DefaultFileItemFactory；
		 * 但是api里说："Deprecated. Use DiskFileItemFactory instead." */
		FileItemFactory itemFactory = new DiskFileItemFactory();
		/* FileUpload类是用来处理文件的上传，而ServletFileUpload是它的子类,
		 * 可以判断和解析前端的表单类型和表单项信息 */
		
		ServletFileUpload upload = new ServletFileUpload(itemFactory);
    	try {
			//parseRequest将前端的表单项项解析为FileItem列表
			list = upload.parseRequest(request);
			Iterator<FileItem> it = list.iterator();
			while (it.hasNext()) {
				FileItem item = it.next();
				/* 过滤下,只有表单项中类型为图片的才保存 */
				if ("image/jpeg".equals(item.getContentType())
						|| "image/gif".equals(item.getContentType())
						|| "image/png".equals(item.getContentType())) {
					InputStream inStream = null;
					OutputStream outStream = null;
					try {
						inStream = item.getInputStream();
						byte[] b = new byte[1024];
						int rb = inStream.read(b);
						File file=new File(picPath, item.getName());
						outStream = new FileOutputStream(file);
						while (rb != -1) {
							outStream.write(b);
							rb = inStream.read(b);
						}
						outStream.flush();
						
						src= picPath+item.getName();
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						try {
							inStream.close();
							outStream.close();
							firstPageService.changePics(id, item.getFieldName(), picPath + "/" + item.getName());
							super.writeJson("上传成功!");
						} catch (Exception e2) {
						}
					}
				}else{
					src="上传格式不对  重新上传...";
					super.writeJson(src);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	    
	}  
	
	 /**  
     * @author qc
     * 修改首页大图
     * 
     * 时间:20160316
     * @throws IOException 
     */  
	public void upPic(){
		HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		String id = request.getParameter("id");
		PfirstPage firstPage = firstPageService.findFirstPageById(id);
		
    	List<FileItem> list = null;
    	String picPath = "C:/";
    	WhesdtlUtil.isExists(picPath);
    	picPath = picPath + "/" + "USFL" + "/";
    	WhesdtlUtil.isExists(picPath);
    	picPath = picPath + "firstPagePics";
    	WhesdtlUtil.isExists(picPath);
    			
    	String src = "";
    	/* FileItemFactory是FileItem的工厂，不过它只是个interface，
		 * 有两个实现DiskFileItemFactory和DefaultFileItemFactory；
		 * 但是api里说："Deprecated. Use DiskFileItemFactory instead." */
		FileItemFactory itemFactory = new DiskFileItemFactory();
		/* FileUpload类是用来处理文件的上传，而ServletFileUpload是它的子类,
		 * 可以判断和解析前端的表单类型和表单项信息 */
		
		ServletFileUpload upload = new ServletFileUpload(itemFactory);
    	try {
			//parseRequest将前端的表单项项解析为FileItem列表
			list = upload.parseRequest(request);
			Iterator<FileItem> it = list.iterator();
			while (it.hasNext()) {
				FileItem item = it.next();
				/* 过滤下,只有表单项中类型为图片的才保存 */
				if ("image/jpeg".equals(item.getContentType())
						|| "image/gif".equals(item.getContentType())
						|| "image/png".equals(item.getContentType())) {
					InputStream inStream = null;
					OutputStream outStream = null;
					try {
						inStream = item.getInputStream();
						byte[] b = new byte[1024];
						int rb = inStream.read(b);
						File file=new File(picPath, item.getName());
						outStream = new FileOutputStream(file);
						while (rb != -1) {
							outStream.write(b);
							rb = inStream.read(b);
						}
						outStream.flush();
						
						src= picPath+item.getName();
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						try {
							inStream.close();
							outStream.close();
							super.writeJson("上传成功!");
							firstPageService.changeFirstPic(id, picPath + "/" + item.getName());
						} catch (Exception e2) {
						}
					}
				}else{
					src="上传格式不对  重新上传...";
					super.writeJson(src);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	 /**  
     * @author qc
     * 查询滚动图片
     * 
     * 时间:20160318
     * @throws IOException 
     */  
	public void findPics(){
		Map<String, List> picMap = firstPageService.findPics();
		super.writeJson(picMap);
	}
	
	/**  
     * @author qc
     * 查询滚动图片
     * 
     * 时间:20160318
     * @throws IOException 
     */  
	public void deletePics(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String pic = request.getParameter("pic");
		String id = request.getParameter("id");
		Json j = new Json();
		firstPageService.deletePics(id,pic);
		j.setMsg("delete success");
		j.setSuccess(true);
		
		super.writeJson(j);
	}
	
	/**  
     * @author qc
     * 预览图片
     * 
     * 时间:20160324
     * @throws IOException 
     */  
	public void PreviewImg(){
		HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		String pic = request.getParameter("pic");
		
    	List<FileItem> list = null;
    	
    	String picPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadList"); 
    	WhesdtlUtil.isExists(picPath);
    			
    	String src = "";
		FileItemFactory itemFactory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(itemFactory);
    	try {
			list = upload.parseRequest(request);
			Iterator<FileItem> it = list.iterator();
			while (it.hasNext()) {
				FileItem item = it.next();
				/* 过滤下,只有表单项中类型为图片的才保存 */
				if ("image/jpeg".equals(item.getContentType())
						|| "image/gif".equals(item.getContentType())
						|| "image/png".equals(item.getContentType())) {
					InputStream inStream = null;
					OutputStream outStream = null;
					try {
						inStream = item.getInputStream();
						byte[] b = new byte[1024];
						int rb = inStream.read(b);
						File file=new File(picPath, item.getName());
						outStream = new FileOutputStream(file);
						while (rb != -1) {
							outStream.write(b);
							rb = inStream.read(b);
						}
						outStream.flush();
						
						src= picPath+item.getName();
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						try {
							inStream.close();
							outStream.close();
							Map<String, String> map = new HashMap<String, String>();
							if(pic.equals("pic1")){
								map.put("pic", "pic1");
							}else if(pic.equals("pic2")){
								map.put("pic", "pic2");
							}else if(pic.equals("pic3")){
								map.put("pic", "pic3");
							}else if(pic.equals("pic4")){
								map.put("pic", "pic4");
							}else if(pic.equals("pic5")){
								map.put("pic", "pic5");
							}
							map.put("path", picPath + "\\" + item.getName());
							super.writeJson(map);
						} catch (Exception e2) {
						}
					}
				}else{
					src="上传格式不对  重新上传...";
					super.writeJson(src);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
	
	 /** 
     * 通过递归调用删除一个文件夹及下面的所有文件 
     * @param file 
     */  
    public static void deleteFile(File file){  
        if(file.isFile()){//表示该文件不是文件夹  
            file.delete();  
        }else{  
            //首先得到当前的路径  
            String[] childFilePaths = file.list();  
            for(String childFilePath : childFilePaths){  
                File childFile=new File(file.getAbsolutePath()+"\\"+childFilePath);  
                deleteFile(childFile);  
            }  
            file.delete();  
        }  
    }
}
