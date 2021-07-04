package Label;

import static org.junit.Assert.*;

import org.junit.Test;

public class PeriodTest {

	@Test
	public void test() {
		Period p1=new Period(1,5);
		
		assertEquals(1,p1.getstart());
		assertEquals(5,p1.getend());
		
		p1.setstart(2);
		assertEquals(2,p1.getstart());
		assertEquals(5,p1.getend());
		
		p1.setend(8);
		assertEquals(2,p1.getstart());
		assertEquals(8,p1.getend());
		
	}

}
