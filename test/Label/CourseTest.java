package Label;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseTest {

	@Test
	public void test() {
		Course c1=new Course(1 ,"�������" ,"����" ,"����12" ,72);
		assertEquals(c1.getID(),1);
		assertEquals(c1.getLocation(),"����12");
		assertEquals(c1.getName(),"�������");
		assertEquals(c1.getTeacher(),"����");
	}

}

