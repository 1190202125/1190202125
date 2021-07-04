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
		System.out.println("建议不要手动输入课程信息，选择文件输入2，然后安排课程输入3");
		System.out.println("为方便测验在info文件夹中提前准备了信息");
		System.out.println("设定学期输入1");
		System.out.println("文件导入课程输入2");
		System.out.println("安排课程时间输入3");
		System.out.println("增加课程信息输入4");
		System.out.println("查询一天课程输入5");
		System.out.println("退出排班系统输入6");
		System.out.println("请输入你的操作:");
	}

	public static int  classhour() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String str;
		int n=0,m=0;
	    do{
		System.out.println("选择周一输入1");
		System.out.println("选择周二输入2");
		System.out.println("选择周三输入3");
		System.out.println("选择周四输入4");
		System.out.println("选择周五输入5");
		System.out.println("选择周六输入6");
		System.out.println("选择周日输入7");
		System.out.println("请输入你的选择:");
		str = sc.nextLine();
		if(str.matches("^[1-7]$")) {
			n=Integer.valueOf(str);
			break;
		}
		}while(true);
		
	    do{
		System.out.println("选择08-10输入1");
		System.out.println("选择10-12输入2");
		System.out.println("选择13-15输入3");
		System.out.println("选择15-17输入4");
		System.out.println("选择19-21输入5");
		System.out.println("请输入你的选择:");
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
				System.out.println("输入错误，重新输入！");
				continue;
			}
			
			switch(n) {
			case 1 :
				String s1;
				Date d1 =new Date();
				while(true) {
					System.out.println("输入日期:");
					s1=sc.nextLine();
					if(s1.matches(treg+","+treg)) break;
					else System.out.println("输入错误，重新输入！");
				}
				d1=format.parse(s1);
				while(true) {
					System.out.println("输入周数:");
					s1=sc.nextLine();
					if(s1.matches("^[0-9]+$")) break;
					else System.out.println("输入错误，重新输入！");
				}
				int week=Integer.valueOf(s1);
				break;
			case 2 :
				parse(course,schedule);
				break;
            case 3 :
				
				System.out.println("添加课程安排记录：");
				System.out.println("输入课程的名称 ");
				String s3=sc.nextLine();
				Course c3=course.get(s3);
				long time =(long)classhour();
				schedule.insert(time, time+1, c3);
				break;

			case 4 :
				int num=0;
				while(true) {
				System.out.println("输入课程个数:");
				String ss=sc.nextLine();
				if(ss.matches("[0-9]+")){
				num=Integer.valueOf(ss);
				break;
				}
				System.out.println("输入错误，重新输入！");
				}
				for(int i=0;i<num;i++) {
					System.out.println("输入增加课程的ID 名称 老师 地点 学时:");
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
					System.out.println("输入日期:");
					s6=sc.nextLine();
					if(s6.matches(treg+","+treg)) break;
					else System.out.println("输入错误，重新输入！");
				}
				d1=format.parse(s6);
				Date d2 =new Date();
				
				long diff=d2.getTime()-d1.getTime();
				int day=(int) (diff/aday);
				day%=7; 
				day+=1;
				break;
				*/
				System.out.println("输入第几周数:");
				s1=sc.nextLine();
				int w;
				System.out.println("输入周几:");
				do{
					System.out.println("选择周一输入1");
					System.out.println("选择周二输入2");
					System.out.println("选择周三输入3");
					System.out.println("选择周四输入4");
					System.out.println("选择周五输入5");
					System.out.println("选择周六输入6");
					System.out.println("选择周日输入7");
					System.out.println("请输入你的选择:");
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
				System.out.println("请输入正确指令\n");
				break;	
			}
			if(n==6) break;
			
		}	
	}	
}
