package test;

import java.util.Calendar;

import entity.MyCalendar;

public class CalendarTest {
	public static void main(String[] args){
	//マイカレンダーインスタンス生成
			MyCalendar mc = new MyCalendar();
			//現在日時でカレンダーインスタンスを生成
			Calendar cal = Calendar.getInstance();
			//二つの引数が来ていたら引数の一つ目を年、二つ目を月に設定
//			if(args.length == 2) {
				cal.set(Calendar.YEAR, 2022);
				cal.set(Calendar.MONTH, 11 - 1);
//			}
			//マイカレンダーに年月をセット
			mc.setYear(cal.get(Calendar.YEAR));
			mc.setMonth(cal.get(Calendar.MONTH) + 1);
			
			System.out.println("表示年" + mc.getYear());
			System.out.println("表示月" + mc.getMonth());
			
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
			
//			//前月の年をセットする
			mc.setLastYear(cal.get(Calendar.YEAR));
			System.out.println("前月の年" + mc.getLastYear());
//			//前月の月をセットする
			mc.setLastMonth(cal.get(Calendar.MONTH) + 1);
			System.out.println("前月の月" + mc.getLastMonth());
//			//次月の年月をセットするため次月にする
			cal.set(Calendar.MONTH, mc.getMonth());
//			System.out.println("今の設定月" + cal.get(Calendar.MONTH));
//			//次月の年をセットする
			if(mc.getMonth() == 1) {
				mc.setNextYear(cal.get(Calendar.YEAR) + 1);
				System.out.println("次月の年" + mc.getNextYear());
			} else {
				mc.setNextYear(cal.get(Calendar.YEAR));
				System.out.println("次月の年" + mc.getNextYear());				
			}
//			//次月の月をセットする
			mc.setNextMonth(cal.get(Calendar.MONTH) + 1);
			System.out.println("次月の月" + mc.getNextMonth());
			
			
//			cal.set(Calendar.MONTH, mc.getMonth() - 1);
//			mc.setLastYear(cal.get(Calendar.YEAR));
//			mc.setLastMonth(cal.get(Calendar.MONTH));
//			
//			//次月の年月をセットする
//			cal.set(Calendar.MONTH, mc.getMonth() + 1);
//			mc.setNextYear(cal.get(Calendar.YEAR));
//			mc.setNextMonth(cal.get(Calendar.MONTH));
	}
}
