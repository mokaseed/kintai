package model;

import java.util.ArrayList;
import java.util.List;

public class WorkTimeCheck {
	
	//入力された勤務時刻のチェック
	
	//時分片方が入力されていて、もう片方が未入力の場合はエラー
	//存在しない時刻が入力された場合はエラー
	//数字以外の文字が入力された場合はエラー
	public List<String> execute(String hStartTime, String mStartTime, String hBreakStartTime, String mBreakStartTime, String hBreakFinishTime, String mBreakFinishTime, String hFinishTime, String mFinishTime){
		
		//戻り値の用意
		List<String> errorMsgList = new ArrayList<>();
		
		if(hStartTime.length() != 0 && mStartTime.length() != 0) {
			//時分片方が入力されていて、もう片方が未入力の場合はエラー
			if(hStartTime.length() == 0) {
				errorMsgList.add("・出勤時刻の時間が入力されていません");
			 }
			if(mStartTime.length() == 0) {
				 errorMsgList.add("・出勤時刻の分数が入力されていません");
			 }
			
			try {
				//存在しない時刻が入力された場合はエラー
				if(Integer.parseInt(hStartTime) > 24 || Integer.parseInt(mStartTime) > 59) {
					errorMsgList.add("・正しい出勤時刻を設定してください");
				}
				
			//数字以外の文字が入力された場合はエラー
			} catch(NumberFormatException e) {
				errorMsgList.add("・半角数字で入力してください（出勤時刻）");
			}
			
			
		}
		if(hBreakStartTime.length() != 0 && mBreakStartTime.length() != 0) {
			//時分片方が入力されていて、もう片方が未入力の場合はエラー
			if(hBreakStartTime.length() == 0) {
				errorMsgList.add("・休憩開始時刻の時間が入力されていません");
			}
			if(mBreakStartTime.length() == 0) {
				 errorMsgList.add("・休憩開始時刻の分数が入力されていません");
			}
			try {
				//存在しない時刻が入力された場合はエラー
				if(Integer.parseInt(hBreakStartTime) > 24 || Integer.parseInt(mBreakStartTime) > 59) {
					errorMsgList.add("・正しい休憩開始時刻を設定してください");
				}
			//数字以外の文字が入力された場合はエラー
			} catch(NumberFormatException e) {
				errorMsgList.add("・半角数字を入力してください（休憩開始時刻）");
			}	 
		}
		if(hBreakFinishTime.length() != 0 && mBreakFinishTime.length() != 0) {
			//時分片方が入力されていて、もう片方が未入力の場合はエラー
			 if(hBreakFinishTime.length() == 0) {
				errorMsgList.add("・休憩終了時刻の時間が入力されていません");
			 }
			 if(mBreakFinishTime.length() == 0) {
				 errorMsgList.add("・休憩終了時刻の分数が入力されていません");
			 }
			try {
				//存在しない時刻が入力された場合はエラー
				if(Integer.parseInt(hBreakFinishTime) > 24 || Integer.parseInt(mBreakFinishTime) > 59) {
					errorMsgList.add("・正しい休憩終了時刻を設定してください");
				}
			//数字以外の文字が入力された場合はエラー
			} catch(NumberFormatException e) {
				errorMsgList.add("・半角数字を入力してください（休憩終了時刻）");
			}
		}
		
		if(hFinishTime.length() != 0 && mFinishTime.length() != 0) {
			//時分片方が入力されていて、もう片方が未入力の場合はエラー
			if(hFinishTime.length() == 0) {
				errorMsgList.add("・退勤時刻の時間が入力されていません。");
			}
			if(mFinishTime.length() == 0) {
				 errorMsgList.add("・退勤時刻の分数が入力されていません。");
			 }
			try {
				//存在しない時刻が入力された場合はエラー
				if(Integer.parseInt(hFinishTime) > 24 || Integer.parseInt(mFinishTime) > 59) {
					errorMsgList.add("・正しい退勤時刻を設定してください");
				}
			//数字以外の文字が入力された場合はエラー
			} catch(NumberFormatException e) {
				errorMsgList.add("・半角数字を入力してください（退勤時刻）");
			}
		}
		
		return errorMsgList;
	}
}
