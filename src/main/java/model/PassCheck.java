package model;

import entity.Employee;

//DBから取得したパスワードと、ログイン画面で入力したパスワードが一致しているか調べる
public class PassCheck {
	public boolean execute(Employee emp, Employee account) {
		if(emp.getPass().equals(account.getPass())) {
			return true;
		}
		return false;
	}

}
