package model;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private String name;	//��·����
	private List<Station> stations = new ArrayList<Station>();	//��·�������ĵ���վ
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	public void addStation(Station station) {
		stations.add(station);
	}
	public Line() {
		
	}
}
