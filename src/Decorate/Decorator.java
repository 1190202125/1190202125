package Decorate;

import java.util.Set;

import ADT.IntervalSet;
import Label.Period;

public abstract class Decorator<L> implements IntervalSet<L>{

	IntervalSet<L> set;
	
	public Decorator(IntervalSet<L> set) {
		this.set=set;
	}
	public boolean insert(long start,long end,L label) {
		return set.insert(start, end, label);
	}
	public boolean remove(L label) {
		return set.remove(label);
	}
	
	public Set<L> labels(){
		return set.labels();
	}
	
	public IntervalSet<Integer> intervals(L label){
		return set.intervals(label);
	}
	
	public Set<Period> getValue(L lable){
		return set.getValue(lable);
	}
	
	public Period getTime() {
		return set.getTime();
	}
	
	public void setTime(Period period) {
		set.setTime(period);
	}
}
