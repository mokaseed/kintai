package model;

import java.time.format.DateTimeFormatter;
import java.util.List;

import entity.WorkTime;


//タイムシートに記録されている時間が「出勤時間<休憩開始時間＜休憩終了時間＜退勤時間」になっているかチェック
public class WorkTimeListTimeCheck {
	
	public boolean execute(List<WorkTime> workTimeList , int i){
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd");
		boolean flag = true;
		for(WorkTime workTime : workTimeList){
			String workDate = workTime.getWorkDate().format(dateFormat);
			if(Integer.parseInt(workDate) == i){
				
				if(workTime.getStartTime() != null) {
					if(workTime.getBreakStartTime() != null) {
						if(workTime.getStartTime().isAfter(workTime.getBreakStartTime())) {
							flag = false;
						}
					}
					if(workTime.getBreakFinishTime() != null) {
						if(workTime.getStartTime().isAfter(workTime.getBreakFinishTime())) {
							flag = false;
						}
					}
					if(workTime.getFinishTime() != null) {
						if(workTime.getStartTime().isAfter(workTime.getFinishTime())) {
							flag = false;
						}
					}
				}
				if(workTime.getBreakStartTime() != null) {
					if(workTime.getBreakFinishTime() != null) {
						if(workTime.getBreakStartTime().isAfter(workTime.getBreakFinishTime())) {
							flag = false;
						}
					}
					if(workTime.getFinishTime() != null) {
						if(workTime.getBreakStartTime().isAfter(workTime.getFinishTime())) {
							flag = false;
						}
					}
				}
				if(workTime.getBreakFinishTime() != null) {
					if(workTime.getFinishTime() != null) {
						if(workTime.getBreakFinishTime().isAfter(workTime.getFinishTime())) {
							flag = false;
						}
					}
				}
			}
		}
		return flag;
		
	}
}
