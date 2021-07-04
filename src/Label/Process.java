package Label;

public class Process implements Comparable<Process> {
	private long PID;
	private String name;
	private long mintime;
	private long maxtime;
	private long residuals;
	
	public Process(long pID, String name, long mintime, long maxtime) {
		
		PID = pID;
		this.name = name;
		this.mintime = mintime;
		this.maxtime = maxtime;
		this.residuals = maxtime;
	}

	public Process() {
		
	}
	public Process(Process p) {
		PID = p.getPID();
		this.name = p.getName();
		this.mintime = p.getMintime();
		this.maxtime = p.getMaxtime();
		this.residuals = p.getResiduals();
	}



	public long getPID() {
		return PID;
	}


	public String getName() {
		return name;
	}

	
	public long getMintime() {
		return mintime;
	}


	public long getMaxtime() {
		return maxtime;
	}


	public long getResiduals() {
		return residuals;
	}

	public void setResiduals(long residuals) {
		this.residuals = residuals;
	}

	@Override
	public int compareTo(Process p) {
		  if(this.residuals > p.residuals)   return 1;
	         else if(this.residuals == p.residuals) return 0;
	         else return -1;
	}
	@Override
	public String toString() {
		//System.out.println(PID+" "+name+" "+" "+mintime+" "+maxtime+" "+ residuals);
		return PID+"  "+name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (PID ^ (PID >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Process other = (Process) obj;
		if (PID != other.PID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
