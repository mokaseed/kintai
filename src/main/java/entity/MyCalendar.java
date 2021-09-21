package entity;

import java.io.Serializable;

public class MyCalendar implements Serializable{
	
	private int year; //カレンダーの年
	private int month; //カレンダーの月
	private  String[][] data; //カレンダーの日付
	private int lastYear; //前月の年
	private int lastMonth; //前月の月
	private int nextYear; //次月の年
	private int nextMonth; //次月の月
	
	
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
