package model;

import java.util.Calendar;

import entity.MyCalendar;

public class MyCalendarLogic {
	public MyCalendar createMyCalendar(int... args) {
		//マイカレンダーインスタンス生成
		MyCalendar mc = new MyCalendar();
		//現在日時でカレンダーインスタンスを生成
		Calendar cal = Calendar.getInstance();
		//二つの引数が来ていたら引数の一つ目を年、二つ目を月に設定
		if(args.length == 2) {
			cal.set(Calendar.YEAR, args[0]);
			cal.set(Calendar.MONTH, args[1] - 1);
		}
		//マイカレンダーに年月をセット
		mc.setYear(cal.get(Calendar.YEAR));
		mc.setMonth(cal.get(Calendar.MONTH) + 1);
		
		//その月の1日が何曜日か調べるために日付を1日にする
		cal.set(Calendar.DATE, 1);
		//カレンダー最初の空白の数
		int before = cal.get(Calendar.DAY_OF_WEEK) - 1;
		//その月の日付の数
		int daysCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//その月の最終日が何曜日か調べるために日付を最終日にする
		cal.set(Calendar.DATE, daysCount);
		//その月の最後の日後の空白の数
		int after = 7 - cal.get(Calendar.DAY_OF_WEEK);
		//全ての要素数
		int total = before + daysCount + after;
		//全ての要素を幅7個の配列に入れたら何行になるか
		int rows = total / 7;
		//その行数で二次元配列を生成
		String[][] data = new String[rows][7];
		//今見ているカレンダーが今月かどうか調べるために、今の日付情報を持つもう一つのカレンダーインスタンスを生成しておく
		Calendar now = Calendar.getInstance();
		
		//前月の最終日が何日か調べるために月を前月にする
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.MONTH, mc.getMonth() - 2);

		//前月の最終日
		int beforeMonthLastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//前月の年をセットする
		mc.setLastYear(cal.get(Calendar.YEAR));
//		//前月の月をセットする
		mc.setLastMonth(cal.get(Calendar.MONTH) + 1);
//		//次月の年月をセットするため次月にする
		cal.set(Calendar.MONTH, mc.getMonth());
//		//次月の年をセットする
		if(mc.getMonth() == 1) {
			mc.setNextYear(cal.get(Calendar.YEAR) + 1);
			System.out.println("次月の年" + mc.getNextYear());
		} else {
			mc.setNextYear(cal.get(Calendar.YEAR));
			System.out.println("次月の年" + mc.getNextYear());				
		}
//		//次月の月をセットする
		mc.setNextMonth(cal.get(Calendar.MONTH) + 1);
		System.out.println("次月の月" + mc.getNextMonth());
		
		//二次元配列を作成
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < 7; j++) {
				//前月の日付
				if(i == 0 && j < before) {
					int date = beforeMonthLastDay - before + j + 1;
					data[i][j] = String.valueOf(date);
					data[i][j] = "b" + data[i][j];
				//次月の日付
				} else if(i == rows - 1 && j >= (7 - after)) {
					//カレンダーの前後に入る空欄の部分は空文字
					int date = j + 1 - (7 - after);
					data[i][j] = String.valueOf(date);
					data[i][j] = "a" + data[i][j];
				} else {
					//日付の生成
					int date = i * 7 + j + 1 - before;
					//配列に日付を入れる
					data[i][j] = String.valueOf(date);
					//もし今作業している年月日が「今日」だったら日付の先頭に「＊」をつける
					if(now.get(Calendar.DATE) == date && now.get(Calendar.MONTH) == mc.getMonth() - 1 && now.get(Calendar.YEAR) == mc.getYear()) {
						data[i][j] = "t" + data[i][j];
					}
				}
			}
		}
		//作成した二次元配列をマイカレンダーにセット
		mc.setData(data);
		return mc;
	}
}
