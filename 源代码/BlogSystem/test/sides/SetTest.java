package sides;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import bean.User;

public class SetTest {

	@Test
	public void setsTest() throws Exception {
		Set<User> users = new HashSet<User>();
		for(int index=1; index<=3; index++) {
			User user = new User();
			user.setName("mark"+index);
			user.setPassword("password"+index);
			user.setEmail("email"+index);
			user.setSex(index%2==0);
			
			users.add(user);
		}
		
		System.out.println(users);
	}
	
}
