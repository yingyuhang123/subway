package model;

import java.util.ArrayList;
import java.util.List;

public class Station {
	private String name;  //����վ����
    private List<String> line=new ArrayList<String>();  //����վ������·������ǻ���վ����ô���վ�����ڶ�����·��
    private List<Station> linkstations = new ArrayList<Station>();  //���վ���ڵĵ���վ
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
