package hitaii.action;

import org.springframework.beans.factory.annotation.Autowired;

import hitaii.pageModel.Pauthorize;
import hitaii.service.AuthorizeServiceI;

import com.opensymphony.xwork2.ModelDriven;

public class AuthorizeAction extends BaseAction implements ModelDriven<Pauthorize>{

	private Pauthorize pauthorize = new Pauthorize();
	@Override
	public Pauthorize getModel() {
		// TODO Auto-generated method stub
		return pauthorize;
	}
	
	AuthorizeServiceI authorizeService ;
	public AuthorizeServiceI getAuthorizeService() {
		return authorizeService;
	}

	@Autowired
	public void setAuthorizeService(AuthorizeServiceI authorizeService) {
		this.authorizeService = authorizeService;
	}
	
	/**
	 * 获取审核的datagrid
	 */
	public void datagrid(){
		
	}

}
