package APP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ADT.DutyIntervalSet;
import Label.Employee;
import Label.Period;

public class DutyRosterApp {
	
	public static void menu() {
		System.out.println("���鲻Ҫ�ֶ�����Ա����Ϣ��ѡ���ļ�����");
		System.out.println("Ϊ���������info�ļ�������ǰ׼������Ϣ");
		System.out.println("������������8-�ļ��������ݣ�7-չʾ�Ű࣬Ȼ������");
		System.out.println("����ʱ������1");
		System.out.println("����Ա������2");
		System.out.println("ɾ��Ա������3");
		System.out.println("����Ű��¼����4");
		System.out.println("ɾ���Ű��¼����5");
		System.out.println("��������Ű�����6");
		System.out.println("չʾ�Ű��¼����7");
		System.out.println("�ļ�������������8");
		System.out.println("�˳��Ű�ϵͳ����9");
		System.out.println("��������Ĳ���:");
	}

	public static void parse(Map<String,Employee> people,DutyIntervalSet duty ) throws IOException, ParseException {
		File file = null;
		BufferedReader br = null;
		StringBuffer buffer = null;
		String path = "src/Info/test1.txt";
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
		br.close();
		SimpleDateFormat format =new SimpleDateFormat("yyy-MM-dd");
		final long aday= 86400000;
		data = data.replaceAll("\\/\\/[^\\n]*","");
		//System.out.println(data);
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(data);
        data = m.replaceAll("");
        String[] s3=new String[3];
  	
        String reg;   
  	    Pattern pattern;
  	    Matcher matcher;
  	    
  	    reg="Employee\\{((\\w+\\{\\w+,\\d{3}-\\d{4}-\\d{4}\\})+)\\}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(data);
        while(matcher.find()) {
             s3[0]=matcher.group(1);
             //System.out.println(s3[0]);
        }
        
        reg="Period\\{(\\d+-\\d+-\\d+,\\d+-\\d+-\\d+)\\}"; 
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(data);
        while(matcher.find()) {
            s3[1]=matcher.group(1);
            //System.out.println(s3[1]);
        }
        
        reg="Roster\\{((\\w+\\{\\d+-\\d+-\\d+,\\d+-\\d+-\\d+\\})+)\\}";
        pattern = Pattern.compile(reg);
        matcher = pattern.matcher(data);
        while(matcher.find()) {
            s3[2]=matcher.group(1);
           // System.out.println(s3[2]);
         }
        
        String [] einf=s3[0].split("\\{|,|\\}");
        for(int i=0;i<einf.length;i+=3) {
        	Employee p1=new Employee(einf[i],einf[i+1] , einf[i+2]);
        	people.put(p1.getName(), p1);
        }
        
		Date d1 =new Date();
		Date d2 =new Date();
	
		String[] arg1 = s3[1].split(",");
	    d1=format.parse(arg1[0]);
	    d2=format.parse(arg1[1]);
	    Period p2=new Period(d1.getTime(),d2.getTime()+aday);
	    duty.setPeriods(p2);	
	    
	    String [] pinf=s3[2].split("\\{|,|\\}");
	    
	    for(int i=0;i<pinf.length;i+=3) {
		    d1=format.parse(pinf[i+1]);
		    d2=format.parse(pinf[i+2]);
		    //System.out.println(d1+" "+d2+" "+pinf[0]);
		    duty.insert(d1.getTime(), d2.getTime()+aday, people.get(pinf[i]));
	    }
		
	}
	public static void main(String[] args) throws  IOException, ParseException{
		final long aday= 86400000;
		SimpleDateFormat format =new SimpleDateFormat("yyy-MM-dd");
		String treg="\\d{4}-\\d{2}-\\d{2}";
		
		DutyIntervalSet duty = new DutyIntervalSet();
		
		Map<String,Employee> people =new HashMap<String,Employee>();
		
		Scanner sc = new Scanner(System.in);
			
		
		while(true) {
			menu();
			
			String str = sc.nextLine();
			int n=0;
	
			if(str.matches("^[1-9]$")) {
				n=Integer.valueOf(str);
				}
				else{
					System.out.println("��������������룡");
					continue;
				}
			switch(n) {
			case 1 :
				
				//ʱ���ʽ��2021-01-10,2021-03-06
				String str1;
				Date d1 =new Date();
				Date d2 =new Date();
			    while(true) {
			    	System.out.println("���뿪ʼ��ʱ��,����ʱ��:");
					str1 =sc.nextLine();
					if(str1.matches(treg+","+treg)) break;
					else System.out.println("��������������룡");
			    }
				String[] arg1 = str1.split(",");
			    d1=format.parse(arg1[0]);
			    d2=format.parse(arg1[1]);
			    Period p=new Period(d1.getTime(),d2.getTime()+aday);
			    duty.setPeriods(p);	
				break;
				
			case 2 :
				int num=0;
				while(true) {
				System.out.println("����Ա������:");
				if(( str=sc.nextLine()).matches("^[0-9]$")){
				  num=Integer.valueOf(str);
				  break;
				}
				  System.out.println("��������������룡");
				}
				for(int i=0;i<num;i++) {
					
					System.out.println("��������Ա�������� ְλ �绰:");
					String str2=sc.nextLine();
				
					String[] arg2=str2.split(" ");
					Employee p1=new Employee(arg2[0],arg2[1],arg2[2]);
					people.put(p1.getName(), p1);
				}
				break;
				
			case 3 :
				System.out.println("����ɾ��Ա��������:");
				String str2=sc.nextLine();
				Employee p1= people.get(str2);
				if(p1!=null) {
					if(duty.labels().contains(p1)) {
						System.out.println("�Ű���Ϣδɾ��,�޷�ɾ��Ա����Ϣ!");
					}
					else people.remove(p1.getName());
				}
				else System.out.println("δ�洢Ա����Ϣ,�޷�ɾ��Ա����Ϣ!");
				break;
				
			case 4 :
				System.out.println("����Ű��¼��");
				System.out.println("����Ա�������� ��ʼʱ�� ����ʱ��");
				String str4=sc.nextLine();
				String[] arg4=str4.split(" ");
				Date d41= new Date();
				Date d42= new Date();
				d41=format.parse(arg4[1]);
				d42=format.parse(arg4[2]);
				Employee p4=people.get(arg4[0]);
				if(p4!=null) {
					duty.insert(d41.getTime(), d42.getTime()+aday,p4);
					//System.out.println("��ӳɹ�!");
				}
				else System.out.println("Ա����Ϣ�����ڣ�");
				break;
			case 5 :
				System.out.println("����ɾ��Ա��������:");
				str2=sc.nextLine();
				Employee p5= people.get(str2);
				if(p5!=null) {
					if(duty.remove(p5))
					  System.out.println("ɾ���ɹ�!");
					else 
					  System.out.println("δ�ſ�,�޷�ɾ����Ϣ!");
				}
				else System.out.println("δ�洢Ա��,�޷�ɾ����Ϣ!");
	
				break;
			case 6 :
				if(people.isEmpty()) {
					System.out.println("Ա����Ϣδ���룬����������8�����ı�����");
				}
				else {
					 int val[]=new int[people.size()];
						
						long diff=duty.getPeriods().getend()-duty.getPeriods().getstart();
						diff/=86400000;
						for(Employee e: people.values()) {
							duty.remove(e);
						}
						Random g =new Random();
						diff-=people.size();
						for(int i=0;i<people.size()-1;i++) {
							val[i]=g.nextInt((int) diff);
							diff-=val[i];
							//System.out.println(" ran1   "+val[i]);
							
						}
						val[people.size()-1]=(int) diff;
						for(int i=0;i<people.size();i++) {
							++val[i];
						}
						long start =duty.getPeriods().getstart();
						int i=0;
						for(String s:people.keySet()) {
							duty.insert(start, start+val[i]*86400000, people.get(s));
							start+=val[i]*86400000;
							i++;
						}
				}
				break;
			case 7 :
				duty.toString();
				break;
			case 8 :
				parse(people,duty);
				break;
			case 9 :
				break;
			}
			if(n==9) break;
		}
		sc.close();
	}

}
