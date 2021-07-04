package ADT;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Set;
import java.util.TreeMap;
//import java.util.TreeSet;

import APIs.APIs;
import Label.Employee;
import Label.Period;

public class DutyIntervalSet {

	private IntervalSet<Employee> set;
	private Period periods;
	
	public DutyIntervalSet() {
		
		set=new CommonIntervalSet<Employee>();
		set=new MultiIntervalSet<Employee>();	
	}
	
	public boolean insert(long start, long end, Employee label) {
		 final long aday= 86400000;
		 start-=periods.getstart();
		 start/=aday;
		 end-=periods.getstart();
		 end/=aday;
		 System.out.println("��������ģ�"+start+" "+end+" "+label.toString());
		 set.insert(start, end, label);
		 APIs<Employee> api=new APIs<Employee>();
		 if(api.calcFreeTimeRatio(set)==0.0) return true;
		 else {
			 System.out.println("��⵽���пհ�����");
			 System.out.println("�����������룡");
		 }
		 return false;
	}
	public void setPeriods(Period periods) {
		this.periods = periods;
		long diff=(periods.getend()-periods.getstart())/86400000;
		set.setTime(new Period(0,diff));
	}
	public boolean remove(Employee label) {
		return set.remove(label);
	}
	public Set<Employee> labels() {
		return set.labels();
	}
	public IntervalSet<Integer> intervals(Employee label) {
		return set.intervals(label);
	}
	public Set<Period> getValue(Employee lable) {
		return set.getValue(lable);
	}
	public Period getTime() {
		return set.getTime();
	}
	public Period getPeriods() {
		return new Period(periods);
	}

	@Override
	public String toString() {
		TreeMap<Period,Employee> chart=new TreeMap<Period,Employee>();
		final long aday= 86400000;
		SimpleDateFormat format =new SimpleDateFormat("yyy-MM-dd");
		System.out.println("DutyIntervalSet:");
		for(Employee e:set.labels()) {
			for(Period p:set.getValue(e)) {
				chart.put(p, e);
				//System.out.println(p.toString()+" "+e.toString());
			}
		}
		
		System.out.println("time    name position tel");
		for(Period p:chart.keySet()) {
			Date d=new Date(p.getstart()*86400000+periods.getstart());
			String s1=format.format(d);
		    d.setTime(p.getend()*86400000-aday+periods.getstart());	
		    String s2=format.format(d);
		    Employee e=chart.get(p);
			System.out.println(s1+"  " +s2+"  "+e.toString());
		}
		return "DutyIntervalSet [set=" + set + "]";
	}
	
}
