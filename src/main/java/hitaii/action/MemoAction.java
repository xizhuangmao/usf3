package hitaii.action;

import java.io.IOException;
import java.util.List;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pmemo;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.MemoServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "memoAction")
public class MemoAction extends BaseAction implements ModelDriven<Pmemo>{
	private Pmemo pmemo = new Pmemo();
	@Override
	public Pmemo getModel() {
		return pmemo;
	}
	private MemoServiceI memoService;

	public MemoServiceI getMemoService() {
		return memoService;
	}
	@Autowired
	public void setMemoService(MemoServiceI memoService) {
		this.memoService = memoService;
	}
	
	/** 
	 * 查找所有vehicleMemo
	 */
	public void findAllVehicleMemo(){
		DataGrid vehicleMemod = memoService.findAllVehicleMemo(pmemo);
		super.writeJson(vehicleMemod);
	}

	/** 
	 * 查找所有UOOMemo
	 */
	public void findAllUooMemo(){
		DataGrid uooMemod = memoService.findAllUooMemo(pmemo);
		super.writeJson(uooMemod);
	}
	
    /**  
     * @author qc
     * 根据id删除车辆信息
     * 
     * 时间:20160510
     * @throws IOException 
     */
    public void deleteVehicleInfoById(){
    	String msg = memoService.deleteVehicleInfoById(pmemo);
    	Json j = new Json();
    	j.setMsg(msg);
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
    	List<Pmemo> pMemo = memoService.findVehicleMemoByVin(pmemo);
    	super.writeJson(pMemo);
    }
    
    /**  
     * @author qc
     * 根据booknum查询memo
     * 
     * 时间:20160713
     * @throws IOException 
     */
    public void findUooMemoByBooknumId(){
    	DataGrid d = memoService.findUooMemoByBooknumId(pmemo);
    	super.writeJson(d);
    }

}
