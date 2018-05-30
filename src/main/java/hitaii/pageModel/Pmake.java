package hitaii.pageModel;

import hitaii.model.Country;


public class Pmake {
		// Fields
		private String id;
		private String countryId;
		private String countryName;
		private String make;
		private String shortname;
		private String nickname;
		private Country country;
		
		//---------------------分页
		private int page;
		private int rows;
		private String order;
		private String sort;
		
		public Country getCountry() {
			return country;
		}
		public void setCountry(Country country) {
			this.country = country;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCountryId() {
			return countryId;
		}
		public void setCountryId(String countryId) {
			this.countryId = countryId;
		}
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public String getMake() {
			return make;
		}
		public void setMake(String make) {
			this.make = make;
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
