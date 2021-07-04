package Label;

public class Period implements Comparable<Period>{
    long start;
    long end;
    public Period(long a,long b){
    	this.start=a;
    	this.end=b;
    }
    
    public Period(Period that) {
    	this.start=that.getstart();
    	this.end=that.getend();
	}

	@Override
    public int compareTo(Period p) {    
         if(this.start > p.start)   return 1;
         else if(this.start == p.start) return 0;
         else return -1;
   }
    @Override
    public String toString() {
    	return Long.toString(start)+"-"+Long.toString(end);
    }
	public long getstart() {
		return start;
	}
	public void setstart(long start) {
		this.start = start;
	}
	public long getend() {
		return end;
	}
	public void setend(long end) {
		this.end = end;
	}
	
	
}