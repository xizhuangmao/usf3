package hitaii.pageModel;

public class Pvoyage {
	// Fields
		private String id;
		private String voyage;
		private String vesselId;
		private String vessel;
		private String cutoffdate;
		private String eta;
		private String etd;
		private String carrierId;
		private String carrier;
		private String terminalId;
		private String terminal;
		private String active;
		private String note;
		private String fullname;
		
		//-------------分页--------------------
		private int page;
		private int rows;
		private String order;
		private String sort;
		
		private String type;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getFullname() {
			return fullname;
		}
		public void setFullname(String fullname) {
			this.fullname = fullname;
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getVoyage() {
			return voyage;
		}
		public void setVoyage(String voyage) {
			this.voyage = voyage;
		}
		public String getVesselId() {
			return vesselId;
		}
		public void setVesselId(String vesselId) {
			this.vesselId = vesselId;
		}
		public String getCutoffdate() {
			return cutoffdate;
		}
		public void setCutoffdate(String cutoffdate) {
			this.cutoffdate = cutoffdate;
		}
		public String getEta() {
			return eta;
		}
		public void setEta(String eta) {
			this.eta = eta;
		}
		public String getEtd() {
			return etd;
		}
		public void setEtd(String etd) {
			this.etd = etd;
		}
		public String getCarrier() {
			return carrier;
		}
		public void setCarrier(String carrier) {
			this.carrier = carrier;
		}
		public String getCarrierId() {
			return carrierId;
		}
		public void setCarrierId(String carrierId) {
			this.carrierId = carrierId;
		}
		public String getTerminalId() {
			return terminalId;
		}
		public void setTerminalId(String terminalId) {
			this.terminalId = terminalId;
		}
		public String getTerminal() {
			return terminal;
		}
		public void setTerminal(String terminal) {
			this.terminal = terminal;
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
		public String getVessel() {
			return vessel;
		}
		public void setVessel(String vessel) {
			this.vessel = vessel;
		}
		
		
}
