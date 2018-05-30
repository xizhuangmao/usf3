package hitaii.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hitaii.dao.NvoccDaoI;
import hitaii.model.Nvocc;
import hitaii.service.BcompanyServiceI;

@Service("bcompanyService")
public class BcompanyServiceImpl implements BcompanyServiceI {
	
	private NvoccDaoI nvoccDao;
	
	public NvoccDaoI getNvoccDao() {
		return nvoccDao;
	}

	@Autowired
	public void setNvoccDao(NvoccDaoI nvoccDao) {
		this.nvoccDao = nvoccDao;
	}


	@Override
	public List<Map<String, String>> findAllBookingCompany() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>> ();
		String hql = "from Nvocc";
		List<Nvocc> nvoccs = nvoccDao.find(hql);
		for(int i =0;i<nvoccs.size();i++){
			Map<String, String> map = new HashMap<String,String>();
			map.put("fullName",  nvoccs.get(i).getFullname());
			map.put("id", nvoccs.get(i).getId());
			list.add(map);
		}
		return list;
	}

}
