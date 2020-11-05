package model;

import java.util.ArrayList;
import java.util.List;

public class Line {
	private String name;	//线路名称
	private List<Station> stations = new ArrayList<Station>();	//线路所包含的地铁站
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
