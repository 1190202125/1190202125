package ADT;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CommonIntervalSetTest {
	
	@Test
	public void test() {
		IntervalSet<String> set = new CommonIntervalSet<String>();
		set.insert(1, 2, "a");
		set.insert(2, 4, "b");
		set.insert(4, 7, "c");
		set.insert(9, 12, "d");
		
		set.insert(3, 4,  "a");
		
		//System.out.println(set.labels().toString());
		assertEquals("[a, b, c, d]",set.labels().toString());
		
		
		//System.out.println(set.intervals("a").toString());
		assertEquals("{0=[1-2]}",set.intervals("a").toString());
		assertEquals("{0=[4-7]}",set.intervals("c").toString());
		
		assertEquals("{a=[1-2], b=[2-4], c=[4-7], d=[9-12]}",set.toString());
		
		set.remove("b");
		set.remove("d");
		
		//System.out.println(set.toString());
		
		assertEquals("{a=[1-2], c=[4-7]}",set.toString());
		
	}

}
