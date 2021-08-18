package entity;

public class Dept {
	private int deptId; //事業部ID
	private String deptName; //事業部名
	
	public Dept() {}
	public Dept(int deptId, String deptName) {
		this.deptId = deptId;
		this.deptName = deptName;
	}
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	

}
