package ADT;


import java.util.Set;
import java.util.TreeMap;

import Label.Period;
import Label.Process;

public class ProcessIntervalSet {

	private IntervalSet<Process> set;

	public ProcessIntervalSet() {
		this.set=new MultiIntervalSet<Process>();
	}

	public boolean insert(long start, long end, Process p) {
		return set.insert(start, end, p);
	}

	public boolean remove(Process label) {
		return set.remove(label);
	}

	public Set<Process> labels() {
		return set.labels();
	}

	public IntervalSet<Integer> intervals(Process label) {
		return set.intervals(label);
	}

	public Set<Period> getValue(Process lable) {
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
		TreeMap<Period,Process> chart=new TreeMap<Period,Process>();
		
		//System.out.println("ProcessIntervalSet:");
		for(Process e:set.labels()) {
			for(Period p:set.getValue(e)) {
				chart.put(p, e);
				//System.out.println(p.toString()+" "+e.toString());
			}
		}
		
		System.out.println("time id name");
		for(Period p:chart.keySet()) {
		    Process e=chart.get(p);
			System.out.println(p.toString()+" "+e.toString());
		}
		return set.toString();
		
	}

}
