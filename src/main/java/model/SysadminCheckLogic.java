package model;

import entity.Employee;

//ログインしたアカウントの管理者権限有無を調べる
public class SysadminCheckLogic {
	public boolean execute(Employee account) {
		if("1".equals(account.getSysadmin())) {
			return true;
		}
		return false;
	}
}
