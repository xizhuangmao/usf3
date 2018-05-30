package hitaii.pageModel;

/**
 *	@author qc
 * 
 * 时间：20160129
 * 
 * 新闻
 */
public class Pnews {
	
	private String id;
	private String title;
	private String content;
	private String newsdate;
	private String types;
	private String usersId;

	private String newsdatefrom;
	private String newsdateto;
	private int page;
	private int rows;
	
	private String order;
	private String sort;
	
	public String getNewsdatefrom() {
		return newsdatefrom;
	}
	public void setNewsdatefrom(String newsdatefrom) {
		this.newsdatefrom = newsdatefrom;
	}
	public String getNewsdateto() {
		return newsdateto;
	}
	public void setNewsdateto(String newsdateto) {
		this.newsdateto = newsdateto;
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
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNewsdate() {
		return newsdate;
	}
	public void setNewsdate(String newsdate) {
		this.newsdate = newsdate;
	}
}
