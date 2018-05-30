package hitaii.service;

import hitaii.model.Company;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcompany;

import java.util.List;

public interface CompanyServiceI {

	public List<Pcompany> findAllCompanies();
	
	/**
     * Company的所有DataGrid数据
     */
	public DataGrid getAllCompanyDataGrid(Pcompany pcompany);

	public List<Pcompany> findWhescompany();

	public List<Pcompany> findPrealertWhescompany();

	public Json deleteCompanyById(Pcompany pcompany);

	public Json addCompany(Pcompany pcompany);

	public Json findCompanyById(Pcompany pcompany);

	public Json updateCompany(Pcompany pcompany);
	
	//查询company
	public List<Company> findCompany();

	public List<Company> findAllWhes();

}
