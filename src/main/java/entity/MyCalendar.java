package entity;

import java.io.Serializable;

public class MyCalendar implements Serializable{
	
	private int year;
	private int month;
	private  String[][] data;
	private int lastYear;
	private int lastMonth;
	private int nextYear;
	private int nextMonth;
	
	
	public int getLastYear() {
		return lastYear;
	}
	public void setLastYear(int lastYear) {
		this.lastYear = lastYear;
	}
	public int getLastMonth() {
		return lastMonth;
	}
	public void setLastMonth(int lastMonth) {
		this.lastMonth = lastMonth;
	}
	public int getNextYear() {
		return nextYear;
	}
	public void setNextYear(int nextYear) {
		this.nextYear = nextYear;
	}
	public int getNextMonth() {
		return nextMonth;
	}
	public void setNextMonth(int nextMonth) {
		this.nextMonth = nextMonth;
	}
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
