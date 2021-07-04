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
import java.util.Scanner;

import ADT.CourseIntervalSet;
import Label.Course;

public class CourseScheduleApp {
	public static void menu() {
		System.out.println("���鲻Ҫ�ֶ�����γ���Ϣ��ѡ���ļ�����2��Ȼ���ſγ�����3");
		System.out.println("Ϊ���������info�ļ�������ǰ׼������Ϣ");
		System.out.println("�趨ѧ������1");
		System.out.println("�ļ�����γ�����2");
		System.out.println("���ſγ�ʱ������3");
		System.out.println("���ӿγ���Ϣ����4");
		System.out.println("��ѯһ��γ�����5");
		System.out.println("�˳��Ű�ϵͳ����6");
		System.out.println("��������Ĳ���:");
	}

	public static int  classhour() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String str;
		int n=0,m=0;
	    do{
		System.out.println("ѡ����һ����1");
		System.out.println("ѡ���ܶ�����2");
		System.out.println("ѡ����������3");
		System.out.println("ѡ����������4");
		System.out.println("ѡ����������5");
		System.out.println("ѡ����������6");
		System.out.println("ѡ����������7");
		System.out.println("���������ѡ��:");
		str = sc.nextLine();
		if(str.matches("^[1-7]$")) {
			n=Integer.valueOf(str);
			break;
		}
		}while(true);
		
	    do{
		System.out.println("ѡ��08-10����1");
		System.out.println("ѡ��10-12����2");
		System.out.println("ѡ��13-15����3");
		System.out.println("ѡ��15-17����4");
		System.out.println("ѡ��19-21����5");
		System.out.println("���������ѡ��:");
		str = sc.nextLine();
		if(str.matches("^[1-5]$")) {
			m=Integer.valueOf(str);
			break;
		}
	    }while(true);
	    int result =5*(n-1)+m;
	    return result ;
}
	public static void parse(Map<String, Course> course, CourseIntervalSet shedule) throws IOException {
		File file = null;
		BufferedReader br = null;
		StringBuffer buffer = null;
		String path = "src/Info/test3.txt";
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
		System.out.println(data);
		String[] ps=data.split("\r\n");
		for(String str : ps) {
			//System.out.println("str");
				//	System.out.println(str);
					
					String[] arg = str.split(" ");
					long ID = Long.valueOf(arg[0]);
					int t=Integer.valueOf(arg[4]);
					Course c= new Course(ID,arg[1],arg[2],arg[3],t);
					course.put(c.getName(),c);
					
		}
		for(@SuppressWarnings("unused") Course p1: course.values()) {
					//System.out.println(p1.toString());
					//System.out.println(p1.getResiduals());
		}		
	}
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException, IOException{
		
		SimpleDateFormat format =new SimpleDateFormat("yyy-MM-dd");
		String treg="\\d{4}-\\d{2}-\\d{2}";
		final long aday= 86400000;
		
		CourseIntervalSet schedule = new CourseIntervalSet();
		Map<String,Course> course =new HashMap<String,Course>();
		
		while(true) {
			menu();
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			int n=0;
			if(str.matches("^[1-9]")) {
			n=Integer.valueOf(str);
			}
			else{
				System.out.println("��������������룡");
				continue;
			}
			
			switch(n) {
			case 1 :
				String s1;
				Date d1 =new Date();
				while(true) {
					System.out.println("��������:");
					s1=sc.nextLine();
					if(s1.matches(treg+","+treg)) break;
					else System.out.println("��������������룡");
				}
				d1=format.parse(s1);
				while(true) {
					System.out.println("��������:");
					s1=sc.nextLine();
					if(s1.matches("^[0-9]+$")) break;
					else System.out.println("��������������룡");
				}
				int week=Integer.valueOf(s1);
				break;
			case 2 :
				parse(course,schedule);
				break;
            case 3 :
				
				System.out.println("��ӿγ̰��ż�¼��");
				System.out.println("����γ̵����� ");
				String s3=sc.nextLine();
				Course c3=course.get(s3);
				long time =(long)classhour();
				schedule.insert(time, time+1, c3);
				break;

			case 4 :
				int num=0;
				while(true) {
				System.out.println("����γ̸���:");
				String ss=sc.nextLine();
				if(ss.matches("[0-9]+")){
				num=Integer.valueOf(ss);
				break;
				}
				System.out.println("��������������룡");
				}
				for(int i=0;i<num;i++) {
					System.out.println("�������ӿγ̵�ID ���� ��ʦ �ص� ѧʱ:");
					String str2=sc.nextLine();
					String[] arg2=str2.split(" ");
					Course c1=new Course(Long.valueOf(arg2[0]),arg2[1],arg2[2],arg2[3],Long.valueOf(arg2[4]));
					course.put(c1.getName(), c1);
				}
				break;
			/*case 5 :
				//shedule.toString();
				break;
			*/
			case 5 :
				/*Date d =new Date();
				String s6;
				while(true) {
					System.out.println("��������:");
					s6=sc.nextLine();
					if(s6.matches(treg+","+treg)) break;
					else System.out.println("��������������룡");
				}
				d1=format.parse(s6);
				Date d2 =new Date();
				
				long diff=d2.getTime()-d1.getTime();
				int day=(int) (diff/aday);
				day%=7; 
				day+=1;
				break;
				*/
				System.out.println("����ڼ�����:");
				s1=sc.nextLine();
				int w;
				System.out.println("�����ܼ�:");
				do{
					System.out.println("ѡ����һ����1");
					System.out.println("ѡ���ܶ�����2");
					System.out.println("ѡ����������3");
					System.out.println("ѡ����������4");
					System.out.println("ѡ����������5");
					System.out.println("ѡ����������6");
					System.out.println("ѡ����������7");
					System.out.println("���������ѡ��:");
					str = sc.nextLine();
					if(str.matches("^[1-7]$")) {
						w=Integer.valueOf(str);
						break;
					}
					}while(true);
				schedule.display(w);
				
			case 6:
				break;
			default:
				System.out.println("��������ȷָ��\n");
				break;	
			}
			if(n==6) break;
			
		}	
	}	
}
