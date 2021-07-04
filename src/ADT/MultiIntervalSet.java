package ADT;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import Label.Period;

public class MultiIntervalSet<L> implements IntervalSet<L>{

	private Map<L,Set<Period>> set;	
	private Period period ;
	
	public MultiIntervalSet() {
		 set= new HashMap<L,Set<Period>>();
		 period = new Period(Long.MAX_VALUE,0);
	}
	
	//无论如何都可以添加成功 ,第一次添加标签和时间段 返回false，之后标签不变，对应添加时间段返回true
	@Override
	public boolean insert(long start, long end, L label) {
		Period p= new Period(start,end);
		Set<Period> s;
		boolean flag =true;
		if(set.get(label)==null)  {
			s=new TreeSet<Period>();
			flag =false;
		}
		else s=set.get(label);
	    s.add(p);
		set.put(label, s);
		if(period.getend()<end) period.setend(end);
		if(period.getstart()>start) period.setstart(start);
		return flag;
	}

	//存在就删除返回true 不存在不操作返回false
	@Override
	public boolean remove(L label) {
		if(!set.containsKey(label)) return false;
		set.remove(label);	
		return true;
	}

	//返回所有的标签
	@Override
	public Set<L> labels() {
		return set.keySet();
	}
	
	//输入标签，返回标签对应的时间段
	@Override
	public IntervalSet<Integer> intervals(L label){
		IntervalSet <Integer> result= IntervalSet.empty();
		Set<Period> s=  set.get(label);
		int i=0;
		for(Period p: s) {
			result.insert(p.getstart(), p.getend(), i++);
		}
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
	@Override
	public String toString() {
		return set.toString();
	}
}
