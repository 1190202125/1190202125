package ADT;

import java.util.Set;

import Label.Period;

public interface IntervalSet<L> {
	
	public static <L> IntervalSet<L> empty(){
		return new MultiIntervalSet<L>();
	}
	
	public boolean insert(long start,long end,L label);
	public boolean remove(L label);
	public Set<L> labels();
	public IntervalSet<Integer> intervals(L label);
	
	public Set<Period> getValue(L lable);
	public Period getTime();
    public void setTime(Period period);
	
}
