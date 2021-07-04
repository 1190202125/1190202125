package ADT;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import Decorate.OverlapDec;
import Label.Course;
import Label.Period;

@SuppressWarnings("unused")
public class CourseIntervalSet {
	private IntervalSet<Course> set;
	
	public CourseIntervalSet() {	
		
		this.set =new MultiIntervalSet<Course>();
		this.set =new OverlapDec<Course>(new MultiIntervalSet<Course>());
	}
	
	public boolean insert(long start, long end, Course label) {
		return set.insert(start, end, label);
	}

	public boolean remove(Course label) {
		return set.remove(label);
	}

	public Set<Course> labels() {
		return set.labels();
	}

	public IntervalSet<Integer> intervals(Course label) {
		return set.intervals(label);
	}

	public Set<Period> getValue(Course lable) {
		return set.getValue(lable);
	}

	public Period getTime() {
		return set.getTime();
	}

	public void setTime(Period period) {
		set.setTime(period);
	}

	@Override
	public String toString() {
		TreeMap<Period,HashSet<Course>> chart=new TreeMap<Period,HashSet<Course>> ();
		for(Course c:set.labels()){
			for(Period p:set.getValue(c)) {
			    if(chart.get(p)==null) {
			    	HashSet<Course> s=new HashSet<Course>();
			    	s.add(c);
			    	chart.put(p,s);
			    }
			    else {
			    	chart.get(p).add(c);
			    }
			}
		}
		for(Period p:chart.keySet()) {System.out.println("p"+p.toString()+" "+chart.get(p).toString());}
		System.out.println("id course teacher location");
		System.out.println();
		for(int i=1;i<6;i++) {	
			for(int j=1;j<8;j++){
				int n=(i-1)*7+j;
				Period p=new Period(n,n+1);
				if(chart.get(p)==null) {
					System.out.print("null                               ");
				}
				else {
					System.out.print(chart.get(p).toString());	
				}
			}
			System.out.println();		
		}
		System.out.println(set.toString());
		return "CourseIntervalSet [set=" + set + "]";
	}

	public void display(int w) {
		TreeMap<Period,HashSet<Course>> chart=new TreeMap<Period,HashSet<Course>> ();
		for(Course c:set.labels()){
			for(Period p:set.getValue(c)) {
			    if(chart.get(p)==null) {
			    	HashSet<Course> s=new HashSet<Course>();
			    	s.add(c);
			    	chart.put(p,s);
			    }
			    else {
			    	chart.get(p).add(c);
			    }
			}
		}
		
		System.out.println("id course teacher location");
		int n=(w-1)*5;
		for(int i=0;i<5;i++) {
			Period p=new Period(n+i+1,n+2+i);
			System.out.printf("%d-%d: ",i+1,i+2);
			if(chart.get(p)==null) {
				System.out.println("null");
			}
			else {
				System.out.println(chart.get(p).toString());	
			}
		}
	}
	
}
