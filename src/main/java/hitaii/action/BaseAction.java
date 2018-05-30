package hitaii.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
/**
 * Copy Right Information : hitaii
 * 
 * Function :统一格式
 * 
 * Author : zw
 * 
 * Date : 20160111
 * 
 * Modification history :
 * 
 */

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction {

	public void writeJson(Object object){
		try{
			//禁用循环引用，不然fastjson会循环调用hibernate多对多关系
			SerializerFeature feature = SerializerFeature.DisableCircularReferenceDetect;
			String json =JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss",feature);

			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
