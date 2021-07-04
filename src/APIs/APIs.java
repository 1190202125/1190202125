package APIs;

import java.util.ArrayList;
import java.util.Set;

import ADT.IntervalSet;
import ADT.MultiIntervalSet;
import Label.Period;

public class APIs<L> {
	

	
	public  double Similarity(MultiIntervalSet<L> s1, MultiIntervalSet<L> s2) {
		
		double result = 0;
		ArrayList<L> list1 = new ArrayList<L>();
		int time=(int) (s1.getTime().getend()-s1.getTime().getstart());
		for(int i=0;i<time;i++) {
			 list1.add(null);
		}
		     
		ArrayList<L> list2 = new ArrayList<L>();
		time=(int) (s2.getTime().getend()-s2.getTime().getstart());
		for(int i=0;i<time;i++) {
			 list2.add(null);
		}
			
		for(L label :s1.labels()) {
		     Set<Period> s= s1.getValue(label);
		     for(Period p : s) {
		    	 for(long i= p.getstart()-s1.getTime().getstart();i < p.getend()-s1.getTime().getstart();i++) {
		    		 list1.set((int)i, label);
		    	 }
		     }   
		}
		for(L label :s2.labels()) {
		     Set<Period> s= s2.getValue(label);
		     for(Period p : s) {
		    	 for(long i= p.getstart()-s1.getTime().getstart();i < p.getend()-s1.getTime().getstart();i++) {
		    		 list2.set((int)i, label);
		    	 }
		     } 
		}
		for(int i=0;i<list1.size()&&i<list2.size();i++) {
			if(list1.get(i)==null || list2.get(i)==null) continue;
			if(list1.get(i)==list2.get(i)) result++;
		}
		int length=Math.max(list1.size(),list2.size());
		
		result /=length;
		return result;
	}
	
	
	
	public double calcConflictRatio(IntervalSet<L> set) {
		double result = 0;
		ArrayList<L> list = new ArrayList<L>();
		int time=(int) (set.getTime().getend()-set.getTime().getstart());
		for(int i=0;i<time;i++) {
			 list.add(null);
		}
		for(L label :set.labels()) {
		     Set<Period> s= set.getValue(label);
		     for(Period p : s) {
		    	 for(long i= p.getstart()-set.getTime().getstart();i < p.getend()-set.getTime().getstart();i++) {
		    		 if(list.get((int) i)!=null) result++; 
		    		 list.set((int)i, label);
		    	 }
		     }   
		}

		result /=list.size();
		return result;
		
	}
	
	//System.out.println(result);
	
	//long time= ((set.getTime().getend()-set.getTime().getstart())/86400000);
	
	//System.out.println(time);
	
	
	public double calcFreeTimeRatio(IntervalSet<L> set) {
		double result = 0;
		ArrayList<L> list = new ArrayList<L>();
		
		long time =set.getTime().getend()-set.getTime().getstart();
		
		for(int i=0;i<time;i++) {
			 list.add(null);
		}
		for(L label :set.labels()) {
		     Set<Period> s= set.getValue(label);
		     for(Period p : s) {
		    	 for(long i= p.getstart();i < p.getend();i++) {
				    list.set((int)i, label);
		    	 }
		     }   
		}
		for(int i=0;i<list.size();i++) {
			if(list.get(i)==null) {
			 result++;
			}
		}
		result /=list.size();
		return result;	
	}
	
}
