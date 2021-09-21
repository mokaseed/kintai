package model;

import java.util.List;

import entity.Dept;

//事業部名を元に事業部IDを返す
public class DeptCheck {
	
	int deptId;
	
	public int execute(String deptName, List<Dept> deptList) {
		for(Dept dept : deptList) {
			if(dept.getDeptName().equals(deptName)){
				deptId = dept.getDeptId();
			}
		}
		return deptId;
	}

}
