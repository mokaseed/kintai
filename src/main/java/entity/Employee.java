package entity;

import java.io.Serializable;

public class Employee implements Serializable{
	private int empId; //社員ID
	private String name; //氏名
	private String pass; //パスワード
	private String deptName; //部署名
	private String tel; //電話番号
	private String mail; //メールアドレス
	private String hireDate; //入社日
	private String sysadmin; //管理者権限有無
	private String remarks; //備考
	
	public Employee() {	}
	public Employee(String name, String pass) {
		this.pass = pass;
		this.name = name;
	}
	public Employee(int empId, String name, String pass, String deptName, String tel, String mail, String hireDate, String sysadmin, String remarks) {
		this.empId = empId;
		this.name = name;
		this.pass = pass;
		this.deptName = deptName;
		this.tel = tel;
		this.mail = mail;
		this.hireDate = hireDate;
		this.sysadmin = sysadmin;
		this.remarks = remarks;
	}
	
	public int getEmpId() {
		return empId;
	}
	public String getPass() {
		return pass;
	}
	public String getName() {
		return name;
	}
	public String getDeptId() {
		return deptName;
	}
	public String getTel() {
		return tel;
	}
	public String getMail() {
		return mail;
	}
	public String getHireDate() {
		return hireDate;
	}
	public String getSysadmin() {
		return sysadmin;
	}
	public String getRemarks() {
		return remarks;
	}
	
	
	

}
