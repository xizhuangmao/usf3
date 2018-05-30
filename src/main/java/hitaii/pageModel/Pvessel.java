package hitaii.pageModel;

public class Pvessel {
	// Fields

		private String id;
		private String carrierId;
		private String fullname;    //carrier
		private String vessel;
		private String active;
		private String note;
		
		//-------------分页--------------------
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getCarrierId() {
			return carrierId;
		}
		public void setCarrierId(String carrierId) {
			this.carrierId = carrierId;
		}
		public String getVessel() {
			return vessel;
		}
		public void setVessel(String vessel) {
			this.vessel = vessel;
		}
		public String getActive() {
			return active;
		}
		public void setActive(String active) {
			this.active = active;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
		}
	
		
}
