package hitaii.action;

import hitaii.dao.PicDaoI;
import hitaii.model.Company;
import hitaii.model.Memo;
import hitaii.model.Pic;
import hitaii.model.Users;
import hitaii.model.Whesdtl;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcompany;
import hitaii.pageModel.Pmake;
import hitaii.pageModel.Pmemo;
import hitaii.pageModel.Ppic;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.CompanyServiceI;
import hitaii.service.CustomerServiceI;
import hitaii.service.MakeServiceI;
import hitaii.service.MemoServiceI;
import hitaii.service.ModelServiceI;
import hitaii.service.UserServiceI;
import hitaii.service.WhesServiceI;
import hitaii.service.WhesdtlServiceI;
import hitaii.util.WhesdtlUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 *	@author qc
 * 
 * 时间：20160104
 * 
 * 仓库Action
 */
@Namespace("/")
@Action(value = "warehouseAction")
public class WarehouseAction extends BaseAction implements ModelDriven<Pwhesdtl>{
	private Pwhesdtl pwhesdtl = new Pwhesdtl();
	@Override
	public Pwhesdtl getModel() {
		return pwhesdtl;
	}
	
	private UserServiceI userService;
	private ModelServiceI modelService;
	private MakeServiceI makeService;
    private WhesServiceI whesService;
    private WhesdtlServiceI whesdtlService;
    private CustomerServiceI customerService;
    private MemoServiceI memoService;
    private CompanyServiceI companyService;
    
	public CompanyServiceI getCompanyService() {
		return companyService;
	}
	@Autowired
	public void setCompanyService(CompanyServiceI companyService) {
		this.companyService = companyService;
	}
	public MemoServiceI getMemoService() {
		return memoService;
	}
	@Autowired
	public void setMemoService(MemoServiceI memoService) {
		this.memoService = memoService;
	}
	public CustomerServiceI getCustomerService() {
		return customerService;
	}
	@Autowired
	public void setCustomerService(CustomerServiceI customerService) {
		this.customerService = customerService;
	}
	public WhesdtlServiceI getWhesdtlService() {
		return whesdtlService;
	}
	@Autowired
	public void setWhesdtlService(WhesdtlServiceI whesdtlService) {
		this.whesdtlService = whesdtlService;
	}
	public WhesServiceI getWhesService() {
		return whesService;
	}
	@Autowired
	public void setWhesService(WhesServiceI whesService) {
		this.whesService = whesService;
	}
	public UserServiceI getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	public ModelServiceI getModelService() {
		return modelService;
	}
	@Autowired
	public void setModelService(ModelServiceI modelService) {
		this.modelService = modelService;
	}

