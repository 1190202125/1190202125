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
	
	//������ζ�������ӳɹ� ,��һ����ӱ�ǩ��ʱ��� ����false��֮���ǩ���䣬��Ӧ���ʱ��η���true
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

	//���ھ�ɾ������true �����ڲ���������false
	@Override
	public boolean remove(L label) {
		if(!set.containsKey(label)) return false;
		set.remove(label);	
		return true;
	}

	//�������еı�ǩ
	@Override
	public Set<L> labels() {
		return set.keySet();
	}
	
	//�����ǩ�����ر�ǩ��Ӧ��ʱ���
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
	@Override
	public String toString() {
		return set.toString();
	}
}
