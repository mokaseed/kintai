package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class MySchedule implements Serializable {
	
	private int skdId; //スケジュールID
	private int empId; //社員ID
	private String year; //スケジュールの年
	private String month; //スケジュールの月
	private String date; //スケジュールの日
	private String startHour; //スケジュールの開始時刻の時
	private String startMinute; //スケジュールの開始時刻の分
	private String finishHour; //スケジュールの開始時刻の時
	private String finishMinute; //スケジュールの開始時刻の分
	private LocalDate skdDate; //スケジュールの年月日
	private LocalTime skdStartTime; //スケジュールの開始時刻
	private LocalTime skdFinishTime; //スケジュールの終了時刻
	private String color; //スケジュールの色
	private String subject; //スケジュールの件名
	private String memo; //スケジュールのメモ
	
	public MySchedule() {}
	public MySchedule(int empId, LocalDate skdDate, LocalTime skdStartTime, LocalTime skdFinishTime, String color, String subject, String memo) {
		this.empId = empId;
		this.skdDate = skdDate;
		this.skdStartTime = skdStartTime;
		this.skdFinishTime = skdFinishTime;
		this.color = color;
		this.subject = subject;
		this.memo = memo;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMinute() {
		return startMinute;
	}
	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}
	public String getFinishHour() {
		return finishHour;
	}
	public void setFinishHour(String finishHour) {
		this.finishHour = finishHour;
	}
	public String getFinishMinute() {
		return finishMinute;
	}
	public void setFinishMinute(String finishMinute) {
		this.finishMinute = finishMinute;
	}
	public LocalTime getSkdStartTime() {
		return skdStartTime;
	}
	public void setSkdStartTime(LocalTime skdStartTime) {
		this.skdStartTime = skdStartTime;
	}
	public LocalTime getSkdFinishTime() {
		return skdFinishTime;
	}
	public void setSkdFinishTime(LocalTime skdFinishTime) {
		this.skdFinishTime = skdFinishTime;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public LocalDate getSkdDate() {
		return skdDate;
	}
	public void setSkdDate(LocalDate skdDate) {
		this.skdDate = skdDate;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSkdId() {
		return skdId;
	}
	public void setSkdId(int skdId) {
		this.skdId = skdId;
	}
	
	
	
	

}
