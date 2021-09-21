package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//入力したスケジュール情報内容にエラーがないかチェックする
public class AddScheduleCheck {
	public List<String> execute(String year, String month, String date, String startHour, String startMinute, String finishHour, String finishMinute){
		
		//戻り値を用意
		List<String> errorMsgList = new ArrayList<>();
		
		
		//年月日を未選択（「ー」を選択）の場合
		if(year == null || year.length() == 0 || month == null || month.length() == 0 || date == null || date.length() == 0) {
			errorMsgList.add("・日付を選択してください");
			System.out.println("・日付を選択してください");
		} else {
			//存在しない日付を選択した場合
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(year));
			cal.set(Calendar.MONTH, Integer.parseInt(month) -1);
			int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			if(Integer.parseInt(date) > lastDate) {
				errorMsgList.add("・正しくない日付を選択しています。");
				System.out.println("・正しくない日付を選択しています。");
			}
		}
		
		//未入力の時間項目はNULLを入れる
		if(startHour.length() == 0) {
			startHour = null;
		}
		if(startMinute.length() == 0) {
			startMinute = null;
		}
		if(finishHour.length() == 0) {
			finishHour = null;
		}
		if(finishMinute.length() == 0) {
			finishMinute = null;
		}
		
		//開始時刻の時を選択し、分を選択していない場合
		if(startHour != null && startMinute == null) {
			errorMsgList.add("・開始時刻の分数が未選択です");
			System.out.println("・開始時刻の分数が未選択です");
		}
		//開始時刻の分を選択し、時を選択していない場合
		if(startMinute != null && startHour == null) {
			errorMsgList.add("・開始時刻の時間が未選択です");
			System.out.println("・開始時刻の時間が未選択です");
		}
		//終了時刻の時を選択し、分を選択していない場合
		if(finishHour != null && finishMinute == null) {
			errorMsgList.add("・終了時刻の分数が未選択です");
			System.out.println("・終了時刻の分数が未選択です");
		}
		//終了時刻の分を選択し、時を選択していない場合
		if(finishMinute != null && finishHour == null) {
			errorMsgList.add("・終了時刻の時間が未選択です");
			System.out.println("・終了時刻の時間が未選択です");
		}
		
		//終了時刻を選択し、開始時刻を選択していない場合
		if((finishHour != null && finishMinute != null) && (startHour == null || startMinute == null )) {
			errorMsgList.add("・開始時刻を選択してください");
			System.out.println("・開始時刻を選択してください");
		}
		
		//終了時刻が開始時刻より早い場合
		if(startHour != null && finishHour != null) {
			if(Integer.parseInt(startHour) > Integer.parseInt(finishHour)) {
				errorMsgList.add("・終了時刻は開始時刻より後の時間で登録してください");
				System.out.println("・終了時刻は開始時刻より後の時間で登録してください");
			}
		}
		
		
		return errorMsgList;
	}

}
