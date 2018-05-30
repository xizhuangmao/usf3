package hitaii.constant;

public interface Constant {
	
	/******************车辆图片路径****************************/
	
	String CAR_IMAGE_PATH="C:/USFL/pic";
	
	
	/** ***************************编码格式******************************* */
	String ENCODING_FORMAT_DEFAULT = "UTF-8";
	/** **********************验证码*************************** */
	public static String VALIDATECODE_KEY = "valCode";
	/** ***********************系统会话************************ */
	String USF_SESSION = "usfSession";
	/** ******************响应码及响应消息******************* */
	String SUCCESS_CODE = "200";
	String FAIL_CODE = "400";
	String REQUEST_PARAM_ERROR_CODE = "401";
	String REQUEST_PARAM_ERROR_MSG = "The request parameter error!";
	String REQUEST_PARAM_VALUE_ERROR_CODE = "402";
	String REQUEST_PARAM_VALUE_ERROR_MSG = "The request parameter value is not in the correct format!";
	String NO_LOGIN_CODE = "403";
	String NO_LOGIN_MSG = "You are not logged or has timed out, please login!";
	String NO_FIND_RESOURCE_CODE = "404";
	String NO_FIND_RESOURCE_MSG = "Did not find the resources!";
	String NO_RIGHT_CODE = "405";
	String NO_RIGHT_MSG = "No permission to operate！";
	String SYSTEM_EXCEPTION_CODE = "500";
	String SYSTEM_EXCEPTION_MSG = "The system is abnormal, please refresh retry！";
	
	/*******************************员工类型**************************************/
	String USER_TYPE_EMPLOYEE = "1";
	

}
