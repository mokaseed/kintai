package model;

//入社日が「yyyy-MM-dd」になっているかの確認
//未入力の場合もOK
public class DateCheck {
	private String regex = "\\d{4}\\-\\d{2}\\-\\d{2}";
	
	public boolean execute(String hireDate) {
		if(hireDate.matches(regex)) {
	        System.out.println("入社日表記チェックOK");
	        return true;
		} else if(hireDate.length() == 0 || hireDate == null) {
			System.out.println("入社日表記チェックOK(未入力)");
			return true;
	    }else {
	    	System.out.println("入社日表記チェックNG");
	        return false;
	    }
	}

}
