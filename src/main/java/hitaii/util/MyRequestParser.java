package hitaii.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.multipart.JakartaMultiPartRequest;
/**
 * strut2 与 ajaxFileUpload 有冲突    
 * strut2.xml 需要配置一下   这里生成的类    
 * 就行解决这个问题  本身此类也没什么作用 
 */
public class MyRequestParser extends JakartaMultiPartRequest{
	public void parse(HttpServletRequest servletRequest, String saveDir) throws IOException {


	} 
}
