package APIs;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ADT.CommonIntervalSet;
import ADT.MultiIntervalSet;
public class APIsTest {
	@Test 
	public void testSimilarity() {
		APIs<String> api = new APIs<String>();
		
		MultiIntervalSet<String> set1 = new MultiIntervalSet<String>();
		set1.insert(0,  5,  "a");
		set1.insert(20, 25, "a");
		set1.insert(10, 20, "b");
		set1.insert(25, 30, "b");
		
		MultiIntervalSet<String> set2 = new MultiIntervalSet<String>();
		set2.insert(20, 35, "a");
		set2.insert(10, 20, "b");
		set2.insert(0,  5,  "c");
		
		assertTrue((api.Similarity(set1,set2)-0.428)<0.1);
		
	}
	@Test
	public void testConflict() {
		APIs<String> api = new APIs<String>();
		MultiIntervalSet<String> set1 = new MultiIntervalSet<String>();
		set1.insert(0,  5,  "a");
		set1.insert(0,  5,  "b");
		set1.insert(5,  7,  "c");
		set1.insert(10, 20, "d");
		set1.insert(15, 35, "e");
		
		assertTrue((api.calcConflictRatio(set1)-0.285)<0.1);
		
		CommonIntervalSet<String> set2 = new CommonIntervalSet<String>();
		set2.insert(0,  5,  "a");
		set2.insert(0,  5,  "b");
		set2.insert(5,  7,  "c");
		set2.insert(10, 20, "d");
		set2.insert(15, 35, "e");
		
		assertTrue((api.calcConflictRatio(set2)-0.285)<0.1);
		
	}
	@Test
	public void testFree() {
		APIs<String> api = new APIs<String>();
		MultiIntervalSet<String> set1 = new MultiIntervalSet<String>();
		set1.insert(0,  5,  "a");
		set1.insert(20, 25, "a");
		set1.insert(10, 20, "b");
		set1.insert(25, 30, "b");
		
		assertTrue((api.calcFreeTimeRatio(set1)-0.166)<0.1);
		
		
		CommonIntervalSet<String> set2 = new CommonIntervalSet<String>();
		set2.insert(20, 35, "a");
		set2.insert(10, 20, "b");
		set2.insert(0,  5,  "c");
		
		assertTrue((api.calcFreeTimeRatio(set2)-0.166)<0.1);
		
		CommonIntervalSet<String> set3 = new CommonIntervalSet<String>();
		
		MultiIntervalSet<String> set4 = new MultiIntervalSet<String>();
		
		set3.insert(0, 5, "a");
		set3.insert(5, 10, "b");
		set3.insert(10, 20, "c");
		
		assertTrue(api.calcFreeTimeRatio(set3)==0.0);
		
		
		set4.insert(0, 5, "a");
		set4.insert(5, 10, "b");
		set4.insert(10, 20, "c");
		
		assertTrue(api.calcFreeTimeRatio(set4)==0.0);
	}

}
