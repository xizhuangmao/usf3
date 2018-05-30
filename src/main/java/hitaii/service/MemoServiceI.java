package hitaii.service;

import java.util.List;

import hitaii.model.Memo;
import hitaii.model.Whesdtl;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pbooknum;
import hitaii.pageModel.Pmemo;
import hitaii.pageModel.Pwhesdtl;

public interface MemoServiceI {

	public DataGrid findAllVehicleMemo(Pmemo pmemo);

	public DataGrid findAllUooMemo(Pmemo pmemo);

	public String saveVehicleMemo(Pwhesdtl pwhesdtl);
	
	//根据id删除车辆信息
	public String deleteVehicleInfoById(Pmemo pmemo);
	//车辆memo查询
	public List<Pmemo> findVehicleMemoByVin(Pmemo pmemo);

	public DataGrid findUooMemoByBooknumId(Pmemo pmemo);

	public String addBooknumMemo(Pbooknum booknum);

	public List<Memo> getMemoByVin(Whesdtl whesdtl);

	public void updateMemo(List<Memo> memos, Pwhesdtl pwhesdtl);

}
