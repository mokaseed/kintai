package entity;

import java.io.Serializable;

public class MyCalendar implements Serializable{
	
	private int year;
	private int month;
	private  String[][] data;
	
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String[][] getData() {
		return data;
	}
	public void setData(String[][] data) {
		this.data = data;
	}
	
	
}
