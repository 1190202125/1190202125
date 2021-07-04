package ADT;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MultiIntervalSetTest {

	@Test
	public void test() {
		IntervalSet<String> set = new MultiIntervalSet<String>();
		set.insert(1, 2, "a");
		set.insert(2, 4, "b");
		set.insert(4, 7, "c");
		set.insert(7, 9, "a");
		set.insert(16, 18, "a");
		set.insert(14, 15, "a");
		set.insert(15, 16, "a");
		
		//System.out.println(set.toString());
		assertEquals("{a=[1-2, 7-9, 14-15, 15-16, 16-18], b=[2-4], c=[4-7]}",set.toString());
		assertEquals("[a, b, c]",set.labels().toString());
		assertEquals("{0=[1-2], 1=[7-9], 2=[14-15], 3=[15-16], 4=[16-18]}",set.intervals("a").toString());
		
		//System.out.println(set.labels().toString());
		//System.out.println(set.intervals("a").toString());
		//assertEquals("{a=[1-2, 7-9], b=[2-4], c=[4-7], d=[9-12]}",set.toString());
		
		set.remove("b");
		//System.out.println(set.toString());
		assertEquals("{a=[1-2, 7-9, 14-15, 15-16, 16-18], c=[4-7]}",set.toString());
		set.remove("a");
		//System.out.println(set.toString());
		assertEquals("{c=[4-7]}",set.toString());
	}
}
