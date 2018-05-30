package hitaii.util;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 示例
 * 工具类
 */
public class WhesdtlUtil {
	/**
	 * 创建UUID
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * @author qc
	 * 
	 * 时间：20160108
	 * 判断是否存在File文件  不存在就创建
	 */
	public static File isExists(String path){
		File file =new File(path);
		 if (!file.exists()) {
			 file.mkdir();
		  }
		return file;
	}
	
	 /** 
     * 通过递归调用删除一个文件夹及下面的所有文件 
     * @param file 
     */  
    public static void deleteFile(File file){  
        if(file.isFile()){//表示该文件不是文件夹  
            file.delete();  
        }else{  
            //首先得到当前的路径  
            String[] childFilePaths = file.list();  
            for(String childFilePath : childFilePaths){  
                File childFile=new File(file.getAbsolutePath()+"\\"+childFilePath);  
                deleteFile(childFile);  
            }  
            file.delete();  
        }  
    }
    
	/**
	 * 2016.01.28 
	 * 创建当前时间的String类型
	 */
	public static String getNowTime(){
		Date date=new Date(System.currentTimeMillis());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String time=format.format(date); 
		return time;
	}
	
	/**
	 * 2016.01.28 
	 * 创建当前时间的String类型
	 */
	public static String getTime(){
		Date date=new Date(System.currentTimeMillis());
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date); 
		return time;
	}
	
	/**
	 * 比较两个String类型的时间
	 */
	public static boolean compare_date(String from, String to) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			 java.util.Date dt1 = df.parse(from);
			 java.util.Date dt2 = df.parse(to);
	            if(dt1.before(dt2)){   //表示dt1小于dt2   2015   2016
	            	 return true;
	            }else{
	            	return false;
	            }
		} catch (ParseException e) {
		}
		return false;
	}   
	
}
