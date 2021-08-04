package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class WorkTime implements Serializable {
	private int empId; //社員ID
	private LocalDate workDate; //日付
	private LocalTime startTime; //出勤時刻
	private String startCond; //出勤コンディション
	private LocalTime breakStartTime; //休憩開始時刻
	private LocalTime breakFinishTime; //休憩終了時刻
	private LocalTime finishTime; //退勤時刻
	private String finishCond; //退勤コンディション
	
	public WorkTime() {}
	public WorkTime(int empId, LocalDate workDate, LocalTime startTime, String startCond, LocalTime breakStartTime, LocalTime breakFinishTime, LocalTime finishTime, String finishCond) {
		this.empId = empId;
		this.workDate = workDate;
		this.startTime = startTime;
		this.startCond = startCond;
		this.breakStartTime = breakStartTime;
		this.breakFinishTime = breakFinishTime;
		this.finishTime = finishTime;
		this.finishCond = finishCond;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public void setWorkDate(LocalDate workDate) {
		this.workDate = workDate;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public void setStartCond(String startCond) {
		this.startCond = startCond;
	}
	public void setBreakStartTime(LocalTime breakStartTime) {
		this.breakStartTime = breakStartTime;
	}
	public void setBreakFinishTime(LocalTime breakFinishTime) {
		this.breakFinishTime = breakFinishTime;
	}
	public void setFinishTime(LocalTime finishTime) {
		this.finishTime = finishTime;
	}
	public void setFinishCond(String finishCond) {
		this.finishCond = finishCond;
	}
	public int getEmpId() {
		return empId;
	}
	public LocalDate getWorkDate() {
		return workDate;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public String getStartCond() {
		return startCond;
	}
	public LocalTime getBreakStartTime() {
		return breakStartTime;
	}
	public LocalTime getBreakFinishTime() {
		return breakFinishTime;
	}
	public LocalTime getFinishTime() {
		return finishTime;
	}
	public String getFinishCond() {
		return finishCond;
	}

}
