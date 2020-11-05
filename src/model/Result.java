package model;

public class Result {
	private Station start;  //起始站
    private Station end;   //终点站
    private int distance;  //距离
    private int linechange;  //标记期间是否有换乘，0为无换乘，1为有换乘
	public Station getStart() {
		return start;
	}
	public void setStart(Station start) {
		this.start = start;
	}
	public Station getEnd() {
		return end;
	}
	public void setEnd(Station end) {
		this.end = end;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getLinechange() {
		return linechange;
	}
	public void setLinechange(int linechange) {
		this.linechange = linechange;
	}
	
}
