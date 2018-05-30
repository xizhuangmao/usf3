package hitaii.pageModel;

public class Pstate {
	private String id;
	
	private String countryId;
	private String state;
	private String shortname;
	private String nickname;
	private String country;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
