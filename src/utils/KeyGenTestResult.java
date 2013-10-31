package utils;

import java.util.Date;

public class KeyGenTestResult {
	private Integer bitLenght;
	private long miliseconds;
	private Date start;
	private Date end;
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
		this.setEnd(start);
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
		this.miliseconds = this.getMiliseconds();
	}

	public Integer getBitLenght() {
		return bitLenght;
	}
	
	public void setBitLenght(Integer bitLenght) {
		this.bitLenght = bitLenght;
	}
	
	public long getMiliseconds() {
		return this.end.getTime() - this.start.getTime();
	}
	
	public Integer getSeconds() {
		return (int) (this.getMiliseconds()/1000);
	}
	
	public String getFormatedDuration() {
		String ret = new String();
		int seconds = (int) (miliseconds/1000);
		int minutes = (int) (seconds / 60);
		ret = String.format("%02d:%02d,%03d", minutes, (int) (seconds - (minutes * 60)), miliseconds - (seconds * 1000) );
		return ret;
	}
}