package Label;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void test() {
		Employee e1=new Employee("zhangsan","master","189-9427-5716");
	    assertEquals("zhangsan",e1.getName());
	    assertEquals("master",e1.getPosition());
	    assertEquals("189-9427-5716",e1.getTelephone());
	}

}
