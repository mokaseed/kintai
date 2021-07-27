package model;

import entity.Employee;

public class SysadminCheckLogic {
	public boolean execute(Employee account) {
		if("1".equals(account.getSysadmin())) {
			return true;
		}
		return false;
	}
}
