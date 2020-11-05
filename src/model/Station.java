package model;

import java.util.ArrayList;
import java.util.List;

public class Station {
	private String name;  //地铁站名称
    private List<String> line=new ArrayList<String>();  //地铁站所在线路（如果是换乘站，那么这个站点属于多条线路）
    private List<Station> linkstations = new ArrayList<Station>();  //与该站相邻的地铁站
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getLine() {
		return line;
	}
	public void setLine(List<String> line) {
		this.line = line;
	}
	public List<Station> getLinkstations() {
		return linkstations;
	}
	public void setLinkstations(List<Station> linkstations) {
		this.linkstations = linkstations;
	}
	
	
}
