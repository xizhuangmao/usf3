package hitaii.pageModel;

public class Pcity {
	private String id;
	private String stateId;      //对应stateId
	private String city;
	private String shortname;
	private String nickname;
	private String isCapital;
	private String country;
	private String countryId;
	private String state;
	private String cityId;
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIsCapital() {
		return isCapital;
	}
	public void setIsCapital(String isCapital) {
		this.isCapital = isCapital;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}


			//排序的数值
			private int page;
			private int rows;
			private String order;
			private String sort;

			public int getPage() {
				return page;
			}
			public void setPage(int page) {
				this.page = page;
			}
			public int getRows() {
				return rows;
			}
			public void setRows(int rows) {
				this.rows = rows;
			}
			public String getOrder() {
				return order;
			}
			public void setOrder(String order) {
				this.order = order;
			}
			public String getSort() {
				return sort;
			}
			public void setSort(String sort) {
				this.sort = sort;
			}
	
	
	
}
