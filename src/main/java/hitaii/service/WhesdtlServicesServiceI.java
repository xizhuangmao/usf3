package hitaii.service;

import java.util.List;

import hitaii.model.WhesdtlServices;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pservices;
import hitaii.pageModel.PwhesdtlServices;

public interface WhesdtlServicesServiceI {

	public Json updateWhesdtlServices(PwhesdtlServices pwhesdtlServices);

	public Json updateWhesdtlServicesPay(PwhesdtlServices pwhesdtlServices);

	public WhesdtlServices updateWhesdtlServicesPayState(
			PwhesdtlServices pwhesdtlServices);

	public List<WhesdtlServices> findWhesdtlServicesById(Pservices pservices);

	public List<WhesdtlServices> findServicesByWhesdtlId(PwhesdtlServices pwhesdtlServices);

}
