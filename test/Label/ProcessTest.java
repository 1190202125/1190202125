package Label;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProcessTest {
	
	@Test
	public void test() {
		Process p1= new Process(1,"p1",5,10) ;
		
		assertEquals(1,p1.getPID());
		assertEquals("p1",p1.getName());
		assertEquals(5,p1.getMintime());
		assertEquals(10,p1.getMaxtime());
		assertEquals(10,p1.getResiduals());
		Process p2 =new Process(p1);
		//System.out.println(p1.hashCode());
		
		p2.setResiduals(8);
		assertEquals(8,p2.getResiduals());
		Process p3 =new Process(1,"p1",5,10);
		
		assertEquals(true,p1.equals(p2));
		assertEquals(true,p2.equals(p3));
		assertEquals(true,p3.equals(p1));
	}

}
