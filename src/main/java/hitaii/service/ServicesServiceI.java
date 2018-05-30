package hitaii.service;

import java.util.List;
import java.util.Map;

import hitaii.model.Company;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pservices;

public interface ServicesServiceI {
	//根据Whes和Nvocc查询服务
	public DataGrid findServices(Pservices pservices);
	//根据whes查询services
	//public List<Map<String, String>> findServicesByWhes(String whesId);
	//根据nvoccId查询services
	//public List<Map<String, String>> findServicesByNvoccId(String nvoccId);
	//增加服务
	public String addServices(Pservices pservices);
	//删除服务
	public Json deleteServices(Pservices pservices, Json j);
	//根据id查询services
	public Pservices findServicesById(Pservices pservices);
	//更新服务
	public Pservices updateServices(Pservices pservices);
	//根据vin号查询服务
	public DataGrid findServicesByVin(String vin, String whesdtlId, Pservices pservices);
	//根据Nvocc查询Services
	public List<Pservices> findServicesByNvocc(String nvoccId);
	//根据users查询whes和Nvocc
	public List<Map<String, String>> findWhesAndNvocc();
	//根据根据whesnvocc查询服务
	public DataGrid findServicesBywhesnvocc(Pservices pservices);
	//根据CompanyName号查询服务
	public DataGrid findServicesByCompanyName(String companyId,
			String whesdtlId, Pservices pservices);

}
