package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Line;
import model.Station;


public class Data {

	public static List<Line> lineset = new ArrayList<Line>(); //������·��List
	
	public Data(String pathname) throws IOException{
		String code = "UTF-8";	//ת���UTF-8��������ܳ�������
        File file = new File(pathname);
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is, code);  
        BufferedReader br = new BufferedReader(isr);
		
		String read = "";
		read = br.readLine();
		int linenum = Integer.parseInt(read);	//һ���м�����·
		
		for(int i=0;i<linenum;i++) {	//ѭ�����ÿһ����·
			read=br.readLine();
        	String[] thisline=read.split(" ");	//������·�������Լ�վ����
        	
        	Line line = new Line();
        	List<Station> stations=new ArrayList<Station>();
        	String linename=thisline[0];
        	line.setName(linename);
        	
        	for(int j=1;j<thisline.length;j++) {
        		Station station = new Station();
        		int exist = 0;	//�ж�վ���Ƿ��Ѿ�����·���ˣ�����վ��
        		station.setName(thisline[j]);
        		for(Line l:lineset) {	//������վ
        			for(int k=0;k<l.getStations().size();k++) {
        				if(l.getStations().get(k).getName().equals(thisline[j])) {  
        					List<String> newline=l.getStations().get(k).getLine();
        					newline.add(linename);
        					l.getStations().get(k).setLine(newline);
        					line.addStation(l.getStations().get(k));
        					exist=1;
        					break;
        				}
        			}
        			if(exist==1)
        				break;
        		}
        		
        		if(j==thisline.length-1&&thisline[j].equals(thisline[1])) {  //������
        			line.getStations().get(0).getLinkstations().add(line.getStations().get(line.getStations().size()-1));
        			line.getStations().get(line.getStations().size()-1).getLinkstations().add(line.getStations().get(0));
        			exist=1;
        		}
        		if(exist==0) {
        			List<String> lines = new ArrayList<String>();
        			lines.add(linename);
        			station.setLine(lines);
        			station.setName(thisline[j]);
        			line.getStations().add(station);
        		}
        			
        		
        	}
        	
        	for(int j=0;j<line.getStations().size();j++) {
        		List<Station> newlinkStations=line.getStations().get(j).getLinkstations();
        		if(j==0) {
        			newlinkStations.add(line.getStations().get(j+1));
        			line.getStations().get(j).setLinkstations(newlinkStations);
        		}
        		else if(j==line.getStations().size()-1) {
        			newlinkStations.add(line.getStations().get(j-1));
        			line.getStations().get(j).setLinkstations(newlinkStations);
        		}
        		else {
        			newlinkStations.add(line.getStations().get(j+1));
        			newlinkStations.add(line.getStations().get(j-1));
        			line.getStations().get(j).setLinkstations(newlinkStations);
        		}
        	}
        	lineset.add(line);
		}
		
	}

}
