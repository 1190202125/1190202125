package Label;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseTest {

	@Test
	public void test() {
		Course c1=new Course(1 ,"软件构造" ,"刘铭" ,"正心12" ,72);
		assertEquals(c1.getID(),1);
		assertEquals(c1.getLocation(),"正心12");
		assertEquals(c1.getName(),"软件构造");
		assertEquals(c1.getTeacher(),"刘铭");
	}

}

