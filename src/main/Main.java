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
			System.out.println("û���ҵ����ļ�����·������ȷ��");
			ex.printStackTrace();
		}
		System.out.println("��ѡ����Ҫ��ѯ����Ŀ��1.��ѯ·������վ��	2.��ѯ���·��");
		Scanner s = new Scanner(System.in);
		int select = s.nextInt();
		if(select == 1) {
			System.out.println("������·������");
			String name = s.next();
			searchline(name);
		}
		
		else if(select == 2) {
			System.out.print("��������㣺");
			String start = s.next();
			System.out.print("�������յ㣺");
			String end = s.next();
			
			findroad(start, end);	//�������·��
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
			System.out.print("�õ�����·������");
		}
		else {
			System.out.println(name+"������վ������: ");
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
			System.out.println("����㲻����");
		}
		if(startstation==null) {
			System.out.println("���յ㲻����");
		}
		List<Result> Result1 = new ArrayList<Result>();	//�����
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
		System.out.println("������"+number+"վ");
		
		for(int i=0;i<Result1.size();i++) {
			if(Result1.get(i).getLinechange()==1) {
				System.out.print(Result1.get(i).getEnd().getName());
				System.out.println();
				List<String> str = new ArrayList<String>();
				str = getPath(Result1.get(i));
				System.out.println("--->���˵�"+str);
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
	
	public static List<String> getPath(Result r){  //���ɳ˳�·��
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
