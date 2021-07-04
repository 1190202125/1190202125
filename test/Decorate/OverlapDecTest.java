package Decorate;

import static org.junit.Assert.*;

import org.junit.Test;

import ADT.CommonIntervalSet;
import ADT.IntervalSet;

public class OverlapDecTest {

	@Test
	public void test() {
		IntervalSet<String> s=new CommonIntervalSet<String>();
		Decorator<String> set=new OverlapDec<String>(s);
		
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
		
		
		set.remove("b");
		set.remove("d");
		
	}

}
