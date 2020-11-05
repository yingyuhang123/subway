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

	public static List<Line> lineset = new ArrayList<Line>(); //地铁线路的List
	
	public Data(String pathname) throws IOException{
		String code = "UTF-8";	//转码成UTF-8，否则可能出现乱码
        File file = new File(pathname);
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is, code);  
        BufferedReader br = new BufferedReader(isr);
		
		String read = "";
		read = br.readLine();
		int linenum = Integer.parseInt(read);	//一共有几条线路
		
		for(int i=0;i<linenum;i++) {	//循环添加每一条线路
			read=br.readLine();
        	String[] thisline=read.split(" ");	//这条线路的名字以及站点名
        	
        	Line line = new Line();
        	List<Station> stations=new ArrayList<Station>();
        	String linename=thisline[0];
        	line.setName(linename);
        	
        	for(int j=1;j<thisline.length;j++) {
        		Station station = new Station();
        		int exist = 0;	//判断站点是否已经在线路里了（换乘站）
        		station.setName(thisline[j]);
        		for(Line l:lineset) {	//处理换乘站
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
        		
        		if(j==thisline.length-1&&thisline[j].equals(thisline[1])) {  //处理环线
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
