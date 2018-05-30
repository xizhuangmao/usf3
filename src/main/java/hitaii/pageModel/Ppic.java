package hitaii.pageModel;

/** 
 * @author qc
 * 20160113
 * vehicleInfo页面显示的属性
 */
public class Ppic {
	private String id;
	private String name;
	private String vin;
	private String path;
	private String pictype;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPictype() {
		return pictype;
	}
	public void setPictype(String pictype) {
		this.pictype = pictype;
	}
}
