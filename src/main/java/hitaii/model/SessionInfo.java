package hitaii.model;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private Users users;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return users.getLogname();
	}

}
