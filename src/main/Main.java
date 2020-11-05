package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import model.*;
import data.Data;

public class Main {
	
	public static Data data;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			data = new Data("D:\\jav\\subway\\src\\data.txt");
		}
		catch(IOException ex) {
			System.out.println("没有找到该文件或者路径不正确！");
			ex.printStackTrace();
		}
		System.out.println("请选择需要查询的项目：1.查询路线所有站点	2.查询最短路径");
		Scanner s = new Scanner(System.in);
		int select = s.nextInt();
		if(select == 1) {
			System.out.println("请输入路线名：");
			String name = s.next();
			searchline(name);
		}
		
		else if(select == 2) {
			System.out.print("请输入起点：");
			String start = s.next();
			System.out.print("请输入终点：");
			String end = s.next();
			
			findroad(start, end);	//查找最短路径
		}
	}
	

	public static void searchline(String name) {
		int index=0;
		for(int i=0;i<data.lineset.size();i++) {
			if(data.lineset.get(i).getName().equals(name)){
				index = i;
				break;
			}
		}
		if(index==0) {
			System.out.print("该地铁线路不存在");
		}
		else {
			System.out.println(name+"的所有站点如下: ");
			for(int i=0;i<data.lineset.get(index).getStations().size();i++) {
				System.out.print(data.lineset.get(index).getStations().get(i)+" ");
			}
			
		}
	}
	
	
	public static void findroad(String start,String end) {
		Station startstation = new Station();
		Station endstation = new Station();
		Station midstation = new Station();
		startstation = findstationbyname(start);
		endstation = findstationbyname(end);
		if(startstation==null) {
			System.out.println("该起点不存在");
		}
		if(startstation==null) {
			System.out.println("该终点不存在");
		}
		List<Result> Result1 = new ArrayList<Result>();	//结果集
		for(Line l:Data.lineset) {  
			for(int k=0;k<l.getStations().size();k++) {
                Result result = new Result();
                result.setStart(startstation);
                result.setEnd(l.getStations().get(k));
                result.setDistance(999999);
                result.setLinechange(0);
                Result1.add(result);
			}
			
		}
		
		for(int i=0;i<midstation.getLinkstations().size();i++) {
			int flag = 0;
			Result res = new Result(); 
			Station thissation = new Station(); 
			thissation = midstation.getLinkstations().get(i);
			for(int j=0;j<Result1.size();j++) {
				if(Result1.get(j).getEnd().equals(thissation)) {
					flag = 1;
				}
			}
			
		}
		
		int number = Result1.size()+1;
		System.out.println("共经过"+number+"站");
		
		for(int i=0;i<Result1.size();i++) {
			if(Result1.get(i).getLinechange()==1) {
				System.out.print(Result1.get(i).getEnd().getName());
				System.out.println();
				List<String> str = new ArrayList<String>();
				str = getPath(Result1.get(i));
				System.out.println("--->换乘到"+str);
			}
		}
	}
	
	public static Station findstationbyname(String name) {
		Station station = new Station();
		for(int i=0;i<data.lineset.size();i++) {
			for(int j=0;j<data.lineset.get(i).getStations().size();j++) {
				if(name.equals(data.lineset.get(i).getStations().get(j).getName())) {
					station = data.lineset.get(i).getStations().get(j);
					return station;
				}
			}
		}
		return null;
	}
	
	public static List<String> getPath(Result r){  //生成乘车路线
		List<String> path=new ArrayList<String>();
		Stack<Station> st=new Stack<Station>();
		Station s = r.getStart();
		while(!r.getStart().equals(s)) {
			st.push(s);
			s=s.getLinkstations().get(0);
		}
		path.add(r.getStart().getName());
		if(r.getLinechange()==1) {
			path.add(r.getEnd().getName());
		}
		else
		    path.add(r.getEnd().getName());
		return path;
	}
}