	public MakeServiceI getMakeService() {
		return makeService;
	}
	@Autowired
	public void setMakeService(MakeServiceI makeService) {
		this.makeService = makeService;
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160331
	 * 查询Make、Whes、Customer
	 * @throws IOException
	 */
	public void findAllWhesMake() throws IOException{
		List<Pmake> makes = makeService.findAllMake();
		List<Pcompany> whes = companyService.findWhescompany();
		List<Users> customers = userService.getCustomerName();
		List<Company> companies = companyService.findAllWhes();
		
		Map<String, Object> all = new HashMap<String, Object>();
		all.put("make", makes);
		all.put("whes", whes);
		all.put("customer", customers);
		all.put("whesCompany", companies);
		super.writeJson(all);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160805
	 * 查询Make、Whes、Customer
	 * @throws IOException
	 */
	public void findPrealertAllWhesMake() throws IOException{
		List<Pmake> makes = makeService.findAllMake();
		List<Pcompany> whes = companyService.findPrealertWhescompany();
		List<Users> customers = userService.getCustomerName();

		Map<String, Object> all = new HashMap<String, Object>();
		all.put("make", makes);
		all.put("whes", whes);
		all.put("customer", customers);
		super.writeJson(all);
	}

	/** 
	 * @author qc
	 * 
	 * 时间:20160108
	 * 查询仓库车辆信息
	 * @throws IOException
	 */
	public void getAlertWhesdtlDatagrid() throws IOException{
		DataGrid d = whesdtlService.getAlertWhesdtlDatagrid(pwhesdtl, null);
		super.writeJson(d);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160108
	 * 查询仓库未下订单车辆信息
	 * @throws IOException
	 */
	public void getAlertWhesdtlNoOrdersDatagrid() throws IOException{
		String type = "noOrders";
		DataGrid d = whesdtlService.getAlertWhesdtlDatagrid(pwhesdtl, type);
		super.writeJson(d);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160108
	 * 查询仓库未下订单车辆总数
	 * @throws IOException
	 */
	public void getAlertWhesdtlNoOrdersCount() throws IOException{
		DataGrid d = whesdtlService.getAlertWhesdtlNoOrdersCount(pwhesdtl);
		super.writeJson(d);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160413
	 * 查询仓库预录入车辆信息
	 * @throws IOException
	 */
	public void getPreAlertWhesdtl() throws IOException{
		DataGrid d = whesdtlService.getPreAlertWhesdtl(pwhesdtl);
		super.writeJson(d);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160413
	 * 删除仓库预录入车辆信息
	 * @throws IOException
	 */
	public void deletePreAlertVehicle() throws IOException{
		Json j = new Json();
		try {
			Pwhesdtl pWhesdtl = whesdtlService.findVehicleInfoById(pwhesdtl);
			if(pWhesdtl.getFlowstate().equals("2")){
				j.setMsg("This vehicle has been put in warehouse, please refresh");
				j.setSuccess(true);
			}else{
				whesdtlService.deletePreAlertVehicle(pwhesdtl);
				j.setMsg("success");
				j.setSuccess(true);
			}
			super.writeJson(j);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
			j.setSuccess(false);
			super.writeJson(j);
		}
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160108
	 * 查询仓库车辆信息
	 * @throws IOException
	 */
	public void getWhesdtDatagrid() throws IOException{
		DataGrid d = whesdtlService.getWhesdtDatagrid(pwhesdtl);
		super.writeJson(d);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:201601014
	 * 流显示图片
	 * @throws IOException
	 */
	public void showWhesdtlPics() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
        // 把得到的文件的集合通过循环的方式读取并放在指定的路径下 
		String path = request.getParameter("path");
		path = new String(path.getBytes("iso-8859-1"),"UTF-8");
		if(null != path && !path.isEmpty()){
			byte data[] = readFile(path);    
	    	response.setContentType("image/jpg;charset=utf-8"); //设置返回的文件类型      //传入一个图片地址
	    	OutputStream os = response.getOutputStream();    
	    	os.write(data);    
	    	os.flush();    
	    	os.close();  
		}
    	 
	}
	
	/** 
	 * @author qc
	 * 
	 * 从文件地址，读取文件的Byte数组 
	 * @param filename 
	 * @return 
	 * @throws IOException 
	 */  
	public static byte[] readFile(String filename) throws IOException {  
	    if( filename==null || filename.equals("") ){  
	        throw new NullPointerException("无效的文件路径");  
	    }  
	    File file =new File(filename);  
	    long len = file.length();  
	    byte[] bytes = new byte[(int)len];  
	  
	    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));  
	    int r = bufferedInputStream.read( bytes );  
	    if (r != len)  
	      throw new IOException("读取文件不正确");  
	    bufferedInputStream.close();  
	    return bytes;  
	}  
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160108
	 * 根据orderId查询订单信息
	 * @throws IOException
	 */
	public void getCustomerOrders() throws IOException{
		DataGrid d = whesdtlService.getAllOrders(pwhesdtl);
		super.writeJson(d);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160108
	 * 连表查询（Whesdtl、Booknum）
	 * @throws IOException
	 */
	public void findWhesdtlWithBooknum(){
		DataGrid d = whesdtlService.findWhesdtlWithBooknum(pwhesdtl);
		super.writeJson(d);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160108
	 * 车辆信息预填写
	 */
	public void addPrealert(){
		Whesdtl whesdtl = whesdtlService.findAllVehicle(pwhesdtl);
		Json j = new Json();
		if(null != whesdtl){
			j.setMsg("Duplication of vin! Please contact your administrator.");
		}else{
			whesdtlService.saveVehicleInf(pwhesdtl);
			j.setMsg("success");
		}
		super.writeJson(j);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160108
	 * 车辆入库
	 */
	public void addVehicleInfToWarehouse(){
		Whesdtl whesdtl = whesdtlService.findAllVehicle(pwhesdtl);
		Users user = userService.getCustomer(pwhesdtl);
		Json j = new Json();
		if(null != whesdtl){
			j.setMsg("Duplication of vin! Please contact your administrator.");
		}else if(null == user){
			j.setMsg("User not exist.");
		}else{
			Pwhesdtl pWhesdtl = whesdtlService.saveOrUpdateVehicleInf(pwhesdtl);
			memoService.saveVehicleMemo(pwhesdtl);
			j.setMsg("success");
			j.setObj(pWhesdtl);
		}
		super.writeJson(j);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160216
	 * 查询预填写车辆信息
	 */
	public void findVehicleInfInWarehouse(){
		List<Whesdtl> vehicleInfs = whesdtlService.findVehicleInfByFlowState();
		super.writeJson(vehicleInfs);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160216
	 * 根据user查询预填写车辆信息
	 */
	public void findVehicleInfByUsers(){
		DataGrid vehicleInfs = whesdtlService.findVehicleInfByUsers(pwhesdtl);
		super.writeJson(vehicleInfs);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160112
	 * 查看车辆信息
	 * @throws IOException 
	 * @throws FileNotFoundException
	 */
	public void findVehicleInfo() throws FileNotFoundException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String vin = request.getParameter("vin");
		Pwhesdtl whesdtls = whesdtlService.findVehicleInfoByVin(vin);
		List<Ppic> pics = whesdtlService.findPicsByVin(vin);
		
		Map<String, Object> all = new HashMap<String, Object>();
		all.put("whesdtl", whesdtls);
		all.put("pic", pics);
		super.writeJson(all);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160115
	 * 查看warehouse车辆信息
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void findWareHouseVehicleInfo() throws FileNotFoundException, IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		String vin = request.getParameter("vin");
		String type = request.getParameter("type");
		DataGrid d = whesdtlService.findWareHouseVehicleInfo(vin);
		
		List<Ppic> pics = whesdtlService.findPicsByVinAndType(vin, type);
		
		Map<String, Object> all = new HashMap<String, Object>();
		all.put("whesdtl", d);
		all.put("pic", pics);
		super.writeJson(all);
	}
	
	/** 
	 * @author qc
	 * 
	 * 时间:20160113
	 * 打包下载车辆图片
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void downVehiclePics() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		String picNames = ServletActionContext.getRequest().getParameter("picName"); //得到要下载的文件名集合
		String path = null;
		List<File> file1 = new ArrayList<File>();
		for(String picName : picNames.split(",")){
			 picName = new String(picName.getBytes("iso8859-1"),"UTF-8");
			 
			 Pic pic = whesdtlService.findPicPathByName(picName);
			 path = pic.getPath();
			 File file = new File(path);
			 file1.add(file);
		}
		//生成的ZIP文件名为vehicle.zip   
        String tmpFileName = "vehicle.zip";   
        byte[] buffer = new byte[1024];   
        String strZipPath = "C:\\"+tmpFileName;   
        try {   
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(   
                    strZipPath));   
            // 需要同时下载的多个文件  
           
            for (int i = 0; i < file1.size(); i++) {   
                FileInputStream fis = new FileInputStream(file1.get(i));   
                out.putNextEntry(new ZipEntry(file1.get(i).getName()));   
                int len;   
                // 读入需要下载的文件的内容，打包到zip文件   
                while ((len = fis.read(buffer)) > 0) {   
                    out.write(buffer, 0, len);   
                }   
                out.closeEntry();   
                fis.close();   
            }   
            out.close();   
            this.downFile(getResponse(), tmpFileName);   
        } catch (Exception e) {   
             
        }   
	}
	/**
	 * @author qc 
	 *  
	 * 时间:20160114
     * 获取Response  
     * @return  
     */  
    private HttpServletResponse getResponse() {   
        return ServletActionContext.getResponse();   
    }   
    
    /**  
     * @author qc
     * 文件下载  
     * 
     * 时间:20160114
     * @param response  
     * @param str  
     */  
    private void downFile(HttpServletResponse response, String str) {   
        try {   
            String path = "C:\\"+str;   
            File file = new File(path);   
            if (file.exists()) {   
                InputStream ins = new FileInputStream(path);   
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面   
                OutputStream outs = response.getOutputStream();// 获取文件输出IO流   
                BufferedOutputStream bouts = new BufferedOutputStream(outs);   
                response.setContentType("application/x-download");// 设置response内容的类型   
                response.setHeader(   
                        "Content-disposition",   
                        "attachment;filename="  
                                + URLEncoder.encode(str, "UTF-8"));// 设置头部信息   
                int bytesRead = 0;   
                byte[] buffer = new byte[8192];   
                // 开始向网络传输文件流   
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {   
                    bouts.write(buffer, 0, bytesRead);   
                }   
                bouts.flush();// 这里一定要调用flush()方法   
                ins.close();   
                bins.close();   
                outs.close();   
                bouts.close();
                File fileZip = new File("C:\\"+str); 
                if(fileZip.exists()){
                	fileZip.delete(); 
                }
            } else {   
                response.sendRedirect("../error.jsp");   
            }   
        } catch (IOException e) {   
        }   
    }  
    
    /**  
     * @author qc
     * 删除车辆图片 
     * 
     * 时间:20160115
     */  
    public void removeWareHousePic(){
    	Pwhesdtl pwhesdtls = whesdtlService.removeWareHousePic(pwhesdtl);
    	Json j = new Json();
    	j.setObj(pwhesdtls);
    	j.setMsg("delete success");
    	super.writeJson(j);
    }
    
    /**  
     * @author qc
     * 修改warehouse展示界面车辆图片 
     * 
     * 时间:20160118
     */  
    public void changeWareHousePic(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String picPath = request.getParameter("picPath");
    	String picVin = request.getParameter("picVin");
    	Pwhesdtl pwhesdtl = whesdtlService.changeWareHousePic(picPath, picVin);
    	super.writeJson(pwhesdtl);
    }
    
    /**  
     * @author qc
     * 增加车辆图片 
     * 
     * 时间:20160118
     * @throws IOException 
     */  
    public void upPics() throws IOException{
    	HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); 
		String vin = request.getParameter("vin");
		String type = request.getParameter("type");
		Pwhesdtl pWhesdtl =  whesdtlService.findwhesdtlByVin(vin);
		
    	List<FileItem> list = null;
    	String picPath = "C:/";
    	WhesdtlUtil.isExists(picPath);
    	picPath = picPath + "/" + "USFL" + "/";
    	WhesdtlUtil.isExists(picPath);
    	if(null == pWhesdtl.getMake() || pWhesdtl.getMake().isEmpty()){
    		pWhesdtl.setMake("DEFAULT");
    	}
    	picPath = picPath + pWhesdtl.getMake() + "/";
    	WhesdtlUtil.isExists(picPath);
    	if(null == pWhesdtl.getModel() || pWhesdtl.getModel().isEmpty()){
    		pWhesdtl.setModel("DEFAULT");
    	}
    	picPath = picPath + pWhesdtl.getModel() + "/";
    	WhesdtlUtil.isExists(picPath);
    	picPath = picPath + pWhesdtl.getVin();
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
				String path = UUID.randomUUID().toString() + item.getName();
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
						File file=new File(picPath, path);
						outStream = new FileOutputStream(file);
						while (rb != -1) {
							outStream.write(b);
							rb = inStream.read(b);
						}
						outStream.flush();
						
						src= picPath+path;
					} catch (IOException e) {
						e.printStackTrace();
					}finally{
						try {
							inStream.close();
							outStream.close();
							super.writeJson("upload complete");
							whesdtlService.addPic(vin, path, picPath + "/" + path, type);
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
     * 查看车辆原始图片 
     * 
     * 时间:20160118
     * @throws IOException 
     */  
    public void findWareHousePic(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String id = request.getParameter("id");
    	Ppic pics = whesdtlService.findWareHousePic(id);
    	super.writeJson(pics);
    }
    
    /**  
     * @author qc
     * 查看车辆信息(修改)
     * 
     * 时间:20160121
     * @throws IOException 
     */  
    public void findVehicleInfoByVin(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String id = request.getParameter("id");
    	
    	Pwhesdtl pwhesdtl = whesdtlService.findVehicleInfoById(id);
    	super.writeJson(pwhesdtl);
    }
    
    /**  
     * @author qc
     * 修改车辆信息
     * 
     * 时间:20160121
     * @throws IOException 
     */  
    public void updateWarehouseInfo(){
    	Json j = new Json();
		Whesdtl whesdtlVin = whesdtlService.getWhesdtlByVin(pwhesdtl);
		if(null != whesdtlVin){
			j.setMsg("Duplication of vin! Please contact your administrator.");
		}else{
			Whesdtl whesdtl = whesdtlService.getWhesdtl(pwhesdtl);
			whesdtlService.changePicInfo(whesdtl, pwhesdtl);
			
			List<Memo> memos = memoService.getMemoByVin(whesdtl);
			if(null != memos && memos.size()>0){
				memoService.updateMemo(memos, pwhesdtl);
			}
			String msg = whesdtlService.updateWarehouseInfo(pwhesdtl);
	    	j.setMsg(msg);
		}
    	super.writeJson(j);
    }
    
    /**  
     * @author qc
     * 车辆入库
     * 
     * 时间:20160413
     * @throws IOException 
     */  
    public void inWarehouseInfo(){
    	String msg = whesdtlService.inWarehouseInfo(pwhesdtl);
    	Json j = new Json();
    	j.setMsg(msg);
    	super.writeJson(j);
    }
    
    /**  
     * @author qc
     * 修改预录入车辆信息
     * 
     * 时间:20160413
     * @throws IOException 
     */  
    public void updatePreAlertInfo(){
    	String msg = whesdtlService.updatePreAlertInfo(pwhesdtl);
    	Json j = new Json();
    	j.setMsg(msg);
    	super.writeJson(j);
    }
    
    /**  
     * @author qc
     * 根据FirstName、logName、address查询用户
     * 
     * 时间:20160121
     * @throws IOException 
     */  
    public void findCustomerByFullName(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String name = request.getParameter("q");
    	DataGrid d = whesdtlService.findCustomerByFullName(name, pwhesdtl);
    	super.writeJson(d);
    }
    
    /**  
     * @author qc
     * 车辆转手
     * 
     * 时间:20160405
     * @throws IOException 
     */  
    public void resaleVehicle(){
    	Json j = whesdtlService.resaleVehicle(pwhesdtl);
    	super.writeJson(j);
    }
    
    /**  
     * @author qc
     * 车辆memo查询
     * 
     * 时间:20160510
     * @throws IOException 
     */
    public void findVehicleMemoByVin(){
    	DataGrid d = whesdtlService.findVehicleMemoByVin(pwhesdtl);
    	super.writeJson(d);
    }
    
    /**  
     * @author qc
     * 添加车辆memo
     * 
     * 时间:20160510
     * @throws IOException 
     */
    public void addVehicleMemo(){
    	String msg = memoService.saveVehicleMemo(pwhesdtl);
    	Json j = new Json();
    	j.setMsg(msg);
    	super.writeJson(j);
    }
    
    /**  
     * @author qc
     * 根据id查询车辆信息
     * 
     * 时间:20160510
     * @throws IOException 
     */
    public void findVehicleInfoById(){
    	Pwhesdtl pWhesdtl = whesdtlService.findVehicleInfoById(pwhesdtl);
    	super.writeJson(pWhesdtl);
    }
   
    public void changePicType(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String picId = request.getParameter("id");
    	String type = request.getParameter("type");
    	whesdtlService.changePicType(picId, type);
    }
    
    /**  
     * @author qc
     * 根据id查询memo信息
     * 
     * 时间:20160526
     * @throws IOException 
     */
    public void findMemoContentById(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String memoId = request.getParameter("id");
    	Pmemo pmemo = whesdtlService.findMemoContentById(memoId);
    	super.writeJson(pmemo);
    }
}
