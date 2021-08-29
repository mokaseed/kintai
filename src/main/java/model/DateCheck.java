package model;

import java.util.Calendar;

//入社日が「yyyy-MM-dd」になっているか確認
//入力した年月日が存在する日付か確認
//未入力の場合もOK
public class DateCheck {
	private String regex = "\\d{4}\\-\\d{2}\\-\\d{2}";
	
	public boolean execute(String hireDate) {
		if(hireDate.matches(regex)) {
	        System.out.println("入社日表記チェックOK");
	        
	        //入力した年月日が存在する日付か確認
	        Calendar cal = Calendar.getInstance();
	        //指定の年月をカレンダーにセット
	        cal.set(Calendar.YEAR, Integer.parseInt(hireDate.substring(0, 4)));
	        cal.set(Calendar.MONTH, Integer.parseInt(hireDate.substring(5, 7)));
	        //その月の最終日を取得
	        int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        //存在しない日付の場合はfalseを返す
	        if(maxDate < Integer.parseInt(hireDate.substring(8, 10))) {
	        	System.out.println("存在しない日付");
	        	return false;
	        } else {
	        	return true;	        	
	        }
	        
		} else if(hireDate.length() == 0 || hireDate == null) {
			System.out.println("入社日表記チェックOK(未入力)");
			return true;
	    }else {
	    	System.out.println("入社日表記チェックNG");
	        return false;
	    }
	}

}
