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
	
	//�Ѵ��ڵ��޷���ӣ������ڵĿ������  
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
	
    //���ھ�ɾ������true �����ڲ���������false
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
	
	//�����ǩ�����ر�ǩ��Ӧ��ʱ���
	@Override
	public IntervalSet<Integer> intervals(L label){
		Set<Period> s = set.get(label);
		Period p =s.iterator().next();
		IntervalSet <Integer> result= IntervalSet.empty();
		result.insert(p.getstart(),p.getend() ,0);
		return result;
	}
	
	//�����ǩ�����ر�ǩ��Ӧ��ʱ���
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
