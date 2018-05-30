package hitaii.action;

import java.util.List;

import hitaii.pageModel.Json;
import hitaii.pageModel.Pcompany;
import hitaii.service.CompanyServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author qc
 * 
 * 时间：20160705
 * 
 * CompanyAction
 */
@Namespace("/")
@Action(value = "companyAction")
public class CompanyAction extends BaseAction implements ModelDriven<Pcompany> {
	private Pcompany pcompany = new Pcompany();
	@Override
	public Pcompany getModel() {
		return pcompany;
	}
	
	private CompanyServiceI companyService;
	
	public CompanyServiceI getCompanyService() {
		return companyService;
	}
	
	@Autowired
	public void setCompanyService(CompanyServiceI companyService) {
		this.companyService = companyService;
	}

	//查找所有的companies
	public void findAllCompanies(){
		List<Pcompany> pcompanies = companyService.findAllCompanies();
		super.writeJson(pcompanies);
	}
	
	/**
     * Company的所有DataGrid数据
     * by zw 20160705
     */
    public void getCompanyDataGrid(){
    	super.writeJson(companyService.getAllCompanyDataGrid(pcompany));	
    }
	
	/**
	 * By qc
	 * 2016-04-26
	 * 根据users查询company
	 */
	public void findCompany(){
		super.writeJson(companyService.findCompany());
	}
	
	/**
     * 删除company
     * by zw 20160705
     */
    public void deleteCompanyById(){
    	Json j = companyService.deleteCompanyById(pcompany);
    	super.writeJson(j);
    }
    
    /**
     * 增加company
     * by qc 20160810
     */
    public void addCompany(){
    	Json j = companyService.addCompany(pcompany);
    	super.writeJson(j);
    }
    
    /**
     * 根据id查询company
     * by qc 20160810
     */
    public void findCompanyById(){
    	Json j = companyService.findCompanyById(pcompany);
    	super.writeJson(j);
    }
    
    /**
     * 修改company
     * by qc 20160810
     */
    public void updateCompany(){
    	Json j = companyService.updateCompany(pcompany);
    	super.writeJson(j);
    }
    
    /**
     * 根据权限查询whes
     * by qc 20160810
     */
    public void findWhescompany(){
    	super.writeJson(companyService.findWhescompany());
    }

}
