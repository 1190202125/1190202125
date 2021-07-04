package Decorate;

import java.util.ArrayList;
import java.util.Set;

import ADT.IntervalSet;
import Label.Period;

public class OverlapDec<L> extends Decorator<L>{

	public OverlapDec(IntervalSet<L> set) {
		super(set);
	}
	
	@Override
	public boolean insert(long start, long end, L label) {
		ArrayList<L> list = new ArrayList<L>();
		for(int i=0;i<set.getTime().getend();i++) {
			 list.add(null);
		}
		for(int i=0;i<end;i++) {
			 list.add(null);
		}
		for(L temp :set.labels()) {
		     Set<Period> s= set.getValue(temp);
		     for(Period p : s) {
		    	 for(long i= p.getstart();i < p.getend();i++) {
		    		 list.set((int)i, temp);
		    	 }
		     }   
		}
		for(int i=(int) start;i<end;i++) {
			if(list.get(i)!=null) return false;
		}
		set.insert(start, end, label);
		return true;
	}
}
