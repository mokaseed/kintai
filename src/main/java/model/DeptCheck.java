package model;

import java.util.List;

import entity.Dept;

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
