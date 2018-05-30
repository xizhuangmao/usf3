package hitaii.test;



import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring.xml","classpath:spring-hibernate.xml"})
public class TestSpring2 {
	/**
	 * Logger for this class
	 */
//	private static final Logger logger = Logger.getLogger(TestSpring2.class);
//
//	private UserServiceI userService;
//
//	public UserServiceI getUserService() {
//		return userService;
//	}
//	@Autowired
//	public void setUserService(UserServiceI userService) {
//		this.userService = userService;
//	}
//	
//	@Test
//	public void test(){
//		User u = new User();
//		u.setName("1234");
//		u.setPwd("1234");
//		
//		userService.save(u);;
//
//	}
//	
//	@Test
//	public void test2(){
//		User u = new User();
//		u.setName("admin");
//		u.setPwd("admin");
//		Tuser u2=userService.login(u);
//		logger.info(JSON.toJSONString(u2));
//	}

}
