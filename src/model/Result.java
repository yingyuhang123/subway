package model;

public class Result {
	private Station start;  //��ʼվ
    private Station end;   //�յ�վ
    private int distance;  //����
    private int linechange;  //����ڼ��Ƿ��л��ˣ�0Ϊ�޻��ˣ�1Ϊ�л���
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
