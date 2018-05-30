package hitaii.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Orders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orders", catalog = "usf3")
public class Orders implements java.io.Serializable {

	// Fields

		private String id;
		private String name;
		private String usersId;
		private String ordersdate;
		private String note;
		private String scoreCu;
		private String scoreEm;
		private String commentCu;
		private String commentEm;
		private String priceTotle;
		private String priceGet;
		private String shipper;
		private String consignee;
		private String notifyparty;
		private String billto;
		private String priseterm;
		private String pol;
		private String pod;
		private String nvoccId;

		// Constructors

		/** default constructor */
		public Orders() {
		}

		/** minimal constructor */
		public Orders(String id, String usersId) {
			this.id = id;
			this.usersId = usersId;
		}

		/** full constructor */
		public Orders(String id, String name, String usersId, String ordersdate,
				String note, String scoreCu, String scoreEm, String commentCu,
				String commentEm, String priceTotle, String priceGet,
				String shipper, String consignee, String notifyparty,
				String billto, String priseterm, String pol, String pod,
				String nvoccId) {
			this.id = id;
			this.name = name;
			this.usersId = usersId;
			this.ordersdate = ordersdate;
			this.note = note;
			this.scoreCu = scoreCu;
			this.scoreEm = scoreEm;
			this.commentCu = commentCu;
			this.commentEm = commentEm;
			this.priceTotle = priceTotle;
			this.priceGet = priceGet;
			this.shipper = shipper;
			this.consignee = consignee;
			this.notifyparty = notifyparty;
			this.billto = billto;
			this.priseterm = priseterm;
			this.pol = pol;
			this.pod = pod;
			this.nvoccId = nvoccId;
		}

		// Property accessors
		@Id
		@Column(name = "id", unique = true, nullable = false, length = 36)
		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@Column(name = "name", length = 100)
		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Column(name = "usersId", nullable = false, length = 36)
		public String getUsersId() {
			return this.usersId;
		}

		public void setUsersId(String usersId) {
			this.usersId = usersId;
		}

		@Column(name = "ordersdate", length = 20)
		public String getOrdersdate() {
			return this.ordersdate;
		}

		public void setOrdersdate(String ordersdate) {
			this.ordersdate = ordersdate;
		}

		@Column(name = "note", length = 200)
		public String getNote() {
			return this.note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		@Column(name = "scoreCu", length = 10)
		public String getScoreCu() {
			return this.scoreCu;
		}

		public void setScoreCu(String scoreCu) {
			this.scoreCu = scoreCu;
		}

		@Column(name = "scoreEm", length = 10)
		public String getScoreEm() {
			return this.scoreEm;
		}

		public void setScoreEm(String scoreEm) {
			this.scoreEm = scoreEm;
		}

		@Column(name = "commentCu", length = 200)
		public String getCommentCu() {
			return this.commentCu;
		}

		public void setCommentCu(String commentCu) {
			this.commentCu = commentCu;
		}

		@Column(name = "commentEm", length = 200)
		public String getCommentEm() {
			return this.commentEm;
		}

		public void setCommentEm(String commentEm) {
			this.commentEm = commentEm;
		}

		@Column(name = "priceTotle", length = 20)
		public String getPriceTotle() {
			return this.priceTotle;
		}

		public void setPriceTotle(String priceTotle) {
			this.priceTotle = priceTotle;
		}

		@Column(name = "priceGet", length = 20)
		public String getPriceGet() {
			return this.priceGet;
		}

		public void setPriceGet(String priceGet) {
			this.priceGet = priceGet;
		}

		@Column(name = "shipper", length = 100)
		public String getShipper() {
			return this.shipper;
		}

		public void setShipper(String shipper) {
			this.shipper = shipper;
		}

		@Column(name = "consignee", length = 100)
		public String getConsignee() {
			return this.consignee;
		}

		public void setConsignee(String consignee) {
			this.consignee = consignee;
		}

		@Column(name = "notifyparty", length = 100)
		public String getNotifyparty() {
			return this.notifyparty;
		}

		public void setNotifyparty(String notifyparty) {
			this.notifyparty = notifyparty;
		}

		@Column(name = "billto", length = 200)
		public String getBillto() {
			return this.billto;
		}

		public void setBillto(String billto) {
			this.billto = billto;
		}

		@Column(name = "priseterm", length = 20)
		public String getPriseterm() {
			return this.priseterm;
		}

		public void setPriseterm(String priseterm) {
			this.priseterm = priseterm;
		}

		@Column(name = "pol", length = 50)
		public String getPol() {
			return this.pol;
		}

		public void setPol(String pol) {
			this.pol = pol;
		}

		@Column(name = "pod", length = 50)
		public String getPod() {
			return this.pod;
		}

		public void setPod(String pod) {
			this.pod = pod;
		}

		@Column(name = "nvoccId", length = 36)
		public String getNvoccId() {
			return this.nvoccId;
		}

		public void setNvoccId(String nvoccId) {
			this.nvoccId = nvoccId;
		}

}