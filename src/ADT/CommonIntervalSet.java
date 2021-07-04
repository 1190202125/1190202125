package ADT;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Label.Period;

public class CommonIntervalSet<L> implements IntervalSet<L>{

	private Map<L,Set<Period>> set;
	private Period period ;
	
	public CommonIntervalSet() {
		 set= new HashMap<L,Set<Period>>();
		 period = new Period(Long.MAX_VALUE,0);
	}
	
	//已存在的无法添加，不存在的可以添加  
	@Override
	public boolean insert(long start, long end, L label) {
	    if(set.containsKey(label)) return false; 
	    Set<Period> s=new HashSet<Period>();
	    s.add(new Period(start,end));
		set.put(label, s);
		if(period.getend()<end) period.setend(end);
		if(period.getstart()>start) period.setstart(start);
		return true;
	}
	//System.out.println("SET1  "+period.getstart()+" "+period.getend());
	
    //存在就删除返回true 不存在不操作返回false
	@Override
	public boolean remove(L label) {
		if(!set.containsKey(label)) return false;
		set.remove(label);	
		return true;
	}
    
	
	@Override
	public Set<L> labels() {
		return set.keySet();
	}
	
	//输入标签，返回标签对应的时间段
	@Override
	public IntervalSet<Integer> intervals(L label){
		Set<Period> s = set.get(label);
		Period p =s.iterator().next();
		IntervalSet <Integer> result= IntervalSet.empty();
		result.insert(p.getstart(),p.getend() ,0);
		return result;
	}
	
	//输入标签，返回标签对应的时间段
	@Override
	public Set<Period> getValue(L lable) {
		return set.get(lable);
	}
	
	@Override
	public Period getTime() {
		return new Period(period);
	}
	
	@Override
	public void setTime(Period period) {
		this.period= new Period(period);
	}
	
	//System.out.println("SET2  "+period.getstart()+" "+period.getend());
	@Override
	public String toString() {
		return set.toString();
	}
	
    public long start(L label) {
	Period p=set.get(label).iterator().next();
	return p.getstart();
    }

    public long end(L label) {
	Period p=set.get(label).iterator().next();
	return p.getend();
    }
}
