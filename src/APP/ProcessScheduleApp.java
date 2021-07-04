package APP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

import ADT.ProcessIntervalSet;
import Label.Process;
public class ProcessScheduleApp {
	
	
	public static void menu() {
		System.out.println("��������˳��1->2->4��1->3->4");
		System.out.println("Ϊ���������info�ļ�������ǰ׼������Ϣ");
		System.out.println("��ӽ�������1(���ļ��е���)");
		System.out.println("�����������2");
		System.out.println("��̵�������3");
		System.out.println("չʾ��������4");
		System.out.println("�˳�����5");

		System.out.println("��������Ĳ���:");
	}
	public static void parse(TreeSet<Process> process) throws IOException {
		File file = null;
		BufferedReader br = null;
		StringBuffer buffer = null;
		String path = "src/Info/test2.txt";
		String data = "";
		file = new File(path);
		buffer = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
		br = new BufferedReader(isr);
		int s;
		while ((s = br.read()) != -1) {
		buffer.append((char) s);
		}
		data = buffer.toString();
		//System.out.println(data);
		String[] ps=data.split("\r\n");
		for(String str : ps) {
			//System.out.println(str);
			//System.out.println("str");
			String[] arg = str.split(" ");
			long pID = Long.valueOf(arg[0]);
			String name = arg[1];
			long mintime = Long.valueOf(arg[2]);
			long maxtime = Long.valueOf(arg[3]);
			
			Process p= new Process(pID, name, mintime, maxtime);
			process.add(p);
			
		}
		//for(Process p1:process) {
			//System.out.println(p1.toString());
			//System.out.println(p1.getResiduals());
		//}
		br.close();
	}
    public static void main(String[] args) throws IOException{
    	
    	
		
	    TreeSet<Process> process = new TreeSet<Process>();
		ProcessIntervalSet chart=new ProcessIntervalSet(); 
	    @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
	    
		while(true) {
			menu();
			String str = sc.next();
			int n=Integer.valueOf(str);
			long start=0;
			switch(n) {
			case 1 :
				parse(process);
				break;
			case 2 :
			   while(process.size()!=0) {  
				    Random r= new Random();
				    int i= r.nextInt(process.size())+1;
				    //System.out.println("r1  "+i);
					Process p=null;
					Iterator<Process> it= process.iterator();
					while(it.hasNext()&&i>0) {
						p=new Process(it.next());
						i--;
						if(i==0) it.remove();
					}
					//p.toString();
					Random rt= new Random();
					int time=rt.nextInt((int) (p.getResiduals()+1));
					//System.out.println("r2  "+time);
					long temp =p.getResiduals()-time;
					if(temp>p.getMaxtime()-p.getMintime()) {
						p.setResiduals(temp);
						process.add(p);
						//System.out.println("add  "+p.getName()+" "+p.getMaxtime()+" "+p.getMintime()+" "+p.getResiduals()+" "+temp);
						//p.toString();
					}
					else {
						time=(int) (p.getResiduals()-(p.getMaxtime()-p.getMintime()));
					}
					chart.insert(start, start+time, p);
					//System.out.println("add2  "+p.getName()+" "+p.getMaxtime()+" "+p.getMintime()+" "+p.getResiduals()+" "+time);
					//System.out.println("insert  ");
					//p.toString();
					start+=time;
					//System.out.println("start  "+start);
			   }
				
				break;
			case 3 :
				 while(process.size()!=0) {
					    Process p1 = process.pollFirst();
						//System.out.println("p  "+p1.toString());
						Random rt1= new Random();
						int time1=rt1.nextInt((int) (p1.getResiduals()+1));
						long temp1 =p1.getResiduals()-time1;
						if(temp1>p1.getMaxtime()-p1.getMintime()) {
							p1.setResiduals(temp1);
							process.add(p1);
						}
						else {
							time1=(int) (p1.getResiduals()-(p1.getMaxtime()-p1.getMintime()));
						}
						chart.insert(start, start+time1, p1);
						//System.out.println("add2  "+p1.getName()+" "+p1.getMaxtime()+" "+p1.getMintime()+" "+p1.getResiduals()+" "+time1);
						start+=time1;
				 }
				
				break;
			case 4 :
				chart.toString();
				break;
			case 5 :
				System.exit(0);
			default:
				System.out.println("��������ȷָ��\n");
				break;	
			}
			
		}
		
	}

}
