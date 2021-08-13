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
	public Employee(int empId, String pass) {
		this.pass = pass;
		this.empId = empId;
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
	
	public Employee(int empId, String tel, String mail, String pass, String remarks) {
		this.empId = empId;
		this.tel = tel;
		this.mail = mail;
		this.pass = pass;
		this.remarks= remarks;
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
	public String getDeptName() {
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
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public void setSysadmin(String sysadmin) {
		this.sysadmin = sysadmin;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
